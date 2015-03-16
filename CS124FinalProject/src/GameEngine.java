import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
 
public class GameEngine extends Canvas{
	protected final int canvasX = 500;
	protected final int canvasY = 500;
	protected int centerX, centerY;
	
	protected ZombieFactory factory;
	protected Player character;
	
	protected double rad, mouseX, mouseY;
	protected ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	protected ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	 
	private int stage;
	
	public GameEngine() throws FileNotFoundException{
		setSize(canvasX, canvasY);
		
		character = new Player(this);
		factory = new ZombieFactory(this);
		
		centerX = getWidth()/2;
		centerY = getHeight()/2;
		
		setBackground(Color.BLACK);
		rad = 0;
		mouseX = 1;
		mouseY = 1;
		
		
		
		
		stage = 1;
		
		Thread repainter = new Thread(new Repainter(this));
		repainter.start();
		
		
		Thread zMaker = new Thread(new ZombieMaker(this));
		zMaker.start();
	}
	
	@Override
	public void paint(Graphics g){
		Graphics gra;
		BufferedImage offscreen = null;
		offscreen = (BufferedImage) createImage(getWidth(), getHeight());
		gra = offscreen.getGraphics();
	
		gra.setColor(Color.WHITE);
		gra.fillArc(centerX-character.getSize()/2, centerY-character.getSize()/2, character.getSize(), character.getSize(), 0, 360);
		
		rad = Math.atan2(mouseY,mouseX);
		character.setDirection(rad);
		gra.drawLine(centerX, centerY, (int)(Math.cos(rad) * 50 + centerX), (int)(Math.sin(rad) * 50 + centerY));
		
		for(int i = 0; i < zombieList.size(); i++){
			zombieList.get(i).draw(gra);
		}
		
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).draw(gra);
		}
		
		/*System.out.println("X:"+mouseX);
		System.out.println("Y:"+mouseY);
		System.out.println("RAD:"+rad);*/
		
		
		g.drawImage(offscreen, 0, 0, null);
		
		checkGameState();
		
	}
	
	public void checkGameState(){
		for(int i = 0; i < zombieList.size(); i++){
			for(int j = 0; j < bulletList.size(); j++){
				if(zombieList.get(i).checkHit(bulletList.get(j))){
					bulletList.remove(bulletList.get(j));
				}
			}
		}
	}
}
