import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Cursor;

public class EnCryptoFile extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFilePath;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldAgain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnCryptoFile frame = new EnCryptoFile();
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
	public EnCryptoFile() {
		EnCryptoFile enCryptoFile=this;
		setResizable(false);
		setTitle("EnCryptoFile");
		setBackground(new Color(30, 144, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u52A0\u5BC6\u6587\u4EF6\uFF1A");
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setFont(new Font("隶书", Font.PLAIN, 20));
		lblNewLabel.setBounds(34, 75, 167, 24);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A\uFF08\u5BC6\u7801\u4E3A6-16\u4F4D\u82F1\u6587\u5B57\u6BCD\u3001\u6570\u5B57\u7EC4\u5408\uFF09");
		label.setForeground(new Color(70, 130, 180));
		label.setFont(new Font("隶书", Font.PLAIN, 20));
		label.setBounds(34, 314, 471, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u518D\u6B21\u8F93\u5165\u5BC6\u7801:");
		label_1.setForeground(new Color(70, 130, 180));
		label_1.setFont(new Font("隶书", Font.PLAIN, 20));
		label_1.setBounds(34, 391, 150, 24);
		contentPane.add(label_1);
		
		JLabel lblaes = new JLabel("\u8BF7\u9009\u62E9\u52A0\u5BC6\u5F3A\u5EA6\uFF1A(\u5BF9\u5E94AES\u52A0\u5BC6)");
		lblaes.setForeground(new Color(70, 130, 180));
		lblaes.setFont(new Font("隶书", Font.PLAIN, 20));
		lblaes.setBounds(35, 210, 308, 24);
		contentPane.add(lblaes);
		
		textFieldFilePath = new JTextField();
		textFieldFilePath.setEditable(false);
		textFieldFilePath.setBounds(34, 109, 286, 31);
		contentPane.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);
		
		JButton btnFilePath = new JButton("...");
		btnFilePath.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser  =new JFileChooser("测试\\加密测试");
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
					
				}
			}
		});
		btnFilePath.setBackground(new Color(245, 245, 245));
		btnFilePath.setBounds(377, 109, 93, 31);
		contentPane.add(btnFilePath);
		
		JSlider slider = new JSlider();
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(30);
		slider.setValue(30);
		slider.setMaximum(90);
		slider.setBounds(57, 233, 249, 37);
		contentPane.add(slider);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E00\u822C\uFF08128bit\uFF09");
		lblNewLabel_1.setForeground(new Color(102, 205, 170));
		lblNewLabel_1.setFont(new Font("华文隶书", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(67, 280, 92, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5F3A(192bit)");
		lblNewLabel_2.setForeground(new Color(102, 205, 170));
		lblNewLabel_2.setFont(new Font("华文隶书", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(170, 280, 70, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5F88\u5F3A\uFF08256bit\uFF09");
		lblNewLabel_3.setForeground(new Color(102, 205, 170));
		lblNewLabel_3.setFont(new Font("华文隶书", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(250, 280, 93, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel label_2 = new JLabel("\u8BF7\u9009\u62E9\u52A0\u5BC6\u7B97\u6CD5\uFF1A\uFF08\u53EA\u80FD\u9009\u4E00\u79CD\uFF09");
		label_2.setForeground(new Color(70, 130, 180));
		label_2.setFont(new Font("隶书", Font.PLAIN, 20));
		label_2.setBounds(34, 160, 309, 24);
		contentPane.add(label_2);
		
		JRadioButton rdbtndes = new JRadioButton("3DES");
		rdbtndes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnAes = new JRadioButton("AES");
		rdbtnAes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnAes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAes.isSelected()) {
					rdbtndes.setSelected(false);
					slider.setEnabled(true);
				}
			}
		});
		rdbtnAes.setSelected(true);
		rdbtnAes.setForeground(new Color(119, 136, 153));
		rdbtnAes.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnAes.setBounds(346, 163, 87, 21);
		contentPane.add(rdbtnAes);
		
        
		rdbtndes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtndes.isSelected()) {
					rdbtnAes.setSelected(false);
					slider.setEnabled(false);
				}
			}
		});
		rdbtndes.setForeground(new Color(0, 128, 128));
		rdbtndes.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtndes.setBounds(435, 161, 100, 24);
		contentPane.add(rdbtndes);
		
		JButton button_1 = new JButton("\u5F00\u59CB\u52A0\u5BC6");
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setBackground(new Color(220, 220, 220));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                       //加密方法
				String fileName=textFieldFilePath.getText();
			    String decryptName=fileName;
					
			    char[] password1=passwordField.getPassword();
				char[] password2=passwordFieldAgain.getPassword();
					try {
						if(Arrays.equals(password1,password2))
{
						String password=new String(password1);
						String filepostfix=fileName.substring(fileName.length()-4,fileName.length());
						if(rdbtnAes.isSelected())
						{
						//创建密钥；
						MessageDigest md;
						md = MessageDigest.getInstance("SHA3-256");
						byte[] hashvalue=md.digest(password.getBytes());
						
						System.out.println(new HexBinaryAdapter().marshal(password.getBytes()));
						System.out.println(new HexBinaryAdapter().marshal(hashvalue));
						SecretKeySpec key256=new SecretKeySpec(hashvalue, 0,32,"AES");  //生成三个不同长度的密钥对象
						SecretKeySpec key192=new SecretKeySpec(hashvalue, 0,24,"AES");
						SecretKeySpec key128=new SecretKeySpec(hashvalue, 0,16,"AES");
						
						
						//创建ＩＶ
						byte[] ivvalue=new byte[16];
						Random random =new Random(System.currentTimeMillis());  //调用随机数对象生成iv，随机数种子用系统时间
						random.nextBytes(ivvalue);
						IvParameterSpec iv=new IvParameterSpec(ivvalue);
						
					
						
						
						//加密
						Cipher cipher=Cipher.getInstance("AES/OFB/PKCS5Padding");
						
						if(slider.getValue()==30)                      //判断加密强度用不同长度密钥生成加密对象
						{
							decryptName=decryptName+"密文AES(一般)enc."+filepostfix;
							cipher.init(Cipher.ENCRYPT_MODE, key128,iv);
						}
						
						if(slider.getValue()==60)
						{
							decryptName=decryptName+"密文AES(强)enc."+filepostfix;
							cipher.init(Cipher.ENCRYPT_MODE, key192,iv);
						}
						
						if(slider.getValue()==90)
						{
							decryptName=decryptName+"密文AES(很强)enc."+filepostfix;
							cipher.init(Cipher.ENCRYPT_MODE, key256,iv);
						}
						//文件输入输出流
						FileInputStream fis =new FileInputStream(fileName);
						FileOutputStream fos=new FileOutputStream(decryptName);
						
						//文件头
						fos.write("suguoyuCrypt".getBytes());
						fos.write(ivvalue);
						System.out.println(new HexBinaryAdapter().marshal(ivvalue));
						
						//创建加解密输入流
						CipherInputStream cis=new CipherInputStream(fis, cipher);
						byte[] buffer=new byte[1024];
						int n=0;
						
						
						while((n=cis.read(buffer))!=-1)
						{
							fos.write(buffer,0,n);
						}
						fos.close();
						cis.close();
						JOptionPane.showMessageDialog(null, "加密成功！(加密文件存放于原文件目录下)");
						} 
						
						//3DES加密
						if(rdbtndes.isSelected())
			     {
			    	    //创建密钥；
						MessageDigest md;
						md = MessageDigest.getInstance("SHA3-256");
						byte[] hashvalue=md.digest(password.getBytes());
						
						SecretKeySpec key3DES=new SecretKeySpec(hashvalue, 0,24,"DESede");  //生成密钥对象
						
						
						//创建ＩＶ
						byte[] ivvalue=new byte[8];                              //和明文分组长度相同
						Random random =new Random(System.currentTimeMillis());  //调用随机数对象生成iv，随机数种子用系统时间
						random.nextBytes(ivvalue);
						IvParameterSpec iv=new IvParameterSpec(ivvalue);
						
						
						//加密对象
						Cipher cipher=Cipher.getInstance("DESede/CBC/PKCS5Padding");
						cipher.init(Cipher.ENCRYPT_MODE, key3DES,iv);  
						decryptName=decryptName+"密文3DES."+filepostfix;			
						//文件输入输出流
						FileInputStream fis =new FileInputStream(fileName);
						FileOutputStream fos=new FileOutputStream(decryptName);
						
						//文件头
						fos.write("suguoyuCrypt".getBytes());
						fos.write(ivvalue);
						
						//创建加解密输入流
						CipherInputStream cis=new CipherInputStream(fis, cipher);
						byte[] buffer=new byte[4096];
						int n=0;
						while((n=cis.read(buffer))!=-1)
						{
							fos.write(buffer,0,n);
						}
						fos.close();
						cis.close();
						JOptionPane.showMessageDialog(null, "加密成功！(加密文件存放于原文件目录下)");
						
						
			     }
						
} else {
						JOptionPane.showMessageDialog(null, "两次输入的口令不一致，请重新输入");
}
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "请检查文件输入路径！");
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "摘要算法出错！");
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidAlgorithmParameterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "文件加密出错，输出错误！");
					}
								
			}
		});
		button_1.setForeground(new Color(100, 149, 237));
		button_1.setFont(new Font("隶书", Font.PLAIN, 26));
		button_1.setBounds(109, 508, 174, 37);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(34, 348, 286, 31);
		contentPane.add(passwordField);
		
		passwordFieldAgain = new JPasswordField();
		passwordFieldAgain.setBounds(34, 425, 286, 31);
		contentPane.add(passwordFieldAgain);
		
		JLabel lblEncryption = new JLabel("EnCryption");
		lblEncryption.setForeground(new Color(0, 128, 128));
		lblEncryption.setFont(new Font("Vladimir Script", Font.BOLD, 28));
		lblEncryption.setBounds(188, 23, 155, 42);
		contentPane.add(lblEncryption);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enCryptoFile.dispose();
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBackground(SystemColor.control);
		button.setForeground(SystemColor.activeCaption);
		button.setFont(new Font("隶书", Font.PLAIN, 20));
		button.setBounds(396, 514, 108, 31);
		contentPane.add(button);
	}
}
