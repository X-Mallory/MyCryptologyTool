import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;

import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import java.awt.Cursor;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;



public class SignatureFile extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldFilePath;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldAgain;
	private JTextField textFieldSignValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignatureFile frame = new SignatureFile();
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
	public SignatureFile() {
		SignatureFile signatureFile=this;
		setResizable(false);
		setTitle("Sighature");
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 765);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(72, 209, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignature = new JLabel("Signature");
		lblSignature.setForeground(new Color(100, 149, 237));
		lblSignature.setFont(new Font("Tiranti Solid LET", Font.BOLD, 32));
		lblSignature.setBounds(321, 26, 128, 40);
		contentPane.add(lblSignature);
		
		JLabel label = new JLabel("\u8BF7\u9009\u62E9\u9700\u8981\u7B7E\u540D\u7684\u6587\u4EF6\uFF1A");
		label.setForeground(new Color(135, 206, 235));
		label.setFont(new Font("隶书", Font.PLAIN, 24));
		label.setBounds(43, 57, 274, 40);
		contentPane.add(label);
		
		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(50, 109, 467, 31);
		contentPane.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);
		
		JButton btnFilePathButton = new JButton("...");
		btnFilePathButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilePathButton.setBackground(SystemColor.control);
		btnFilePathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser  =new JFileChooser("测试\\签名测试");
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
					
				}
			}
		});
		btnFilePathButton.setBounds(605, 109, 107, 31);
		contentPane.add(btnFilePathButton);
		
		JLabel label_1 = new JLabel("\u5728\u6B64\u4E4B\u524D\u751F\u6210\u4E00\u5BF9\u5C5E\u4E8E\u4F60\u7684\u5BC6\u94A5\u5BF9\u548C\u516C\u94A5\u6570\u5B57\u8BC1\u4E66\uFF1A");
		label_1.setForeground(new Color(135, 206, 235));
		label_1.setFont(new Font("隶书", Font.PLAIN, 20));
		label_1.setBounds(30, 270, 545, 31);
		contentPane.add(label_1);
		
		JLabel lblyourkeystorekeystoreyour = new JLabel("\uFF08\u751F\u6210\u7684\u5BC6\u94A5\u5BF9\u548C\u516C\u94A5\u6570\u5B57\u8BC1\u4E66\u5B58\u5728\u7B7E\u540D\u6587\u4EF6\u76EE\u5F55\u4E0B\u7684YourKeyStore.keystore\u548CYourSign.cer\u4E2D\uFF09");
		lblyourkeystorekeystoreyour.setForeground(new Color(135, 206, 235));
		lblyourkeystorekeystoreyour.setFont(new Font("隶书", Font.BOLD, 16));
		lblyourkeystorekeystoreyour.setBounds(20, 301, 760, 19);
		contentPane.add(lblyourkeystorekeystoreyour);
		
		JLabel label_2 = new JLabel("\u8BF7\u9009\u62E9\u7B7E\u540D\u7B97\u6CD5\uFF1A");
		label_2.setForeground(new Color(135, 206, 235));
		label_2.setFont(new Font("隶书", Font.PLAIN, 24));
		label_2.setBounds(43, 150, 202, 40);
		contentPane.add(label_2);
		
		JRadioButton rdbtnShawithdsa_512 = new JRadioButton("SHA512withDSA");
		rdbtnShawithdsa_512.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnShawithdsa_384 = new JRadioButton("SHA384withDSA");
		rdbtnShawithdsa_384.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnSHA224withRSA = new JRadioButton("SHA224withRSA");
		rdbtnSHA224withRSA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnSHA256withRSA = new JRadioButton("SHA256withRSA");
		rdbtnSHA256withRSA.setSelected(true);
		rdbtnSHA256withRSA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		rdbtnSHA256withRSA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSHA256withRSA.isSelected()){
					rdbtnSHA224withRSA.setSelected(false);
					rdbtnShawithdsa_384.setSelected(false);
					rdbtnShawithdsa_512.setSelected(false);
				}
			}
		});
		rdbtnSHA256withRSA.setForeground(new Color(205, 92, 92));
		rdbtnSHA256withRSA.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnSHA256withRSA.setBounds(123, 196, 179, 31);
		contentPane.add(rdbtnSHA256withRSA);
		
		rdbtnSHA224withRSA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSHA224withRSA.isSelected()){
					rdbtnSHA256withRSA.setSelected(false);
					rdbtnShawithdsa_384.setSelected(false);
					rdbtnShawithdsa_512.setSelected(false);
				}
			}
		});
		rdbtnSHA224withRSA.setForeground(new Color(255, 165, 0));
		rdbtnSHA224withRSA.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnSHA224withRSA.setBounds(322, 196, 179, 31);
		contentPane.add(rdbtnSHA224withRSA);
		
		rdbtnShawithdsa_384.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnShawithdsa_384.isSelected()){
					rdbtnSHA256withRSA.setSelected(false);
					rdbtnSHA224withRSA.setSelected(false);
					rdbtnShawithdsa_512.setSelected(false);
				}
			}
		});
		rdbtnShawithdsa_384.setForeground(new Color(124, 252, 0));
		rdbtnShawithdsa_384.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnShawithdsa_384.setBounds(123, 239, 179, 31);
		contentPane.add(rdbtnShawithdsa_384);
		
		rdbtnShawithdsa_512.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnShawithdsa_512.isSelected()){
					rdbtnSHA256withRSA.setSelected(false);
					rdbtnShawithdsa_384.setSelected(false);
					rdbtnSHA224withRSA.setSelected(false);
				}
			}
		});
		rdbtnShawithdsa_512.setForeground(new Color(135, 206, 250));
		rdbtnShawithdsa_512.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnShawithdsa_512.setBounds(322, 239, 179, 31);
		contentPane.add(rdbtnShawithdsa_512);
		
		JLabel label_3 = new JLabel("\uFF08\u4E00\u79CD\u5373\u53EF\uFF09");
		label_3.setForeground(new Color(135, 206, 235));
		label_3.setFont(new Font("隶书", Font.PLAIN, 16));
		label_3.setBounds(231, 162, 107, 23);
		contentPane.add(label_3);
		
		JLabel lblyourkeystore = new JLabel("\u8BF7\u5148\u8F93\u5165YourKeyStore\u6587\u4EF6\u7684\u5BC6\u7801\uFF1A");
		lblyourkeystore.setForeground(new Color(135, 206, 235));
		lblyourkeystore.setFont(new Font("隶书", Font.PLAIN, 24));
		lblyourkeystore.setBounds(30, 324, 424, 40);
		contentPane.add(lblyourkeystore);
		
		JLabel lblyourkeystore_1 = new JLabel("\uFF08\u7528\u4E8E\u4E4B\u540E\u6253\u5F00YourKeyStore\u6587\u4EF6\u67E5\u770B\u4F60\u7684\u5BC6\u94A5\u5BF9\uFF09");
		lblyourkeystore_1.setForeground(new Color(135, 206, 235));
		lblyourkeystore_1.setFont(new Font("隶书", Font.PLAIN, 16));
		lblyourkeystore_1.setBounds(410, 330, 370, 27);
		contentPane.add(lblyourkeystore_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(30, 375, 356, 31);
		contentPane.add(passwordField);
		
		JLabel lblyourkeystore_2 = new JLabel("\u8BF7\u518D\u6B21\u8F93\u5165YourKeyStore\u6587\u4EF6\u7684\u5BC6\u7801\uFF1A");
		lblyourkeystore_2.setForeground(new Color(135, 206, 235));
		lblyourkeystore_2.setFont(new Font("隶书", Font.PLAIN, 24));
		lblyourkeystore_2.setBounds(30, 411, 424, 40);
		contentPane.add(lblyourkeystore_2);
		
		passwordFieldAgain = new JPasswordField();
		passwordFieldAgain.setBounds(30, 453, 356, 31);
		contentPane.add(passwordFieldAgain);
		
		JLabel label_4 = new JLabel("\u8BF7\u9009\u62E9\u7B7E\u540D\u7B97\u6CD5\u7684\u52A0\u5BC6\u5F3A\u5EA6\uFF1A");
		label_4.setForeground(new Color(135, 206, 235));
		label_4.setFont(new Font("隶书", Font.PLAIN, 24));
		label_4.setBounds(30, 494, 424, 40);
		contentPane.add(label_4);
		
		JSlider slider = new JSlider();
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setValue(30);
		slider.setMaximum(90);
		slider.setMajorTickSpacing(30);
		slider.setBounds(375, 502, 293, 26);
		contentPane.add(slider);
		
		JLabel label_5 = new JLabel("\u4E00\u822C");
		label_5.setForeground(new Color(135, 206, 235));
		label_5.setFont(new Font("隶书", Font.BOLD, 14));
		label_5.setBounds(400, 538, 54, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u5F3A");
		label_6.setForeground(new Color(124, 252, 0));
		label_6.setFont(new Font("隶书", Font.BOLD, 15));
		label_6.setBounds(510, 538, 54, 15);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u5F88\u5F3A");
		label_7.setForeground(new Color(255, 0, 0));
		label_7.setFont(new Font("隶书", Font.BOLD, 15));
		label_7.setBounds(599, 538, 54, 15);
		contentPane.add(label_7);
		
		JButton btnSignButton = new JButton("\u5F00\u59CB\u7B7E\u540D");
		btnSignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passWord1= passwordField.getPassword();
				char[] passWord2=passwordFieldAgain.getPassword();
				if(Arrays.equals(passWord1,passWord2))
				{
				try {
					//按照选择生成三种密钥对
					KeyPairGenerator keyPairGeneratorDSA = null;
					KeyPairGenerator keyPairGeneratorRSA=null;
					
					KeyPair keypair=null;
					PrivateKey privateKey=null;
					
				if(rdbtnShawithdsa_384.isSelected())
				{
					if(slider.getValue()==30)
					{
						keyPairGeneratorDSA = KeyPairGenerator.getInstance("DSA");
						keyPairGeneratorDSA.initialize(512);
					}
					if(slider.getValue()==60)
					{
						keyPairGeneratorDSA = KeyPairGenerator.getInstance("DSA");
						keyPairGeneratorDSA.initialize(1024);
						
					}
					if(slider.getValue()==90)
					{
						keyPairGeneratorDSA = KeyPairGenerator.getInstance("DSA");
						keyPairGeneratorDSA.initialize(2048);	
					}
					keypair=keyPairGeneratorDSA.generateKeyPair();
					privateKey=keypair.getPrivate();
					
					//先生成自签名证书
					String subjectDN = "CN = su OU = cauc O = cauc L = tj S = tj C = cn";
					
					String signatureAlgorithm="SHA224WithDSA";           //所用的签名算法名
					
					//数字证书生成并导出
					Certificate certificate = GenerateCert.selfSign(keypair, subjectDN, signatureAlgorithm);
					//System.out.println(certificate);
					PemWriter pemWriter = new PemWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textFieldFilePath.getText()+"224DSAa.cer"))));

	                //导出证书
					pemWriter.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));

					pemWriter.close();

					
					//导出密钥对到密钥库文件
					KeyStore keyStore = KeyStore.getInstance("JCEKS");
					keyStore.load(null, passWord1);
					keyStore.setKeyEntry("mydsakey", keypair.getPrivate().getEncoded(),new Certificate[] { certificate } );
					FileOutputStream fos = new FileOutputStream(textFieldFilePath.getText()+"YourDSA384KeyStore.keystore");
					keyStore.store(fos, passWord1);				
					fos.close();
					
					JOptionPane.showMessageDialog(null, "已为你生成一个DSA密钥对，并以数字证书的形式存到了签名文件目录下，密钥库文件密码为你刚才输的密码！");
					
			  }
				
			if(rdbtnShawithdsa_512.isSelected())
			{
				if(slider.getValue()==30)
				{
					keyPairGeneratorDSA = KeyPairGenerator.getInstance("DSA");
					keyPairGeneratorDSA.initialize(512);
				}
				if(slider.getValue()==60)
				{
					keyPairGeneratorDSA = KeyPairGenerator.getInstance("DSA");
					keyPairGeneratorDSA.initialize(1024);
					
				}
				if(slider.getValue()==90)
				{
					keyPairGeneratorDSA = KeyPairGenerator.getInstance("DSA");
					keyPairGeneratorDSA.initialize(2048);	
				}
				keypair=keyPairGeneratorDSA.generateKeyPair();
				privateKey=keypair.getPrivate();
				
				//先生成自签名证书
				String subjectDN = "CN = su OU = cauc O = cauc L = tj S = tj C = cn";
				
				String signatureAlgorithm="SHA224WithDSA";           //所用的签名算法名
				
				//数字证书生成并导出
				Certificate certificate = GenerateCert.selfSign(keypair, subjectDN, signatureAlgorithm);
				//System.out.println(certificate);
				PemWriter pemWriter = new PemWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textFieldFilePath.getText()+"512DSAa.cer"))));

                //导出证书

				pemWriter.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));

				pemWriter.close();

				
				//导出密钥对到密钥库文件
				KeyStore keyStore = KeyStore.getInstance("JCEKS");
				keyStore.load(null, passWord1);
				keyStore.setKeyEntry("mydsakey", keypair.getPrivate().getEncoded(),new Certificate[] { certificate } );
				FileOutputStream fos = new FileOutputStream(textFieldFilePath.getText()+"YourDSA512KeyStore.keystore");
				keyStore.store(fos, passWord1);				
				fos.close();
				
				JOptionPane.showMessageDialog(null, "已为你生成一个DSA密钥对，并以数字证书的形式存到了签名文件目录下，密钥库文件密码为你刚才输的密码！");
				
			}
			
			if(rdbtnSHA256withRSA.isSelected())
			{
				if(slider.getValue()==30)
				{
					keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
					keyPairGeneratorRSA.initialize(512);
				}
				if(slider.getValue()==60)
				{
					keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
					keyPairGeneratorRSA.initialize(1024);
					
				}
				if(slider.getValue()==90)
				{
					keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
					keyPairGeneratorRSA.initialize(2048);	
				}
				keypair=keyPairGeneratorRSA.generateKeyPair();
				privateKey=keypair.getPrivate();
				
				//先生成自签名证书
				String subjectDN = "CN = su OU = cauc O = cauc L = tj S = tj C = cn";
				
				String signatureAlgorithm="SHA224WithRSA";           //所用的签名算法名
				
				//数字证书生成并导出
				Certificate certificate = GenerateCert.selfSign(keypair, subjectDN, signatureAlgorithm);
				//System.out.println(certificate);
				PemWriter pemWriter = new PemWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textFieldFilePath.getText()+"256RSAa.cer"))));

                //导出证书
				pemWriter.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));

				pemWriter.close();

				
				//导出密钥对到密钥库文件
				KeyStore keyStore = KeyStore.getInstance("JCEKS");
				keyStore.load(null, passWord1);
				keyStore.setKeyEntry("myrsakey", keypair.getPrivate().getEncoded(),new Certificate[] { certificate } );
				FileOutputStream fos = new FileOutputStream(textFieldFilePath.getText()+"YourRSA256KeyStore.keystore");
				keyStore.store(fos, passWord1);				
				fos.close();
				
				JOptionPane.showMessageDialog(null, "已为你生成一个RSA密钥对，并以数字证书的形式存到了签名文件目录下，密钥库文件密码为你刚才输的密码！");
				
			}
			if(rdbtnSHA224withRSA.isSelected())
			{    
				if(slider.getValue()==30)
				{
					keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
					keyPairGeneratorRSA.initialize(512);
				}
				if(slider.getValue()==60)
				{
					keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
					keyPairGeneratorRSA.initialize(1024);
					
				}
				if(slider.getValue()==90)
				{
					keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
					keyPairGeneratorRSA.initialize(2048);
				}
				keypair=keyPairGeneratorRSA.generateKeyPair();
				privateKey=keypair.getPrivate();
				
				//先生成自签名证书
				String subjectDN = "CN = su OU = cauc O = cauc L = tj S = tj C = cn";
				
				String signatureAlgorithm="SHA224WithRSA";           //所用的签名算法名
				
				//数字证书生成并导出
				Certificate certificate = GenerateCert.selfSign(keypair, subjectDN, signatureAlgorithm);
				//System.out.println(certificate);
				PemWriter pemWriter = new PemWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textFieldFilePath.getText()+"224RSAa.cer"))));

                //导出证书

				pemWriter.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));

				pemWriter.close();

				
				//导出密钥对到密钥库文件
				KeyStore keyStore = KeyStore.getInstance("JCEKS");
				keyStore.load(null, passWord1);
				keyStore.setKeyEntry("myecdsakey", keypair.getPrivate().getEncoded(),new Certificate[] { certificate } );
				FileOutputStream fos = new FileOutputStream(textFieldFilePath.getText()+"YourRSA224KeyStore.keystore");
				keyStore.store(fos, passWord1);				
				fos.close();
				
				JOptionPane.showMessageDialog(null, "已为你生成一个RSA密钥对，并以数字证书的形式存到了签名文件目录下，密钥库文件密码为你刚才输的密码！");
				
			}

					if(textFieldFilePath.getText()!=null)
					{
					   try {
						
							String fileName=textFieldFilePath.getText();
							String signFileName=fileName;
							FileInputStream fis;
							fis = new FileInputStream(fileName);//输入流
							
							
							if(rdbtnSHA256withRSA.isSelected())
							{       
								   Signature signature=Signature.getInstance("SHA256withRSA");
							       signature.initSign(privateKey);
							       int n=0;
							        byte[] buffer=new byte[1024];
									while((n=fis.read(buffer))!=-1)
									{
										signature.update(buffer);
									}
							  
							       
							       signFileName=signFileName+"FileSignSHA256withRSA.txt";
							       byte[] signValue=signature.sign();
							       textFieldSignValue.setText(Hex.toHexString(signValue));
							       FileOutputStream fosSHA224withDSA=new FileOutputStream(signFileName);
							       fosSHA224withDSA.write(signValue); 
							     
							       fosSHA224withDSA.close();
							       
							       JOptionPane.showMessageDialog(null, "签名完成，签名文件存在原文件目录下！");

							}
							if(rdbtnSHA224withRSA.isSelected())
							{
								 
								    Signature signature=Signature.getInstance("SHA224withRSA");
								      signature.initSign(privateKey);
								      int n=0;
								        byte[] buffer=new byte[1024];
										while((n=fis.read(buffer))!=-1)
										{
											signature.update(buffer);
										}
								       
								       signFileName=signFileName+"FileSignSHA224withRSA.txt";
								       byte[] signValue=signature.sign();
								       textFieldSignValue.setText(Hex.toHexString(signValue));
								       FileOutputStream fosSHA256withDSA=new FileOutputStream(signFileName);
								       fosSHA256withDSA.write(signValue); 
								       fosSHA256withDSA.close();
								       JOptionPane.showMessageDialog(null, "签名完成，签名文件存在原文件目录下！");
							}
							if(rdbtnShawithdsa_384.isSelected())
							{
								   
								    Signature signature=Signature.getInstance("SHA384withDSA");
								       signature.initSign(privateKey);
								       int n=0;
								        byte[] buffer=new byte[1024];
										while((n=fis.read(buffer))!=-1)
										{
											signature.update(buffer);
										}
								       
								       signFileName=signFileName+"FileSignSHA384withDSA.txt";
								       byte[] signValue=signature.sign();
								       textFieldSignValue.setText(Hex.toHexString(signValue));
								       FileOutputStream fosSHA384withDSA=new FileOutputStream(signFileName);
								       fosSHA384withDSA.write(signValue); 
								       fosSHA384withDSA.close();
								       JOptionPane.showMessageDialog(null, "签名完成，签名文件存在原文件目录下！");
							}
							if(rdbtnShawithdsa_512.isSelected())
							{
								   
								    Signature signature=Signature.getInstance("SHA512withDSA");
								       signature.initSign(privateKey);
								       int n=0;
								        byte[] buffer=new byte[1024];
										while((n=fis.read(buffer))!=-1)
										{
											signature.update(buffer);
										}
								       
								       signFileName=signFileName+"FileSignSHA512withDSA.txt";
								       byte[] signValue=signature.sign();
								       textFieldSignValue.setText(Hex.toHexString(signValue));
								       FileOutputStream fosSHA512withDSA=new FileOutputStream(signFileName);
								       fosSHA512withDSA.write(signValue); 
								       fosSHA512withDSA.close();
								       JOptionPane.showMessageDialog(null, "签名完成，签名文件存在原文件目录下！");
							}
							fis.close();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "文件读取出错！");
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "算法选择出错！");
						e1.printStackTrace();
					} catch (SignatureException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "签名出错！");
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "输入输出出错！");
						e1.printStackTrace();
					}
					}else {
					JOptionPane.showMessageDialog(null, "请选择需要签名的文件！");
					      }
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "密钥出错！");
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (KeyStoreException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "密钥库文件生成出错！");
					e1.printStackTrace();
				} catch (CertificateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
				
				else{
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入！");
				}
			}
		});
		btnSignButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignButton.setBackground(SystemColor.control);
		btnSignButton.setForeground(new Color(32, 178, 170));
		btnSignButton.setFont(new Font("隶书", Font.PLAIN, 26));
		btnSignButton.setBounds(155, 646, 166, 48);
		contentPane.add(btnSignButton);
		
		JButton btnBackButton = new JButton("\u8FD4\u56DE");
		btnBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBackButton.setBackground(SystemColor.control);
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signatureFile.dispose();
			}
		});
		btnBackButton.setForeground(new Color(135, 206, 235));
		btnBackButton.setFont(new Font("隶书", Font.PLAIN, 20));
		btnBackButton.setBounds(463, 658, 116, 30);
		contentPane.add(btnBackButton);
		
		JLabel lblyourfilesigntxt = new JLabel("\uFF08\u8BE5\u6587\u4EF6\u7684\u7B7E\u540D\u503C\u5B58\u5728\u5F53\u524D\u76EE\u5F55\u4E0BYourFileSign.txt\u6587\u4EF6\u4E2D\uFF09");
		lblyourfilesigntxt.setForeground(new Color(135, 206, 235));
		lblyourfilesigntxt.setFont(new Font("隶书", Font.PLAIN, 16));
		lblyourfilesigntxt.setBounds(165, 704, 440, 23);
		contentPane.add(lblyourfilesigntxt);
		
		JLabel label_8 = new JLabel("\u8BE5\u6587\u4EF6\u7684\u7B7E\u540D\u503C\u4E3A\uFF1A");
		label_8.setForeground(new Color(135, 206, 235));
		label_8.setFont(new Font("隶书", Font.PLAIN, 24));
		label_8.setBounds(30, 544, 335, 40);
		contentPane.add(label_8);
		
		textFieldSignValue = new JTextField();
		textFieldSignValue.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSignValue.setBackground(new Color(224, 255, 255));
		textFieldSignValue.setColumns(10);
		textFieldSignValue.setBounds(30, 588, 610, 31);
		contentPane.add(textFieldSignValue);
	}
}

class GenerateCert {
	// 生成自签名数字证书
	   public static Certificate selfSign(KeyPair keyPair, String subjectDN, String signatureAlgorithm) throws Exception {
		BouncyCastleProvider bcProvider = new BouncyCastleProvider();
		Security.addProvider(bcProvider);

		long now = System.currentTimeMillis();
		Date startDate = new Date(now);
		X500Name dnName = new X500Name(subjectDN);

		// Using the current time stamp as the certificate serial number
		BigInteger certSerialNumber = new BigInteger(Long.toString(now));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, 1); // <-- 1 Yr validity
		Date endDate = calendar.getTime();

		ContentSigner contentSigner = new JcaContentSignerBuilder(signatureAlgorithm).build(keyPair.getPrivate());

		JcaX509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(dnName, certSerialNumber, startDate,
				endDate, dnName, keyPair.getPublic());

		// Extensions --------------------------
		// Basic Constraints true for CA, false for EndEntity
		BasicConstraints basicConstraints = new BasicConstraints(true);
		// Basic Constraints is usually marked as critical.
		certBuilder.addExtension(new ASN1ObjectIdentifier("2.5.29.19"), true, basicConstraints);

		return new JcaX509CertificateConverter().setProvider(bcProvider)
				.getCertificate(certBuilder.build(contentSigner));
	   }
}

