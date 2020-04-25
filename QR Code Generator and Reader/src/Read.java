import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Read extends JFrame implements ActionListener{
	Font fontHeader = new Font("Segoe UI", Font.PLAIN, 26);
	ImageIcon imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
	JPanel 	pnlNorth = new JPanel(new BorderLayout(5, 5)),
			pnlCenter = new JPanel(new BorderLayout(5, 5)),
			pnlRead = new JPanel(new BorderLayout(5, 5)),
			pnlExtraction = new JPanel(new BorderLayout(5, 5)),
			pnlQRText = new JPanel(new BorderLayout(5, 5));
	JButton	btnBack = new JButton("Back"),
			btnChoose = new JButton("Choose File"),
			btnRead = new JButton("Read QR");
	JTextField	txtFileName = new JTextField();
	JLabel	lblTitle = new JLabel("QR Code Decoder"),
			lblQR = new JLabel(imgQR);
	JTextArea txtQRContent = new JTextArea();
	JScrollPane scp = new JScrollPane(txtQRContent);
	JFileChooser fileChooser;
	String filePath;

	public Read() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(5, 5));
		lblTitle.setFont(fontHeader);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNorth.add(btnBack, BorderLayout.WEST);
		pnlNorth.add(lblTitle, BorderLayout.CENTER);
		pnlCenter.add(pnlRead, BorderLayout.NORTH);
		pnlCenter.add(pnlExtraction, BorderLayout.CENTER);
		pnlExtraction.add(lblQR, BorderLayout.WEST);
		pnlExtraction.add(pnlQRText, BorderLayout.CENTER);
		pnlQRText.add(btnRead, BorderLayout.NORTH);
		pnlQRText.add(scp, BorderLayout.CENTER);
		pnlRead.add(txtFileName, BorderLayout.CENTER);
		pnlRead.add(btnChoose, BorderLayout.EAST);
		
		txtFileName.setText("No file choosen");
		txtFileName.setEnabled(false);
		pnlNorth.setBorder(new EmptyBorder(10, 10, 0, 10));
		pnlCenter.setBorder(new EmptyBorder(0, 10, 10, 10));
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		
		setTitle("Decode QR");
		setSize(450, 311);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		System.out.println(this.getSize());
		btnBack.addActionListener(this);
		btnChoose.addActionListener(this);
		btnRead.addActionListener(this);
	}
	
	public String decodeQRCode(File qrCodeImage) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		try {
			Result result = new MultiFormatReader().decode(bitmap);
			return result.getText();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No QR Code");
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnBack) {
			new MainApp();
			setVisible(false);
		}
		if (arg0.getSource() == btnChoose) {
			fileChooser = new JFileChooser();
			int i = fileChooser.showOpenDialog(this);
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
				filePath = f.getPath();
				txtFileName.setText(f.getName());
				imgQR = new ImageIcon(filePath);
				Image img1 = imgQR.getImage();
				Image img2 = img1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
				ImageIcon img3 = new ImageIcon(img2);
				lblQR.setIcon(img3);
			}
		}
		if (arg0.getSource() == btnRead) {
			try {
				File file = new File(filePath);
				String decodedText = decodeQRCode(file);
				if (decodedText == null) {
					System.out.println("No QR Code found in the image");
					JOptionPane.showMessageDialog(this, "No QR Code found in the image!");
				} else {
					System.out.println("Decoded text = " + decodedText);
					txtQRContent.setText(decodedText);
					JOptionPane.showMessageDialog(this, "QR Code decoded successfully");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
				JOptionPane.showMessageDialog(this, "Could not decode QR Code!");
				imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
				Image img1 = imgQR.getImage();
				Image img2 = img1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
				ImageIcon img3 = new ImageIcon(img2);
				lblQR.setIcon(img3);
			}
		}
	}

}
