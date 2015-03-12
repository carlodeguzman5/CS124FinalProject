
public class Repainter implements Runnable{
	GameEngine gc;
	public Repainter(GameEngine c){
		gc = c;
	}
	@Override
	public void run() {
		long start;
		while(true){
			start = System.currentTimeMillis();
			gc.repaint();
			gc.revalidate();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//while(System.currentTimeMillis() - start < 1000/17){}
		}
		
		
	}
	
}
