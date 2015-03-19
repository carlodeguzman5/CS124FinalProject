import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame implements KeyListener, MouseMotionListener, MouseListener{

	private JPanel contentPane;
	private GameEngine engine;

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public MainWindow() throws FileNotFoundException {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		engine = new GameEngine();
		engine.setBounds(0,0,getWidth(),getHeight()-30);
		contentPane.add(engine);
		
		

		engine.addKeyListener(this);
		engine.addMouseMotionListener(this);
		engine.addMouseListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			engine.rad += Math.PI/12;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			engine.rad -= Math.PI/12;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		engine.mouseX =  me.getPoint().getX() - engine.getWidth()/2 + engine.character.getSize()/2;
		engine.mouseY =  me.getPoint().getY() - engine.getHeight()/2 + engine.character.getSize()/2;
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		engine.mouseX =  me.getPoint().getX() - engine.getWidth()/2 + engine.character.getSize()/2;
		engine.mouseY =  me.getPoint().getY() - engine.getHeight()/2 + engine.character.getSize()/2;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		engine.character.attack();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
