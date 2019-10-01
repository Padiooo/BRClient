package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ReadyPanel extends JPanel implements ActionListener {


	private Window win;
	
	public ReadyPanel(Window win) {
		this.win = win;
		JButton button = new JButton();
		button.setText("Ready !");
		button.addActionListener(this);
		
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ready");
		ConnectionHolder.send("READY");
		win.close();
	}
	
}
