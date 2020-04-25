import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Generator extends JFrame implements ActionListener, KeyListener {
	Font fontHeader = new Font("Segoe UI", Font.PLAIN, 26);
	ImageIcon imgQR = new ImageIcon("src/GenQR/NoImageFound.jpg");
	JPanel 	pnlNorth = new JPanel(new BorderLayout(5, 5)),
			pnlCenter = new JPanel(new BorderLayout(5, 5)),
			pnlInput = new JPanel(new BorderLayout(5, 5));
	JLabel	lblTitle = new JLabel("Generate QR Code"),
			lblGeneratedQR = new JLabel(imgQR);
	JButton btnBack = new JButton("Back"),
			btnGenerate = new JButton("Generate");
	JTextField txtInput = new JTextField();

	public Generator() {
		// TODO Auto-generated constructor stub
		lblTitle.setFont(fontHeader);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		setLayout(new BorderLayout(5, 5));
		pnlNorth.setBorder(new EmptyBorder(10, 10, 0, 10));
		pnlCenter.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		pnlNorth.add(btnBack, BorderLayout.WEST);
		pnlNorth.add(lblTitle, BorderLayout.CENTER);
		pnlCenter.add(pnlInput, BorderLayout.NORTH);
		pnlInput.add(txtInput, BorderLayout.CENTER);
		pnlInput.add(btnGenerate, BorderLayout.EAST);
		pnlCenter.add(lblGeneratedQR, BorderLayout.CENTER);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		
		setTitle("Generate QR");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		btnBack.addActionListener(this);
		btnGenerate.addActionListener(this);
		txtInput.addKeyListener(this);
	}
	
	public void generateQRCode() {
		try {
			String qrData = txtInput.getText();
			String filePath = "src/GenQR/GenerateQR.png";
			String charset = "UTF-8"; // atau "ISO-8895-1"
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(qrData.getBytes(charset), charset), 
					BarcodeFormat.QR_CODE, 500, 500, hintMap);
			MatrixToImageWriter.writeToFile(matrix, 
					filePath.substring(filePath.lastIndexOf('.') + 1), 
					new File(filePath));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		imgQR = new ImageIcon("src/GenQR/GenerateQR.png");
		Image imgQR1 = imgQR.getImage();
		Image imgQR2 = imgQR1.getScaledInstance(lblGeneratedQR.getWidth(), lblGeneratedQR.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgQR3 = new ImageIcon(imgQR2);
		lblGeneratedQR.setIcon(imgQR3);
		JOptionPane.showMessageDialog(this, "QR Code Generated!");
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10) {
			generateQRCode();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnBack) {
			new MainApp();
			setVisible(false);
		}
		if (arg0.getSource() == btnGenerate) {
			if (txtInput.getText().length() > 0) {
				generateQRCode();					
			} else {
				JOptionPane.showMessageDialog(this, "Content must be filled");
			}
		}
	}

}
