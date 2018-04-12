import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class MACValue extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldString;
	private JPasswordField passwordFieldpassword;
	private JPasswordField passwordFieldpasswordAgain;
	private JTextField textFieldMACValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MACValue frame = new MACValue();
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
	public MACValue() {
		MACValue macValue=this;
		setTitle("MAC");
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMac = new JLabel("MAC");
		lblMac.setForeground(new Color(0, 191, 255));
		lblMac.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
		lblMac.setBounds(257, 10, 84, 59);
		contentPane.add(lblMac);
		
		JLabel lblmac = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u8BA1\u7B97MAC\u7801\u7684\u5B57\u7B26\u4E32\uFF1A");
		lblmac.setForeground(new Color(0, 139, 139));
		lblmac.setFont(new Font("¡• È", Font.PLAIN, 26));
		lblmac.setBounds(24, 68, 436, 46);
		contentPane.add(lblmac);
		
		textFieldString = new JTextField();
		textFieldString.setBounds(23, 115, 593, 30);
		contentPane.add(textFieldString);
		textFieldString.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u5BC6\u7801\uFF1A\uFF086-16\u4F4D\uFF09");
		label.setForeground(new Color(0, 139, 139));
		label.setFont(new Font("¡• È", Font.PLAIN, 26));
		label.setBounds(27, 156, 367, 46);
		contentPane.add(label);
		
		passwordFieldpassword = new JPasswordField();
		passwordFieldpassword.setBounds(29, 203, 372, 30);
		contentPane.add(passwordFieldpassword);
		
		JLabel label_1 = new JLabel("\u8BF7\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_1.setForeground(new Color(0, 139, 139));
		label_1.setFont(new Font("¡• È", Font.PLAIN, 26));
		label_1.setBounds(24, 243, 367, 46);
		contentPane.add(label_1);
		
		passwordFieldpasswordAgain = new JPasswordField();
		passwordFieldpasswordAgain.setBounds(26, 295, 372, 30);
		contentPane.add(passwordFieldpasswordAgain);
		
		JLabel label_2 = new JLabel("\u8BF7\u9009\u62E9\u91C7\u7528\u7684\u7B97\u6CD5\uFF1A");
		label_2.setForeground(new Color(0, 139, 139));
		label_2.setFont(new Font("¡• È", Font.PLAIN, 26));
		label_2.setBounds(21, 333, 367, 46);
		contentPane.add(label_2);
		
		
		JCheckBox chckbxHmacripeMD128 = new JCheckBox("HmacRipeMD128");
		JCheckBox chckbxHmacMD2 = new JCheckBox("HmacMD2");
		JCheckBox chckbxHmacMD4 = new JCheckBox("HmacMD4");
		JCheckBox chckbxHmacSHA256 = new JCheckBox("HmacSHA256");
		JCheckBox chckbxHmacSHA1 = new JCheckBox("HmacSHA1");
		JCheckBox chckbxHmacMD5 = new JCheckBox("HmacMD5");
		
		chckbxHmacMD5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHmacMD5.isSelected())
				{
					chckbxHmacMD2.setSelected(false);
					chckbxHmacMD4.setSelected(false);
					chckbxHmacripeMD128.setSelected(false);
					chckbxHmacSHA1.setSelected(false);
					chckbxHmacSHA256.setSelected(false);
				}
			}
		});
		chckbxHmacMD5.setForeground(new Color(178, 34, 34));
		chckbxHmacMD5.setSelected(true);
		chckbxHmacMD5.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		chckbxHmacMD5.setBounds(32, 390, 146, 31);
		contentPane.add(chckbxHmacMD5);
		
		chckbxHmacSHA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHmacSHA1.isSelected())
				{
					chckbxHmacMD2.setSelected(false);
					chckbxHmacMD4.setSelected(false);
					chckbxHmacripeMD128.setSelected(false);
					chckbxHmacMD5.setSelected(false);
					chckbxHmacSHA256.setSelected(false);
				}
			}
		});
		chckbxHmacSHA1.setForeground(new Color(255, 165, 0));
		chckbxHmacSHA1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		chckbxHmacSHA1.setBounds(217, 390, 146, 31);
		contentPane.add(chckbxHmacSHA1);
		
		chckbxHmacSHA256.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHmacSHA256.isSelected())
				{
					chckbxHmacMD2.setSelected(false);
					chckbxHmacMD4.setSelected(false);
					chckbxHmacripeMD128.setSelected(false);
					chckbxHmacSHA1.setSelected(false);
					chckbxHmacMD5.setSelected(false);
				}
			}
		});
		chckbxHmacSHA256.setForeground(new Color(123, 104, 238));
		chckbxHmacSHA256.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		chckbxHmacSHA256.setBounds(397, 390, 187, 31);
		contentPane.add(chckbxHmacSHA256);
		
		chckbxHmacMD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHmacMD2.isSelected())
				{
					chckbxHmacMD5.setSelected(false);
					chckbxHmacMD4.setSelected(false);
					chckbxHmacripeMD128.setSelected(false);
					chckbxHmacSHA1.setSelected(false);
					chckbxHmacSHA256.setSelected(false);
				}
			}
		});
		chckbxHmacMD2.setForeground(new Color(255, 99, 71));
		chckbxHmacMD2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		chckbxHmacMD2.setBounds(32, 444, 146, 31);
		contentPane.add(chckbxHmacMD2);
		
		chckbxHmacMD4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHmacMD4.isSelected())
				{
					chckbxHmacMD2.setSelected(false);
					chckbxHmacMD5.setSelected(false);
					chckbxHmacripeMD128.setSelected(false);
					chckbxHmacSHA1.setSelected(false);
					chckbxHmacSHA256.setSelected(false);
				}
			}
		});
		chckbxHmacMD4.setForeground(new Color(46, 139, 87));
		chckbxHmacMD4.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		chckbxHmacMD4.setBounds(217, 444, 146, 31);
		contentPane.add(chckbxHmacMD4);
		
		chckbxHmacripeMD128.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHmacripeMD128.isSelected())
				{
					chckbxHmacMD2.setSelected(false);
					chckbxHmacMD4.setSelected(false);
					chckbxHmacMD5.setSelected(false);
					chckbxHmacSHA1.setSelected(false);
					chckbxHmacSHA256.setSelected(false);
				}
			}
		});
		chckbxHmacripeMD128.setForeground(new Color(135, 206, 235));
		chckbxHmacripeMD128.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
		chckbxHmacripeMD128.setBounds(397, 444, 189, 31);
		contentPane.add(chckbxHmacripeMD128);
		
		JLabel lblmac_1 = new JLabel("\u8BE5\u5B57\u7B26\u4E32\u7684MAC\u7801\uFF1A");
		lblmac_1.setForeground(new Color(0, 139, 139));
		lblmac_1.setFont(new Font("¡• È", Font.PLAIN, 26));
		lblmac_1.setBounds(27, 481, 367, 46);
		contentPane.add(lblmac_1);
		
		textFieldMACValue = new JTextField();
		textFieldMACValue.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 10));
		textFieldMACValue.setBounds(23, 533, 593, 30);
		contentPane.add(textFieldMACValue);
		textFieldMACValue.setColumns(10);
		
		JButton buttonCalculate = new JButton("\u8BA1\u7B97");
		buttonCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		   char[] password1=passwordFieldpassword.getPassword();
		   char[] password2=passwordFieldpasswordAgain.getPassword();
		   String string=textFieldString.getText();
		   byte[] macValue=null;
		   
		   if(Arrays.equals(password1,password2))
		   {
			   try {
				   String password=new String(password1);
				   MessageDigest md;
					md = MessageDigest.getInstance("SHA3-256");
					byte[] hashvalue=md.digest(password.getBytes());     //ππΩ®√‹¬Î’™“™
					
					if(chckbxHmacMD2.isSelected())
					{
						SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacMD2");
						
						Mac mac=Mac.getInstance("HmacMD2");           //MAC∂‘œÛ
						
						mac.init(keySpec);                //√‹‘ø≥ı ºªØ
						
						macValue=mac.doFinal(string.getBytes()); //º∆À„mac¬Î÷µ
						textFieldMACValue.setText(Hex.toHexString(macValue).toUpperCase());
					}
					
					if(chckbxHmacMD4.isSelected())
					{
				      SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacMD4");
						
						Mac mac=Mac.getInstance("HmacMD4");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
						textFieldMACValue.setText(Hex.toHexString(macValue).toUpperCase());
					}
					if(chckbxHmacMD5.isSelected())
					{
				      SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacMD5");
						
						Mac mac=Mac.getInstance("HmacMD5");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
						textFieldMACValue.setText(Hex.toHexString(macValue).toUpperCase());
					}
					if(chckbxHmacripeMD128.isSelected())
					{
				       SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacRipeMD128");
						
						Mac mac=Mac.getInstance("HmacRipeMD128");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
						textFieldMACValue.setText(Hex.toHexString(macValue).toUpperCase());
					}
				    if(chckbxHmacSHA1.isSelected())
				    {
				      SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacSHA1");
						
						Mac mac=Mac.getInstance("HmacSHA1");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
						textFieldMACValue.setText(Hex.toHexString(macValue).toUpperCase());
				    }
				    if(chckbxHmacSHA256.isSelected())
				    {
				     SecretKeySpec keySpec=new SecretKeySpec(hashvalue, "HmacSHA256");
						
						Mac mac=Mac.getInstance("HmacSHA256");
						
						mac.init(keySpec);
						
						macValue=mac.doFinal(string.getBytes());
						textFieldMACValue.setText(Hex.toHexString(macValue).toUpperCase());
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
		   else{
				JOptionPane.showMessageDialog(null, "¡Ω¥Œ ‰»Îµƒ√‹¬Î≤ª“ª÷¬£¨«Î÷ÿ–¬ ‰»Î");
		   }
					
			}
		});
		buttonCalculate.setBackground(new Color(220, 220, 220));
		buttonCalculate.setForeground(new Color(0, 128, 128));
		buttonCalculate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCalculate.setFont(new Font("¡• È", Font.BOLD, 26));
		buttonCalculate.setBounds(54, 585, 155, 46);
		contentPane.add(buttonCalculate);
		
		JButton buttonBack = new JButton("\u8FD4\u56DE");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				macValue.dispose();
			}
		});
		buttonBack.setBackground(new Color(245, 245, 245));
		buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonBack.setForeground(new Color(95, 158, 160));
		buttonBack.setFont(new Font("¡• È", Font.PLAIN, 22));
		buttonBack.setBounds(369, 595, 117, 37);
		contentPane.add(buttonBack);
	}
}
