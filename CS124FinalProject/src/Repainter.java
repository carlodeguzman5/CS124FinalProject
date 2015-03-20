
public class Repainter implements Runnable{
	GameEngine gc;
	public Repainter(GameEngine c){
		gc = c;
	}
	@Override
	public void run() {
		while(true){
			gc.repaint();
			gc.revalidate();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
