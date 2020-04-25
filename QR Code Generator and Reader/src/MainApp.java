import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainApp extends JFrame implements ActionListener {
	JPanel pnlNorth = new JPanel();
	JPanel pnlCenter = new JPanel(new GridLayout(2, 1, 5, 5));
	JLabel lblMenu = new JLabel("Main menu");
	Font fontHeader = new Font("Segoe UI", Font.PLAIN, 26);
	JButton btnGen = new JButton("Generate QR Code");
	JButton btnRead = new JButton("Decode QR Code");

	public MainApp() {
		// TODO Auto-generated constructor stub
		lblMenu.setFont(fontHeader);
		btnGen.setFont(fontHeader);
		btnRead.setFont(fontHeader);
		
		pnlNorth.add(lblMenu);
		pnlCenter.setBorder(new EmptyBorder(0, 10, 10, 10));
		pnlCenter.add(btnGen);
		pnlCenter.add(btnRead);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		
		setTitle("QR Code Tools");
		setSize(375, 194);
	//	pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	//	System.out.println(this.getSize());
		btnGen.addActionListener(this);
		btnRead.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainApp();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnGen) {
			new GeneratorSelect();
			setVisible(false);			
		}
		if (arg0.getSource() == btnRead) {
		//	JOptionPane.showMessageDialog(this, "Feature Under Development");
			new Read();
			setVisible(false);
		}
	}

}
