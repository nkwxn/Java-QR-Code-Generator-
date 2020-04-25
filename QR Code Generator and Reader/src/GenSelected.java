import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class GenSelected extends JFrame implements ActionListener {
	Font fontHeader = new Font("Segoe UI", Font.PLAIN, 26);
	JPanel	pnlNorth = new JPanel(new BorderLayout(5, 5)),
			pnlCenter = new JPanel(new BorderLayout(5, 5)),
			pnlGenText = new JPanel(new BorderLayout(5, 5)),
			pnlNames = new JPanel(new GridLayout(1, 2)),
			pnlCompany = new JPanel(new GridLayout(1, 2)),
			pnlCity = new JPanel(new GridLayout(1, 2)),
			pnlTexts;
	ImageIcon imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
	JButton	btnBack = new JButton("Back"),
			btnGenerate = new JButton("Generate");
	JLabel 	lblGenerate = new JLabel(),
			lblQR = new JLabel(imgQR),
			lblGenText = new JLabel("Text Content / URL"),
			lblSSID = new JLabel("SSID"),
			lblPwd = new JLabel("Password"),
			lblNetType = new JLabel("Network Type"),
			lblHidden = new JLabel("Hidden?"),
			lblName = new JLabel("Name (last, first)"),
			lblPhoneNumbers = new JLabel("Phone Number"),
			lblEmail = new JLabel("Email"),
			lblCompany = new JLabel("Company, Title"),
			lblStreet = new JLabel("Street"),
			lblCity = new JLabel("City, Postal Code"),
			lblState = new JLabel("State / Province"),
			lblCountry = new JLabel("Country"),
			lblWebsite = new JLabel("Website");
	JTextArea txtGenText = new JTextArea(5, 20);
	JScrollPane scp = new JScrollPane(txtGenText);
	JTextField	txtSSID = new JTextField(),
				txtLastName = new JTextField(),
				txtFirstName = new JTextField(),
				txtPhoneNumber = new JTextField(),
				txtEmail = new JTextField(),
				txtCompany = new JTextField(),
				txtTitle = new JTextField(),
				txtStreet = new JTextField(),
				txtCity = new JTextField(),
				txtPostalCode = new JTextField(),
				txtState = new JTextField(),
				txtCountry = new JTextField(),
				txtWebsite = new JTextField();
	JPasswordField txtpwd = new JPasswordField();
	JComboBox<String> cbType;
	JCheckBox chkHidden = new JCheckBox();
	String content;

	public GenSelected(String content) {
		// TODO Auto-generated constructor stub
		this.content = content;
		lblGenerate.setText("Generate QR Code from " + content + "");
		lblGenerate.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenerate.setFont(fontHeader);
		
		switch (content) {
		case "Text / URL":
			pnlTexts = new JPanel(new BorderLayout(5, 5));
			pnlTexts.add(lblGenText, BorderLayout.NORTH);
			pnlTexts.add(scp, BorderLayout.CENTER);
			break;
		case "Wifi network":
			pnlTexts = new JPanel(new GridLayout(4, 2, 5, 5));
			lblSSID.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPwd.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNetType.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHidden.setHorizontalAlignment(SwingConstants.RIGHT);
			String[] netType = {"WEP", "WPA/WPA2", "No encryption"};
			cbType = new JComboBox<String>(netType);
			pnlTexts.add(lblSSID);
			pnlTexts.add(txtSSID);
			pnlTexts.add(lblPwd);
			pnlTexts.add(txtpwd);
			pnlTexts.add(lblNetType);
			pnlTexts.add(cbType);
			pnlTexts.add(lblHidden);
			pnlTexts.add(chkHidden);
			break;
		case "Contact Info":
			pnlNames.add(txtLastName);
			pnlNames.add(txtFirstName);
			pnlCompany.add(txtCompany);
			pnlCompany.add(txtTitle);
			pnlCity.add(txtCity);
			pnlCity.add(txtPostalCode);
			pnlTexts = new JPanel(new GridLayout(9, 2, 5, 0));
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPhoneNumbers.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCompany.setHorizontalAlignment(SwingConstants.RIGHT);
			lblStreet.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
			lblState.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCountry.setHorizontalAlignment(SwingConstants.RIGHT);
			lblWebsite.setHorizontalAlignment(SwingConstants.RIGHT);
			pnlTexts.add(lblName);
			pnlTexts.add(pnlNames);
			pnlTexts.add(lblPhoneNumbers);
			pnlTexts.add(txtPhoneNumber);
			pnlTexts.add(lblEmail);
			pnlTexts.add(txtEmail);
			pnlTexts.add(lblCompany);
			pnlTexts.add(pnlCompany);
			pnlTexts.add(lblStreet);
			pnlTexts.add(txtStreet);
			pnlTexts.add(lblCity);
			pnlTexts.add(pnlCity);
			pnlTexts.add(lblState);
			pnlTexts.add(txtState);
			pnlTexts.add(lblCountry);
			pnlTexts.add(txtCountry);
			pnlTexts.add(lblWebsite);
			pnlTexts.add(txtWebsite);
			break;

		default:
			break;
		}
		
		pnlGenText.add(pnlTexts, BorderLayout.CENTER);
		pnlGenText.add(btnGenerate, BorderLayout.SOUTH);	
		pnlNorth.add(btnBack, BorderLayout.WEST);
		pnlNorth.add(lblGenerate, BorderLayout.CENTER);
		pnlCenter.add(pnlGenText, BorderLayout.CENTER);
		pnlCenter.add(lblQR, BorderLayout.EAST);
		pnlNorth.setBorder(new EmptyBorder(10, 10, 0, 10));
		pnlCenter.setBorder(new EmptyBorder(0, 10, 10, 10));
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		
		setTitle("Generate QR");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		System.out.println(this.getSize());
		btnBack.addActionListener(this);
		btnGenerate.addActionListener(this);
	}
	
	public void generateQRCode(String qrData) {
		try {
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
		Image imgQR2 = imgQR1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
		ImageIcon imgQR3 = new ImageIcon(imgQR2);
		lblQR.setIcon(imgQR3);
		JOptionPane.showMessageDialog(this, "QR Code Generated!");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnBack) {
			new GeneratorSelect();
			setVisible(false);
		}
		if (arg0.getSource() == btnGenerate) {
			String qrData;
			switch (content) {
			case "Text / URL":
				if (txtGenText.getText().length() < 1) {
					JOptionPane.showMessageDialog(this, "Text must be at least 1 character");
					imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
					Image img1 = imgQR.getImage();
					Image img2 = img1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
					ImageIcon img3 = new ImageIcon(img2);
					lblQR.setIcon(img3);
				} else {
					generateQRCode(txtGenText.getText());
				}
				break;
			case "Wifi network":
				qrData = "WIFI:";
				if (txtSSID.getText().length() < 1) {
					JOptionPane.showMessageDialog(this, "SSID must be at least 1 character");
					imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
					Image img1 = imgQR.getImage();
					Image img2 = img1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
					ImageIcon img3 = new ImageIcon(img2);
					lblQR.setIcon(img3);
				} else {
					qrData += "S:" + txtSSID.getText() + ";";
					switch (cbType.getSelectedIndex()) {
					case 0:
						qrData += "T:WEP;";
						break;
					case 1:
						qrData += "T:WPA;";
						break;

					default:
						break;
					}
					String pwd =  txtpwd.getText().toString();
					qrData += "P:" + pwd + ";";
					if (chkHidden.isSelected()) {
						qrData += "H:true;";
					}
					qrData += ";";
					System.out.println(qrData);
					generateQRCode(qrData);
				}
				break;
			case "Contact Info":
				qrData = "BEGIN:VCARD\r\n" + 
						"VERSION:3.0\r\n";
				if (txtLastName.getText().length() < 1 && txtFirstName.getText().length() < 1) {
					JOptionPane.showMessageDialog(this, "Name must be filled!");
					imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
					Image img1 = imgQR.getImage();
					Image img2 = img1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
					ImageIcon img3 = new ImageIcon(img2);
					lblQR.setIcon(img3);
				} else {
					boolean phIsNumeric = true;
					for (int i = 0; i < txtPhoneNumber.getText().length(); i++) {
						if (Character.isLetter(txtPhoneNumber.getText().charAt(i))) {
							phIsNumeric = false;
						}
					}
					if (phIsNumeric) {
						qrData += "N:" + txtLastName.getText() + ";" + txtFirstName.getText() + "\r\n"
								+ "FN:" + txtFirstName.getText() + " " + txtLastName.getText() + "\r\n"
								+ "ORG:" + txtCompany.getText() + "\r\n" + "TITLE:" + txtTitle.getText() + "\r\n"
								+ "ADR:;;" + txtStreet.getText() + ";" + txtCity.getText() + ";" + txtState.getText()
								+ ";" + txtPostalCode.getText() + ";" + txtCountry.getText() + "\r\n"
								+ "TEL;CELL:" + txtPhoneNumber.getText() + "\r\n"
								+ "EMAIL;WORK;INTERNET:" + txtEmail.getText() + "\r\n"
								+ "URL:" + txtWebsite.getText() + "\r\n"
								+ "END:VCARD";
						System.out.println(qrData);
						generateQRCode(qrData);
					} else {
						JOptionPane.showMessageDialog(this, "Phone number must be a digit \r\n(can begin with +[country code] tho)");
						imgQR = new ImageIcon("src/GenQR/NoImageFound-s.jpg");
						Image img1 = imgQR.getImage();
						Image img2 = img1.getScaledInstance(lblQR.getWidth(), lblQR.getWidth(), Image.SCALE_SMOOTH);
						ImageIcon img3 = new ImageIcon(img2);
						lblQR.setIcon(img3);
					}
				}
				break;

			default:
				break;
			}
		}
	}

}
