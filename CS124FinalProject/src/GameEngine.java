import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
 
public class GameEngine extends Canvas{
	protected final int canvasX = 500;
	protected final int canvasY = 500;
	protected int centerX, centerY;
	protected BufferedImage bg, mole;
	
	protected ZombieFactory factory;
	protected Player character;
	
	protected double rad, mouseX, mouseY;
	protected ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	protected ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	
	private AffineTransform tx;
	private AffineTransformOp op;
	 
	protected ZombieMaker zMaker;
	protected StageHandler stageHandler;
	
	private int stage;
	
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		rad = 0;
		mouseX = 1;
		mouseY = 1;
		
		stage = 1;
		
		Thread repainter = new Thread(new Repainter(this));
		repainter.start();
		
		stageHandler = new StageHandler(this);
		
		zMaker = new ZombieMaker(this);
		Thread threadZombieMaker = new Thread(zMaker);
		threadZombieMaker.start();

		stageHandler.setStage(stage);
	}
	
	@Override
	public void paint(Graphics g){
		Graphics gra;
		BufferedImage offscreen = null;
		offscreen = (BufferedImage) createImage(getWidth(), getHeight());
		gra = offscreen.getGraphics();
		
		gra.drawImage(bg,0,0,null);
	
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
		
		
		g.drawImage(offscreen, 0, 0, null);
		
		checkGameState();
		
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void checkGameState(){
		if(zMaker.isDepleted() && zombieList.isEmpty()){
			stageHandler.setStage(stage++);
			infoFrame.setStage(stage);
		}
		for(int i = 0; i < zombieList.size(); i++){
			for(int j = 0; j < bulletList.size(); j++){
				if(zombieList.get(i).checkHit(bulletList.get(j))){
					bulletList.remove(bulletList.get(j));
					zombieList.remove(zombieList.get(i));
					infoFrame.decreaseZombieCount();
				}
			}
		}
	}
}
