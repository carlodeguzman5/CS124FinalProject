import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class InfoFrame extends JFrame {
	JLabel lblStageNum, lblZombieCount;	
	GameEngine engine;
	JProgressBar progressBar;
	public InfoFrame(GameEngine ge) {
		setBounds(800, 20, 300, 600);
		setAlwaysOnTop(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		engine = ge;
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 274, 150);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStage = new JLabel("Stage:");
		lblStage.setBounds(10, 11, 61, 14);
		panel.add(lblStage);
		
		lblStageNum = new JLabel("1");
		lblStageNum.setBounds(118, 11, 77, 14);
		panel.add(lblStageNum);
		
		JLabel lblHealth = new JLabel("Health");
		lblHealth.setBounds(10, 36, 46, 14);
		panel.add(lblHealth);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(100);
		progressBar.setValue(100);
		progressBar.setBounds(118, 36, 146, 14);
		panel.add(progressBar);
		
		JLabel lblZombiesLeft = new JLabel("Zombies Left:");
		lblZombiesLeft.setBounds(10, 61, 84, 14);
		panel.add(lblZombiesLeft);
		
		lblZombieCount = new JLabel("<number>");
		lblZombieCount.setBounds(118, 61, 77, 14);
		panel.add(lblZombieCount);
	}
	
	public JLabel getLblStageNum() {
		return lblStageNum;
	}
	public void increaseStage() {
		int x = Integer.parseInt(lblStageNum.getText());
		lblStageNum.setText((x++) + "");
	}
	public JLabel getZombieCount() {
		return lblZombieCount;
	}
	public void setZombieCount(int x) {
		lblZombieCount.setText(x + "");
	}
	
}
