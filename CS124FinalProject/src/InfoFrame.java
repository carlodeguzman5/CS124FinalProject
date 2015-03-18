import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;

public class InfoFrame extends JFrame {
	JLabel lblStageNum, lblZombieCount;	
	JPanel upgradePanel;
	GameEngine engine;
	JProgressBar progressBar;
	/*
	 * Used for calculation of prices
	 */
	PriceVisitor visitor = new PriceVisitor();
	ArrayList<Upgrade> upList;
	ArrayList<JButton> btnList;
	public InfoFrame(GameEngine ge) {
		setBounds(800, 20, 300, 600);
		setAlwaysOnTop(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		upList = new ArrayList<Upgrade>();
		btnList = new ArrayList<JButton>();
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
		
		upgradePanel = new JPanel();
		upgradePanel.setBounds(10, 172, 274, 388);
		getContentPane().add(upgradePanel);
		upgradePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
	}
	
	public JLabel getLblStageNum() {
		return lblStageNum;
	}
	public void setStage(int x) {
		lblStageNum.setText(x + "");
	}
	public JLabel getZombieCount() {
		return lblZombieCount;
	}
	public void setZombieCount(int x) {
		lblZombieCount.setText(x + "");
	}
	public void decreaseZombieCount() {
		lblZombieCount.setText( (Integer.parseInt(lblZombieCount.getText())- 1)+"");
	}
	public void addUpgrade(Upgrade x){
		
		
		upList.add(x);
		for(Upgrade u :upList){
			btnList.add(new JButton("<html><center>"+u.getName()+"<br> " + visitor.visit(u) + "</center></html>"));
		}
		for(final JButton b : btnList){
			
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					upList.get(btnList.indexOf(b)).levelUp();
					b.setText("<html><center>"+upList.get(btnList.indexOf(b)).getName()+"<br> " + visitor.visit(upList.get(btnList.indexOf(b))) + "</center></html>");
				}
			});
			
			b.setSize(120, 60);
			upgradePanel.add(b);
		}
		upgradePanel.repaint();
	}
}
