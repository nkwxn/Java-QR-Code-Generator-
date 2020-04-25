import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GeneratorSelect extends JFrame implements ActionListener {
	Font fontHeader = new Font("Segoe UI", Font.PLAIN, 26);
	JPanel 	pnlNorth = new JPanel(new BorderLayout(5, 5)),
			pnlCenter = new JPanel(new BorderLayout(5, 5));
	JLabel	lblTitle = new JLabel("Select content:");
	JButton	btnBack = new JButton("Back"),
			btnContinue = new JButton("Continue");
	JComboBox<String> cbContents = new JComboBox<String>();

	public GeneratorSelect() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(5, 5));
		
		String[] strCbContents = {"Select content to generate", "Text / URL", "Wifi network", "Contact Info"};
		for (int i = 0; i < strCbContents.length; i++) {
			cbContents.addItem(strCbContents[i]);
		}
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		pnlNorth.setBorder(new EmptyBorder(10, 10, 0, 10));
		pnlCenter.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		lblTitle.setFont(fontHeader);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNorth.add(btnBack, BorderLayout.WEST);
		pnlNorth.add(lblTitle, BorderLayout.CENTER);
		pnlCenter.add(cbContents, BorderLayout.CENTER);
		pnlCenter.add(btnContinue, BorderLayout.EAST);
		
		setTitle("Generate QR");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		btnBack.addActionListener(this);
		btnContinue.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnBack) {
			new MainApp();
			setVisible(false);
		}
		if (arg0.getSource() == btnContinue) {
			if (cbContents.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Content must be selected");
			} else {
				new GenSelected(cbContents.getSelectedItem().toString());
				setVisible(false);
			}
		}
	}

}
