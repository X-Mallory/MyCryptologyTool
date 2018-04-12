import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.SliderUI;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JPasswordField;
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
import java.awt.SystemColor;
import java.awt.Cursor;

public class DeCryptoFile extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFilePath;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeCryptoFile frame = new DeCryptoFile();
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
	public DeCryptoFile() {
		DeCryptoFile deCryptoFile=this;
		setResizable(false);
		setTitle("DeCryptoFile");
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 521);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u89E3\u5BC6\u6587\u4EF6\uFF1A");
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("隶书", Font.PLAIN, 24));
		label.setBounds(50, 74, 244, 39);
		contentPane.add(label);
		
		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(60, 123, 348, 31);
		contentPane.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);
		
		JButton btnFilePathButton = new JButton("...");
		btnFilePathButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilePathButton.setBackground(new Color(220, 220, 220));
		btnFilePathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser  =new JFileChooser("测试\\加密测试");
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
					
				}
			}
		});
		btnFilePathButton.setBounds(492, 122, 93, 30);
		contentPane.add(btnFilePathButton);
		
		JLabel label_1 = new JLabel("\u8BF7\u9009\u62E9\u52A0\u5BC6\u7B97\u6CD5\uFF1A");
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(new Font("隶书", Font.PLAIN, 24));
		label_1.setBounds(50, 169, 244, 39);
		contentPane.add(label_1);
		
		JSlider slider = new JSlider();
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtndes = new JRadioButton("3DES");
		rdbtndes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JRadioButton rdbtnAes = new JRadioButton("AES");
		rdbtnAes.setSelected(true);
		rdbtnAes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtndes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAes.isSelected())
				{
					rdbtndes.setSelected(false);
					slider.setEnabled(true);
				}
			}
		});
		rdbtnAes.setForeground(new Color(47, 79, 79));
		rdbtnAes.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnAes.setBounds(349, 183, 77, 23);
		contentPane.add(rdbtnAes);
		
		
		rdbtndes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtndes.isSelected())
				{
					rdbtnAes.setSelected(false);
					slider.setEnabled(false);
				}
			}
		});
		rdbtndes.setForeground(new Color(0, 128, 128));
		rdbtndes.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtndes.setBounds(454, 183, 77, 23);
		contentPane.add(rdbtndes);
		
		JLabel lblaes = new JLabel("\u52A0\u5BC6\u5F3A\u5EA6\uFF08\u5BF9\u5E94AES\uFF09\uFF1A");
		lblaes.setForeground(new Color(30, 144, 255));
		lblaes.setFont(new Font("隶书", Font.PLAIN, 22));
		lblaes.setBounds(50, 212, 273, 39);
		contentPane.add(lblaes);
		
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setValue(30);
		slider.setMajorTickSpacing(30);
		slider.setMaximum(90);
		slider.setBounds(331, 223, 254, 26);
		contentPane.add(slider);
		
		JLabel label_2 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_2.setForeground(new Color(30, 144, 255));
		label_2.setFont(new Font("隶书", Font.PLAIN, 24));
		label_2.setBounds(50, 285, 244, 39);
		contentPane.add(label_2);
		
		JLabel lblbit = new JLabel("\u4E00\u822C\uFF08128bit\uFF09");
		lblbit.setForeground(new Color(100, 149, 237));
		lblbit.setFont(new Font("华文隶书", Font.PLAIN, 12));
		lblbit.setBounds(341, 259, 84, 15);
		contentPane.add(lblbit);
		
		JLabel lblbit_1 = new JLabel("\u5F3A\uFF08192bit\uFF09");
		lblbit_1.setForeground(new Color(100, 149, 237));
		lblbit_1.setFont(new Font("华文隶书", Font.PLAIN, 12));
		lblbit_1.setBounds(426, 259, 84, 15);
		contentPane.add(lblbit_1);
		
		JLabel lblbit_2 = new JLabel("\u5F88\u5F3A\uFF08256bit\uFF09");
		lblbit_2.setForeground(new Color(100, 149, 237));
		lblbit_2.setFont(new Font("华文隶书", Font.PLAIN, 12));
		lblbit_2.setBounds(520, 259, 84, 15);
		contentPane.add(lblbit_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(78, 333, 348, 31);
		contentPane.add(passwordField);
		
		JButton btnDeCryptoButton = new JButton("\u5F00\u59CB\u89E3\u5BC6");
		btnDeCryptoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String fileName=textFieldFilePath.getText();
					String encryptName=fileName;
					
					String filepostfix=fileName.substring(fileName.length()-4,fileName.length());
					char[] password1=passwordField.getPassword();

					FileInputStream fis =new FileInputStream(fileName);
					String password=new String(password1);
				
					
					    byte[] fileIdentfier=new byte[12];
						fis.read(fileIdentfier);
						if(new String(fileIdentfier).equals("suguoyuCrypt"))
						{
						      
							if(rdbtnAes.isSelected())
							{
								//读取IV值
					        byte[] ivvalue=new byte[16];
					        fis.read(ivvalue);
							IvParameterSpec iv=new IvParameterSpec(ivvalue);
							
							//创建密钥；
							MessageDigest md;
							md = MessageDigest.getInstance("SHA3-256");
							byte[] hashvalue=md.digest(password.getBytes());
							
							SecretKeySpec key256=new SecretKeySpec(hashvalue, 0,32,"AES");  //生成三个不同长度的密钥对象
							SecretKeySpec key192=new SecretKeySpec(hashvalue, 0,24,"AES");
							SecretKeySpec key128=new SecretKeySpec(hashvalue, 0,16,"AES");
							
							//解密
							Cipher cipher=Cipher.getInstance("AES/OFB/PKCS5Padding");
							
							if(slider.getValue()==30)                      //判断加密强度用不同长度密钥生成解密对象
							{
								encryptName=encryptName+"解密AES(一般)enc."+filepostfix;
								cipher.init(Cipher.DECRYPT_MODE, key128,iv);
							}
							
							if(slider.getValue()==60)
							{
								encryptName=encryptName+"解密AES(强)enc."+filepostfix;
								cipher.init(Cipher.DECRYPT_MODE, key192,iv);
							}
							
							if(slider.getValue()==90)
							{
								encryptName=encryptName+"解密AES(很强)enc."+filepostfix;
								cipher.init(Cipher.DECRYPT_MODE, key256,iv);
							}
							//文件输出流
							FileOutputStream fos=new FileOutputStream(encryptName);
							
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
							JOptionPane.showMessageDialog(null, "解密成功！(解密文件存放于原文件目录下)");
							}
						
							
							//3DES解密
							if(rdbtndes.isSelected())
					 {
								
						//读取IV值
					     byte[] ivvalue=new byte[8];
					     fis.read(ivvalue);
						 IvParameterSpec iv=new IvParameterSpec(ivvalue);
						    //创建密钥；
							MessageDigest md;
							md = MessageDigest.getInstance("SHA3-256");
							byte[] hashvalue=md.digest(password.getBytes());
							
							SecretKeySpec key3DES=new SecretKeySpec(hashvalue, 0,24,"DESede");  //生成密钥对象
							
							//加密对象
							Cipher cipher=Cipher.getInstance("DESede/CBC/PKCS5Padding");
							cipher.init(Cipher.DECRYPT_MODE, key3DES,iv);  
							encryptName=encryptName+"解密3DES."+filepostfix;			
							//文件输入输出流
							FileOutputStream fos=new FileOutputStream(encryptName);

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
							JOptionPane.showMessageDialog(null, "解密成功！(解密文件存放于原文件目录下)");
					 }
							
						}
						else{
							JOptionPane.showMessageDialog(null, "文件不符合加密文件规范，请检查！");
						}
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "IV 初始向量读取出错！");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "文件读取出错！");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidAlgorithmParameterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "密码输入有误或密码文件不匹配！解密失败");
				}
								
			}
		});
		btnDeCryptoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeCryptoButton.setBackground(new Color(220, 220, 220));
		btnDeCryptoButton.setForeground(new Color(30, 144, 255));
		btnDeCryptoButton.setFont(new Font("隶书", Font.PLAIN, 30));
		btnDeCryptoButton.setBounds(165, 410, 165, 39);
		contentPane.add(btnDeCryptoButton);
		
		JLabel lblEncryption = new JLabel("DeCryption");
		lblEncryption.setForeground(new Color(240, 128, 128));
		lblEncryption.setFont(new Font("Vladimir Script", Font.BOLD, 28));
		lblEncryption.setBounds(213, 25, 153, 39);
		contentPane.add(lblEncryption);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deCryptoFile.dispose();
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBackground(SystemColor.menu);
		button.setForeground(SystemColor.activeCaption);
		button.setFont(new Font("隶书", Font.PLAIN, 20));
		button.setBounds(437, 420, 113, 31);
		contentPane.add(button);
	}
}
