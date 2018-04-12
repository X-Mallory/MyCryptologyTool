import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Cursor;

public class VerifySignature extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFilePath;
	private JTextField textFieldPublickey;
	private JTextField textFieldResult;
	private JTextField textFieldSignValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerifySignature frame = new VerifySignature();
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
	public VerifySignature() {
		VerifySignature verifySignature=this;
		setResizable(false);
		setTitle("VerifySign");
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 677);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerifysign = new JLabel("VerifySign");
		lblVerifysign.setForeground(new Color(175, 238, 238));
		lblVerifysign.setFont(new Font("Script MT Bold", Font.BOLD, 30));
		lblVerifysign.setBounds(231, 10, 174, 38);
		contentPane.add(lblVerifysign);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u9A8C\u8BC1\u7B7E\u540D\u7684\u6587\u4EF6\uFF1A");
		label.setForeground(new Color(135, 206, 250));
		label.setFont(new Font("隶书", Font.PLAIN, 24));
		label.setBounds(22, 70, 324, 34);
		contentPane.add(label);
		
		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(49, 108, 388, 30);
		contentPane.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);
		
		JButton btnFilePathButton = new JButton("...");
		btnFilePathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser  =new JFileChooser("测试\\签名测试");
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
					
				}
			}
		});
		btnFilePathButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilePathButton.setBackground(new Color(245, 245, 245));
		btnFilePathButton.setBounds(508, 107, 118, 30);
		contentPane.add(btnFilePathButton);
		
		JLabel label_1 = new JLabel("\u8BF7\u786E\u5B9A\u7B7E\u540D\u7684\u7B97\u6CD5\uFF1A");
		label_1.setForeground(new Color(135, 206, 250));
		label_1.setFont(new Font("隶书", Font.PLAIN, 24));
		label_1.setBounds(22, 246, 324, 34);
		contentPane.add(label_1);
		
		JRadioButton rdbtnSha512withdsa = new JRadioButton("SHA512withDSA");
		rdbtnSha512withdsa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnSha384withdsa = new JRadioButton("SHA384withDSA");
		rdbtnSha384withdsa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnSha224withrsa = new JRadioButton("SHA224withRSA");
		rdbtnSha224withrsa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JRadioButton rdbtnSha256withrsa = new JRadioButton("SHA256withRSA");
		rdbtnSha256withrsa.setSelected(true);
		rdbtnSha256withrsa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnSha256withrsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSha256withrsa.isSelected()){
					rdbtnSha224withrsa.setSelected(false);
					rdbtnSha384withdsa.setSelected(false);
					rdbtnSha512withdsa.setSelected(false);
				}
			}
		});
		rdbtnSha256withrsa.setForeground(new Color(219, 112, 147));
		rdbtnSha256withrsa.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnSha256withrsa.setBounds(67, 292, 193, 23);
		contentPane.add(rdbtnSha256withrsa);
		
		rdbtnSha224withrsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSha224withrsa.isSelected()){
					rdbtnSha256withrsa.setSelected(false);
					rdbtnSha384withdsa.setSelected(false);
					rdbtnSha512withdsa.setSelected(false);
				}
			}
		});
		rdbtnSha224withrsa.setForeground(new Color(244, 164, 96));
		rdbtnSha224withrsa.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnSha224withrsa.setBounds(271, 292, 193, 23);
		contentPane.add(rdbtnSha224withrsa);
		
		rdbtnSha384withdsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSha384withdsa.isSelected()){
					rdbtnSha256withrsa.setSelected(false);
					rdbtnSha224withrsa.setSelected(false);
					rdbtnSha512withdsa.setSelected(false);
				}
			}
		});
		rdbtnSha384withdsa.setForeground(new Color(127, 255, 0));
		rdbtnSha384withdsa.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnSha384withdsa.setBounds(67, 337, 193, 23);
		contentPane.add(rdbtnSha384withdsa);
		
		rdbtnSha512withdsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSha512withdsa.isSelected()){
					rdbtnSha256withrsa.setSelected(false);
					rdbtnSha384withdsa.setSelected(false);
					rdbtnSha224withrsa.setSelected(false);
				}
			}
		});
		rdbtnSha512withdsa.setForeground(new Color(135, 206, 250));
		rdbtnSha512withdsa.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rdbtnSha512withdsa.setBounds(271, 338, 204, 23);
		contentPane.add(rdbtnSha512withdsa);
		
		JLabel label_2 = new JLabel("\u8BF7\u8F93\u5165\u9A8C\u8BC1\u7B7E\u540D\u7684\u516C\u94A5\u7684\u6570\u5B57\u8BC1\u4E66\uFF1A");
		label_2.setForeground(new Color(135, 206, 250));
		label_2.setFont(new Font("隶书", Font.PLAIN, 24));
		label_2.setBounds(22, 366, 394, 34);
		contentPane.add(label_2);
		
		textFieldPublickey = new JTextField();
		textFieldPublickey.setColumns(10);
		textFieldPublickey.setBounds(49, 410, 367, 30);
		contentPane.add(textFieldPublickey);
		
		JButton btnPublicKeyFilePath = new JButton("...");
		btnPublicKeyFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser  =new JFileChooser("G:\\课程相关\\密码学相关\\测试\\签名测试");
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					textFieldPublickey.setText(fileChooser.getSelectedFile().getPath());
					
				}
			}
		});
		btnPublicKeyFilePath.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPublicKeyFilePath.setBackground(new Color(245, 245, 245));
		btnPublicKeyFilePath.setBounds(508, 413, 118, 30);
		contentPane.add(btnPublicKeyFilePath);
		
		JLabel label_3 = new JLabel("\u9A8C\u8BC1\u7ED3\u679C\uFF1A");
		label_3.setForeground(new Color(135, 206, 250));
		label_3.setFont(new Font("隶书", Font.PLAIN, 24));
		label_3.setBounds(22, 450, 324, 34);
		contentPane.add(label_3);
		
		textFieldResult = new JTextField();
		textFieldResult.setFont(new Font("隶书", Font.PLAIN, 18));
		textFieldResult.setBackground(new Color(255, 228, 225));
		textFieldResult.setEditable(false);
		textFieldResult.setColumns(10);
		textFieldResult.setBounds(49, 484, 211, 30);
		contentPane.add(textFieldResult);
		
		JButton btnbuttonSignValueFilePath = new JButton("...");
		btnbuttonSignValueFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			JFileChooser fileChooser  =new JFileChooser("测试\\签名测试");
			if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				textFieldSignValue.setText(fileChooser.getSelectedFile().getPath());
				
			}	
			}
		});
		btnbuttonSignValueFilePath.setBackground(new Color(245, 245, 245));
		btnbuttonSignValueFilePath.setBounds(508, 195, 118, 30);
		contentPane.add(btnbuttonSignValueFilePath);
		
		JButton btnVerifyButton = new JButton("\u5F00\u59CB\u9A8C\u8BC1");
		btnVerifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				//读取证书获得公钥
			
				try {
					//读证书得公钥
					String keyFilepath=textFieldPublickey.getText();
					CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
					FileInputStream fincert=new FileInputStream(keyFilepath);
					Certificate certificate =certificateFactory.generateCertificate(fincert);
					X509Certificate x509Certificate=(X509Certificate)certificate;
					fincert.close();
					
					PublicKey publickey=x509Certificate.getPublicKey();
					FileInputStream fisSign=new FileInputStream(textFieldSignValue.getText());
				
					//读签名值
					int nn=0;
					byte[] temp=new byte[1024];
		
					nn=fisSign.read(temp);//签名值
					fisSign.close();
					
					byte[] signValue=Arrays.copyOf(temp,nn);
					String fileName=textFieldFilePath.getText();
					FileInputStream fis;
					fis = new FileInputStream(fileName);//输入流
					boolean result=false;
					
					
					if(rdbtnSha256withrsa.isSelected())
					{      try {  
						   Signature signature=Signature.getInstance("SHA256withRSA");
					       signature.initVerify(publickey);
					       byte[] buffer=new byte[1024];
							int n=0;
							while((n=fis.read(buffer))!=-1)
							{
								signature.update(buffer);
							}
					       
					      
							result=signature.verify(signValue);
							   if(result)
							   {
								   textFieldResult.setText("验证通过！");
							   }
							   else{
								   textFieldResult.setText("验证不通过！");
							   }
							   JOptionPane.showMessageDialog(null, "验证完成！");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							 textFieldResult.setText("验证不通过！");
							 JOptionPane.showMessageDialog(null, "验证出错，请检查各输入和算法选择！");
						}

					}
					if(rdbtnSha224withrsa.isSelected())
					{  try {
						Signature signature=Signature.getInstance("SHA224withRSA");
						  signature.initVerify(publickey);
					       byte[] buffer=new byte[1024];
							int n=0;
							while((n=fis.read(buffer))!=-1)
							{
								signature.update(buffer);
							}
					       
					     
							result=signature.verify(signValue);
							   if(result)
							   {
								   textFieldResult.setText("验证通过！");
							   }
							   else{
								   textFieldResult.setText("验证不通过！");
							   }
							   JOptionPane.showMessageDialog(null, "验证完成！");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							 textFieldResult.setText("验证不通过！");
							 JOptionPane.showMessageDialog(null, "验证出错，请检查各输入和算法选择！");
						}

					}
					if(rdbtnSha384withdsa.isSelected())
					{   try {
						Signature signature=Signature.getInstance("SHA384withDSA");
						   signature.initVerify(publickey);
					       byte[] buffer=new byte[1024];
							int n=0;
							while((n=fis.read(buffer))!=-1)
							{
								signature.update(buffer);
							}
					       
					       
							result=signature.verify(signValue);
							   if(result)
							   {
								   textFieldResult.setText("验证通过！");
							   }
							   else{
								   textFieldResult.setText("验证不通过！");
							   }
							   JOptionPane.showMessageDialog(null, "验证完成！");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							 textFieldResult.setText("验证不通过！");
							 JOptionPane.showMessageDialog(null, "验证出错，请检查各输入和算法选择！");
						}

					}
					if(rdbtnSha512withdsa.isSelected())
					{ try {
						Signature signature=Signature.getInstance("SHA512withDSA");
						   signature.initVerify(publickey);
					       byte[] buffer=new byte[1024];
							int n=0;
							while((n=fis.read(buffer))!=-1)
							{
								signature.update(buffer);
							}
					       
					       
							result=signature.verify(signValue);
							   if(result)
							   {
								   textFieldResult.setText("验证通过！");
							   }
							   else{
								   textFieldResult.setText("验证不通过！");
							   }
							   JOptionPane.showMessageDialog(null, "验证完成！");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							 textFieldResult.setText("验证不通过！");
							 JOptionPane.showMessageDialog(null, "验证出错，请检查各输入和算法选择！");
						}
					}
					fis.close();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				} catch (CertificateException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "证书出错！");
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "文件读取出错！");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVerifyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerifyButton.setBackground(new Color(245, 245, 245));
		btnVerifyButton.setForeground(new Color(173, 216, 230));
		btnVerifyButton.setFont(new Font("隶书", Font.PLAIN, 26));
		btnVerifyButton.setBounds(125, 568, 145, 43);
		contentPane.add(btnVerifyButton);
		
		JButton btnBackButton = new JButton("\u8FD4\u56DE");
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifySignature.dispose();
			}
		});
		btnBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBackButton.setBackground(new Color(245, 245, 245));
		btnBackButton.setForeground(new Color(135, 206, 250));
		btnBackButton.setFont(new Font("隶书", Font.PLAIN, 20));
		btnBackButton.setBounds(414, 573, 134, 38);
		contentPane.add(btnBackButton);
		
		JLabel label_4 = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u9A8C\u8BC1\u7B7E\u540D\u503C\uFF1A");
		label_4.setForeground(new Color(135, 206, 250));
		label_4.setFont(new Font("隶书", Font.PLAIN, 24));
		label_4.setBounds(22, 148, 324, 34);
		contentPane.add(label_4);
		
		textFieldSignValue = new JTextField();
		textFieldSignValue.setColumns(10);
		textFieldSignValue.setBounds(49, 192, 388, 30);
		contentPane.add(textFieldSignValue);
		
		
	}
}
