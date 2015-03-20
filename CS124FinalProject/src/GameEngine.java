import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
 
public class GameEngine extends Canvas{
	protected final int canvasX = 500;
	protected final int canvasY = 500;
	protected int centerX, centerY;
	protected BufferedImage bg, mole, go;
	
	protected ZombieFactory factory;
	protected Player character;
	
	protected double rad, mouseX, mouseY;
	protected ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	protected ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	protected ArrayList<Bullet> bulletPool = new ArrayList<Bullet>();
	private final int POOL_SIZE = 50;
	
	private AffineTransform tx;
	private AffineTransformOp op;
	 
	protected ZombieMaker zMaker;
	protected StageHandler stageHandler;
	private Thread threadZombieMaker;
	private boolean isOver = false;
	
	protected int gold, stage, totalZombieCount, zombieKilled, totalZombieKilled;
	
	private PriceVisitor visitor = new PriceVisitor();
	
	/*
	 * Game Over Alpha over
	 */
	private float gameOverAlpha = 0;
	public static final long RUNNING_TIME = 2000;
	
	InfoFrame infoFrame;
	public GameEngine() throws FileNotFoundException{
		infoFrame = new InfoFrame(this);
		infoFrame.setVisible(true);
		
		
		setSize(canvasX, canvasY);
		
		centerX = getWidth()/2;
		centerY = getHeight()/2;
		
		character = new Player(this);
		factory = new ZombieFactory(this);
		
		
		try {
			bg = ImageIO.read( new File("img/GameBG.png"));
			mole = ImageIO.read(new File("img/Mole.png"));
			go = ImageIO.read(new File("img/gameover.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		rad = 0;
		mouseX = 1;
		mouseY = 1;
		
		stage = 1;
		gold = 0;
		
		
		Thread repainter = new Thread(new Repainter(this));
		repainter.start();
		
		stageHandler = new StageHandler(this);
		
		zMaker = new ZombieMaker(this);
		threadZombieMaker = new Thread(zMaker);
		threadZombieMaker.start();

		stageHandler.setStage(stage);
		
		/*
		 * Upgrade Instantiations
		 * 
		 */
		BetterAimUpgrade aimUp = new BetterAimUpgrade();
		ReinforcedBullets bullUp = new ReinforcedBullets();
		
		infoFrame.addUpgrade(aimUp);
		infoFrame.addUpgrade(bullUp);
		character.upgrades.add(aimUp);
		character.upgrades.add(bullUp);
		
		infoFrame.init();
		
		/*
		 * Bullet Pool Instantiation
		 * 
		 */
		for(int i = 0; i < POOL_SIZE; i++){
			bulletPool.add(new Bullet(0, this));
		}
		
		
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		Graphics gra;
		BufferedImage offscreen = null;
		offscreen = (BufferedImage) createImage(getWidth(), getHeight());
		gra = offscreen.getGraphics();
		
		gra.drawImage(bg,0,0,canvasX,canvasY,null);
	
		rad = Math.atan2(mouseY,mouseX);
		character.setDirection(rad);
		//gra.drawLine(centerX, centerY, (int)(Math.cos(rad) * 50 + centerX), (int)(Math.sin(rad) * 50 + centerY));
		
		tx = AffineTransform.getRotateInstance(rad, mole.getWidth()/2, mole.getHeight()/2);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		//Mole Image
		gra.drawImage(op.filter(mole, null), centerX-character.getSize()/2, centerY-character.getSize()/2, character.getSize(), character.getSize(), null);
		
		for(int i = 0; i < zombieList.size(); i++){
			zombieList.get(i).draw(gra);
		}
		
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).draw(gra);
		}
		
		g2d.setComposite(AlphaComposite.SrcOver.derive(1f - gameOverAlpha));
		g2d.drawImage(offscreen, 0, 0, null);
		g2d.setComposite(AlphaComposite.SrcOver.derive(gameOverAlpha));
		g2d.drawImage(go, 0, 0, null);
  
        g2d.dispose();

		checkGameState();
		bulletOutChecker();
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public synchronized void checkGameState(){
		/*
		 * Setting infoFrame Damage
		 * Checking if stage is done
		 */
		
		if(totalZombieCount-zombieKilled <= 0){
			stageHandler.setStage(stage++);
		}
		
		infoFrame.setDamage(character.getDamage());
		/*
		 * Bullet Collision checking
		 */
		for(int i = 0; i < zombieList.size(); i++){
			for(int j = 0; j < bulletList.size(); j++){
				if(zombieList.get(i).checkHit(bulletList.get(j))){
					Bullet b = bulletList.get(j);
					/*
					 * Bullet Pool Replenish
					 */
					bulletList.remove(b);
					bulletPool.add(b);
					b.reset();
					/*
					 * Uses Visitor to calculate for damage
					 */
					if(zombieList.get(i).damage(character.getDamage())){
						zombieList.get(i).killThread();
						gold += zombieList.get(i).getBounty();
						zombieList.remove(zombieList.get(i));
						totalZombieKilled++;
						zombieKilled++;
						
						//Updating of infoFrame
						infoFrame.setGold(gold);
						infoFrame.setZombieCount(totalZombieCount-zombieKilled);
					}
					
				}
			}
		}
		/*
		 * Gold checking & Button enabling
		 */
		for(int i = 0; i < infoFrame.upList.size(); i++){
			if(gold >= visitor.visit(infoFrame.upList.get(i))){
				infoFrame.btnList.get(infoFrame.upList.indexOf(infoFrame.upList.get(i))).setEnabled(true);
			}
			else{
				infoFrame.btnList.get(infoFrame.upList.indexOf(infoFrame.upList.get(i))).setEnabled(false);
			}
		}	
	}
	/*
	 * Thread clean up
	 */
	public void killAllThreads(){
		gameOver();
		threadZombieMaker.stop();
		for(int i = 0; i < zombieList.size(); i++){
			zombieList.get(i).killThread();
			System.out.println("KILL");
		}
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).killThread();
		}
	}
	private void gameOver(){
		if(!isOver){
			isOver = true;
			Timer timer = new Timer();
			timer.schedule(new GameOverTask(), 0, RUNNING_TIME);
		}
		
	}
	
	public void bulletOutChecker(){
		for(int i = 0; i < bulletList.size(); i++){
			Bullet b = bulletList.get(i);
			if(b.x > canvasX || b.x < 0 || b.y > canvasY || b.y < 0){
				bulletList.remove(b);
				bulletPool.add(b);
				b.reset();
			}
		}
	}
	
	public synchronized void attack(){
		//engine.bulletList.add(new Bullet(engine.character.direction, engine));
		bulletPool.get(0).move();
		bulletList.add(bulletPool.remove(0));
		bulletList.get(bulletList.size()-1).setDirection(character.direction);;
		
		
	}
	
	class GameOverTask extends TimerTask{
		/*
		 * Task to overlay Game Over screen
		 * 
		 */
		@Override
		public void run() {
			gameOverAlpha += 1.0f;
			 if (gameOverAlpha >= 1.0f) {
				 gameOverAlpha = 1.0f;
		    } else {
		        repaint();
		    }
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
