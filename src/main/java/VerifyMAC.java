import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class VerifyMAC extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldString;
	private JPasswordField passwordFieldpassword;
	private JTextField textFieldResult;
	private JTextField textFieldmac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerifyMAC frame = new VerifyMAC();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerifyMAC() {
		VerifyMAC verifyMAC=this;
		setTitle("VerifyMAC");
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerifymac = new JLabel("VerifyMAC");
		lblVerifymac.setForeground(new Color(0, 191, 255));
		lblVerifymac.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
		lblVerifymac.setBounds(203, 0, 199, 59);
		contentPane.add(lblVerifymac);
		
		JLabel lblmac = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u6821\u9A8CMAC\u7801\u7684\u5B57\u7B26\u4E32\uFF1A");
		lblmac.setForeground(new Color(0, 139, 139));
		lblmac.setFont(new Font("¡• È", Font.PLAIN, 26));
		lblmac.setBounds(21, 55, 436, 46);
		contentPane.add(lblmac);
		
		textFieldString = new JTextField();
		textFieldString.setColumns(10);
		textFieldString.setBounds(21, 100, 593, 30);
		contentPane.add(textFieldString);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_1.setForeground(new Color(0, 139, 139));
		label_1.setFont(new Font("¡• È", Font.PLAIN, 26));
		label_1.setBounds(35, 229, 367, 46);
		contentPane.add(label_1);
		
		passwordFieldpassword = new JPasswordField();
		passwordFieldpassword.setBounds(35, 275, 372, 30);
		contentPane.add(passwordFieldpassword);
		
		JLabel label = new JLabel("\u6821\u9A8C\u7ED3\u679C\uFF1A");
		label.setForeground(new Color(0, 139, 139));
		label.setFont(new Font("¡• È", Font.PLAIN, 26));
		label.setBounds(38, 444, 367, 46);
		contentPane.add(label);
		
		textFieldResult = new JTextField();
		textFieldResult.setFont(new Font("¡• È", Font.PLAIN, 24));
		textFieldResult.setBackground(new Color(250, 240, 230));
		textFieldResult.setColumns(10);
		textFieldResult.setBounds(34, 491, 217, 37);
		contentPane.add(textFieldResult);
		
		
		
		JButton buttonBack = new JButton("\u8FD4\u56DE");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifyMAC.dispose();
			}
		});
		buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonBack.setBackground(new Color(220, 220, 220));
		buttonBack.setForeground(new Color(0, 128, 128));
		buttonBack.setFont(new Font("¡• È", Font.PLAIN, 24));
		buttonBack.setBounds(452, 568, 124, 38);
		contentPane.add(buttonBack);
		
		JCheckBox checkBoxHmacMD4 = new JCheckBox("HmacMD4");
		JCheckBox checkBoxHmacMD2 = new JCheckBox("HmacMD2");
		JCheckBox checkBoxHmacSHA256 = new JCheckBox("HmacSHA256");
		JCheckBox checkBoxHmacMD128 = new JCheckBox("HmacRipeMD128");
		JCheckBox checkBoxHmacSHA1 = new JCheckBox("HmacSHA1");
		JCheckBox checkBoxHmacMD5 = new JCheckBox("HmacMD5");
		
		checkBoxHmacMD5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHmacMD5.isSelected())
				{
					checkBoxHmacMD2.setSelected(false);
					checkBoxHmacMD4.setSelected(false);
					checkBoxHmacMD128.setSelected(false);
					checkBoxHmacSHA1.setSelected(false);
					checkBoxHmacSHA256.setSelected(false);
				}
			}
		});
		checkBoxHmacMD5.setSelected(true);
		checkBoxHmacMD5.setForeground(new Color(178, 34, 34));
		checkBoxHmacMD5.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		checkBoxHmacMD5.setBounds(43, 356, 146, 31);
		contentPane.add(checkBoxHmacMD5);
		
		JLabel label_2 = new JLabel("\u8BF7\u9009\u62E9\u91C7\u7528\u7684\u7B97\u6CD5\uFF1A");
		label_2.setForeground(new Color(0, 139, 139));
		label_2.setFont(new Font("¡• È", Font.PLAIN, 26));
		label_2.setBounds(31, 304, 367, 46);
		contentPane.add(label_2);
		
		
		checkBoxHmacSHA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHmacSHA1.isSelected())
				{
					checkBoxHmacMD2.setSelected(false);
					checkBoxHmacMD4.setSelected(false);
					checkBoxHmacMD128.setSelected(false);
					checkBoxHmacMD5.setSelected(false);
					checkBoxHmacSHA256.setSelected(false);
				}
			}
		});
		checkBoxHmacSHA1.setForeground(new Color(255, 165, 0));
		checkBoxHmacSHA1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		checkBoxHmacSHA1.setBounds(230, 356, 146, 31);
		contentPane.add(checkBoxHmacSHA1);
		
		checkBoxHmacSHA256.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHmacSHA256.isSelected())
				{
					checkBoxHmacMD2.setSelected(false);
					checkBoxHmacMD4.setSelected(false);
					checkBoxHmacMD128.setSelected(false);
					checkBoxHmacSHA1.setSelected(false);
					checkBoxHmacMD5.setSelected(false);
				}
			}
		});
		checkBoxHmacSHA256.setForeground(new Color(123, 104, 238));
		checkBoxHmacSHA256.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		checkBoxHmacSHA256.setBounds(412, 356, 187, 31);
		contentPane.add(checkBoxHmacSHA256);
		
		checkBoxHmacMD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHmacMD2.isSelected())
				{
					checkBoxHmacMD5.setSelected(false);
					checkBoxHmacMD4.setSelected(false);
					checkBoxHmacMD128.setSelected(false);
					checkBoxHmacSHA1.setSelected(false);
					checkBoxHmacSHA256.setSelected(false);
				}
			}
		});
		checkBoxHmacMD2.setForeground(new Color(255, 99, 71));
		checkBoxHmacMD2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		checkBoxHmacMD2.setBounds(42, 403, 146, 31);
		contentPane.add(checkBoxHmacMD2);
		
		checkBoxHmacMD4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHmacMD4.isSelected())
				{
					checkBoxHmacMD2.setSelected(false);
					checkBoxHmacMD5.setSelected(false);
					checkBoxHmacMD128.setSelected(false);
					checkBoxHmacSHA1.setSelected(false);
					checkBoxHmacSHA256.setSelected(false);
				}
			}
		});
		checkBoxHmacMD4.setForeground(new Color(46, 139, 87));
		checkBoxHmacMD4.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		checkBoxHmacMD4.setBounds(228, 403, 146, 31);
		contentPane.add(checkBoxHmacMD4);
		
		checkBoxHmacMD128.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHmacMD128.isSelected())
				{
					checkBoxHmacMD2.setSelected(false);
					checkBoxHmacMD4.setSelected(false);
					checkBoxHmacMD5.setSelected(false);
					checkBoxHmacSHA1.setSelected(false);
					checkBoxHmacSHA256.setSelected(false);
				}
			}
		});
		checkBoxHmacMD128.setForeground(new Color(135, 206, 235));
		checkBoxHmacMD128.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		checkBoxHmacMD128.setBounds(412, 403, 189, 31);
		contentPane.add(checkBoxHmacMD128);
		
		JButton buttonVerify = new JButton("\u6821\u9A8C");
		buttonVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					String string=textFieldString.getText();
					String password=new String(passwordFieldpassword.getPassword());
					MessageDigest md;
					md = MessageDigest.getInstance("SHA3-256");
					byte[] hashvalue=md.digest(password.getBytes());
					
					byte[] macValue=null;
					String macstr=null;
					String macstring=textFieldmac.getText();
					if(checkBoxHmacMD2.isSelected())
					{
						SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacMD2");
						
						Mac mac=Mac.getInstance("HmacMD2");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
					    macstr=Hex.toHexString(macValue).toUpperCase();
					}
					
					if(checkBoxHmacMD4.isSelected())
					{
					 SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacMD4");
						
						Mac mac=Mac.getInstance("HmacMD4");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
					    macstr=Hex.toHexString(macValue).toUpperCase();
					}
					if(checkBoxHmacMD5.isSelected())
					{
					   SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacMD5");
						
						Mac mac=Mac.getInstance("HmacMD5");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
					    macstr=Hex.toHexString(macValue).toUpperCase();
					}
					if(checkBoxHmacMD128.isSelected())
					{
					   SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacRipeMD128");
						
						Mac mac=Mac.getInstance("HmacRipeMD128");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
					    macstr=Hex.toHexString(macValue).toUpperCase();
					}
					if(checkBoxHmacSHA1.isSelected())
					{
						 SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacSHA1");
							
							Mac mac=Mac.getInstance("HmacSHA1");
							
							mac.init(keySpec);
							
							macValue=mac.doFinal(string.getBytes());
						    macstr=Hex.toHexString(macValue).toUpperCase();
					}
					if(checkBoxHmacSHA256.isSelected())
					{
						 SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacSHA256");
							
							Mac mac=Mac.getInstance("HmacSHA256");
							
							mac.init(keySpec);
							
							macValue=mac.doFinal(string.getBytes());
						    macstr=Hex.toHexString(macValue).toUpperCase();
					}
					
					if(macstr.equals(macstring)){
						textFieldResult.setText("–£—ÈÕ®π˝£°");
					}
					else
					{
						textFieldResult.setText("–£—È≤ªÕ®π˝£°");
					}
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "À„∑®√˚≥ˆ¥Ì£°");
					e1.printStackTrace();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonVerify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonVerify.setBackground(new Color(220, 220, 220));
		buttonVerify.setForeground(new Color(0, 128, 128));
		buttonVerify.setFont(new Font("¡• È", Font.PLAIN, 30));
		buttonVerify.setBounds(100, 559, 151, 53);
		contentPane.add(buttonVerify);
		
		JLabel lblmac_1 = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u6821\u9A8C\u7684MAC\u7801\uFF1A");
		lblmac_1.setForeground(new Color(0, 139, 139));
		lblmac_1.setFont(new Font("¡• È", Font.PLAIN, 26));
		lblmac_1.setBounds(21, 140, 436, 46);
		contentPane.add(lblmac_1);
		
		textFieldmac = new JTextField();
		textFieldmac.setColumns(10);
		textFieldmac.setBounds(21, 189, 593, 30);
		contentPane.add(textFieldmac);
	}

}
