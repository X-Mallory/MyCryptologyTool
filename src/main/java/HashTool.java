import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ByteLookupTable;
import java.awt.peer.CheckboxMenuItemPeer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.awt.Panel;
import javax.swing.DropMode;
import java.awt.Label;


public class HashTool extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldInPutMassage;
	private JTextField textFieldMD5;
	private JTextField textFieldSHA3_224;
	private JCheckBox chckbxMD5;
	private JCheckBox chckbxSHA3_224;
	private JTextField textFieldSHA3_256;
	private JTextField textFieldSHA3_384;
	private JTextField textFieldSHA3_512;
	private JTextField textFieldSHA_224;
	private JTextField textFieldSHA_256;
	private JTextField textFieldSHA_384;
	private JTextField textFieldSHA_512;
	private JTextField textFieldSHA1;
	private JTextField textFieldFilePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HashTool frame = new HashTool();
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
	public HashTool() {
	
		HashTool hashTool=this;
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setForeground(new Color(0, 128, 128));
		setResizable(false);
		setTitle("HashCalc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 713);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("TableHeader.background"));
		contentPane.setBorder(new LineBorder(new Color(255, 245, 238)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u5B57\u7B26\u4E32\uFF1A");
		label.setForeground(new Color(51, 153, 204));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("方正舒体", Font.PLAIN, 23));
		label.setBounds(41, 97, 144, 31);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8F93\u51FA\u7ED3\u679C\uFF1A");
		label_1.setForeground(new Color(51, 153, 204));
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 23));
		label_1.setBounds(41, 181, 115, 27);
		contentPane.add(label_1);
		
		textFieldInPutMassage = new JTextField();
		textFieldInPutMassage.setBackground(new Color(255, 240, 245));
		textFieldInPutMassage.setForeground(Color.BLACK);
		textFieldInPutMassage.setBounds(197, 105, 514, 23);
		contentPane.add(textFieldInPutMassage);
		textFieldInPutMassage.setColumns(10);
		
       
       
		JButton btnBack = new JButton("\u8FD4\u56DE");
		btnBack.setFont(new Font("隶书", Font.PLAIN, 18));
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setBorder(new LineBorder(new Color(102, 153, 204)));
		btnBack.setForeground(new Color(135, 206, 235));
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hashTool.dispose();//关闭当前窗口
			}
		});
		
		
		btnBack.setBounds(682, 622, 114, 37);
		contentPane.add(btnBack);
		
		chckbxMD5 = new JCheckBox("MD5");
		chckbxMD5.setEnabled(false);
		chckbxMD5.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxMD5.setBackground(SystemColor.control);
		chckbxMD5.setForeground(Color.LIGHT_GRAY);
		chckbxMD5.setBounds(63, 229, 52, 23);
		contentPane.add(chckbxMD5);
		
		chckbxSHA3_224 = new JCheckBox("SHA3-224");
		chckbxSHA3_224.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA3_224.setForeground(new Color(153, 153, 255));
		chckbxSHA3_224.setBackground(SystemColor.control);
		chckbxSHA3_224.setBounds(64, 268, 103, 23);
		contentPane.add(chckbxSHA3_224);
		
		textFieldMD5 = new JTextField();
		textFieldMD5.setFont(new Font("隶书", Font.PLAIN, 12));
		textFieldMD5.setForeground(Color.LIGHT_GRAY);
		textFieldMD5.setText("\u6B64\u7B97\u6CD5\u5DF2\u7ECF\u4E0D\u5B89\u5168\uFF0C\u5DF2\u88AB\u820D\u5F03");
		textFieldMD5.setEditable(false);
		textFieldMD5.setBackground(new Color(255, 240, 245));
		textFieldMD5.setBounds(178, 228, 618, 21);
		contentPane.add(textFieldMD5);
		textFieldMD5.setColumns(10);
		
		textFieldSHA3_224 = new JTextField();
		textFieldSHA3_224.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA3_224.setEditable(false);
		textFieldSHA3_224.setBackground(new Color(255, 240, 245));
		textFieldSHA3_224.setBounds(178, 269, 618, 21);
		contentPane.add(textFieldSHA3_224);
		textFieldSHA3_224.setColumns(10);
		
		JLabel lblhash = new JLabel("\u8FD9\u4EC5\u4EC5\u662F\u4E00\u4E2A\u4F60\u81EA\u5DF1\u505A\u7684HASH\u503C\u8BA1\u7B97\u5668\uFF01");
		lblhash.setForeground(new Color(51, 153, 204));
		lblhash.setFont(new Font("华文新魏", Font.PLAIN, 26));
		lblhash.setBounds(169, 40, 492, 31);
		contentPane.add(lblhash);
		
		JCheckBox chckbxSHA3_256 = new JCheckBox("SHA3-256");
		chckbxSHA3_256.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA3_256.setForeground(new Color(153, 102, 255));
		chckbxSHA3_256.setBounds(64, 306, 103, 23);
		contentPane.add(chckbxSHA3_256);
		
		JCheckBox chckbxSHA3_384 = new JCheckBox("SHA3-384");
		chckbxSHA3_384.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA3_384.setForeground(new Color(51, 204, 255));
		chckbxSHA3_384.setBounds(64, 340, 103, 23);
		contentPane.add(chckbxSHA3_384);
		
		JCheckBox chckbxSHA3_512 = new JCheckBox("SHA3-512");
		chckbxSHA3_512.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA3_512.setForeground(new Color(0, 153, 153));
		chckbxSHA3_512.setBounds(64, 381, 103, 23);
		contentPane.add(chckbxSHA3_512);
		
		JCheckBox chckbxSHA_224 = new JCheckBox("SHA-224");
		chckbxSHA_224.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA_224.setForeground(new Color(255, 153, 255));
		chckbxSHA_224.setBounds(64, 421, 103, 23);
		contentPane.add(chckbxSHA_224);
		
		JCheckBox chckbxSHA_256 = new JCheckBox("SHA-256");
		chckbxSHA_256.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA_256.setForeground(new Color(255, 102, 0));
		chckbxSHA_256.setBounds(64, 457, 103, 23);
		contentPane.add(chckbxSHA_256);
		
		JCheckBox chckbxSHA_384 = new JCheckBox("SHA-384");
		chckbxSHA_384.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA_384.setForeground(new Color(102, 204, 153));
		chckbxSHA_384.setBounds(64, 493, 103, 23);
		contentPane.add(chckbxSHA_384);
		
		JCheckBox chckbxSHA_512 = new JCheckBox("SHA-512");
		chckbxSHA_512.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA_512.setForeground(new Color(204, 51, 102));
		chckbxSHA_512.setBounds(64, 530, 93, 23);
		contentPane.add(chckbxSHA_512);
		
		JCheckBox chckbxSHA1 = new JCheckBox("SHA1");
		chckbxSHA1.setFont(new Font("宋体", Font.PLAIN, 15));
		chckbxSHA1.setForeground(new Color(204, 153, 255));
		chckbxSHA1.setBounds(64, 569, 84, 23);
		contentPane.add(chckbxSHA1);
		
		textFieldSHA3_256 = new JTextField();
		textFieldSHA3_256.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA3_256.setEditable(false);
		textFieldSHA3_256.setBackground(new Color(255, 245, 238));
		textFieldSHA3_256.setBounds(176, 307, 620, 21);
		contentPane.add(textFieldSHA3_256);
		textFieldSHA3_256.setColumns(10);
		
		textFieldSHA3_384 = new JTextField();
		textFieldSHA3_384.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA3_384.setEditable(false);
		textFieldSHA3_384.setBackground(new Color(255, 240, 245));
		textFieldSHA3_384.setBounds(176, 344, 620, 21);
		contentPane.add(textFieldSHA3_384);
		textFieldSHA3_384.setColumns(10);
		
		textFieldSHA3_512 = new JTextField();
		textFieldSHA3_512.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA3_512.setEditable(false);
		textFieldSHA3_512.setBackground(new Color(255, 240, 245));
		textFieldSHA3_512.setBounds(177, 383, 619, 21);
		contentPane.add(textFieldSHA3_512);
		textFieldSHA3_512.setColumns(10);
		
		textFieldSHA_224 = new JTextField();
		textFieldSHA_224.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA_224.setEditable(false);
		textFieldSHA_224.setBackground(new Color(255, 240, 245));
		textFieldSHA_224.setBounds(177, 422, 619, 21);
		contentPane.add(textFieldSHA_224);
		textFieldSHA_224.setColumns(10);
		
		textFieldSHA_256 = new JTextField();
		textFieldSHA_256.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA_256.setEditable(false);
		textFieldSHA_256.setBackground(new Color(255, 240, 245));
		textFieldSHA_256.setBounds(176, 458, 620, 21);
		contentPane.add(textFieldSHA_256);
		textFieldSHA_256.setColumns(10);
		
		textFieldSHA_384 = new JTextField();
		textFieldSHA_384.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA_384.setEditable(false);
		textFieldSHA_384.setBackground(new Color(255, 240, 245));
		textFieldSHA_384.setBounds(178, 493, 618, 21);
		contentPane.add(textFieldSHA_384);
		textFieldSHA_384.setColumns(10);
		
		textFieldSHA_512 = new JTextField();
		textFieldSHA_512.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA_512.setEditable(false);
		textFieldSHA_512.setBackground(new Color(255, 240, 245));
		textFieldSHA_512.setBounds(178, 531, 618, 21);
		contentPane.add(textFieldSHA_512);
		textFieldSHA_512.setColumns(10);
		
		textFieldSHA1 = new JTextField();
		textFieldSHA1.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textFieldSHA1.setEditable(false);
		textFieldSHA1.setBackground(new Color(255, 245, 238));
		textFieldSHA1.setBounds(179, 570, 617, 21);
		contentPane.add(textFieldSHA1);
		textFieldSHA1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u52A0\u5BC6\u6587\u4EF6\u9009\u62E9\uFF1A");
		lblNewLabel.setForeground(new Color(51, 153, 204));
		lblNewLabel.setFont(new Font("华文新魏", Font.PLAIN, 22));
		lblNewLabel.setBounds(41, 140, 165, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblhashhash = new JLabel("\uFF08\u6587\u4EF6\u7684HASH\u540E\u4F1A\u5728\u539F\u76EE\u5F55\u4E0B\u751F\u6210\u5BF9\u5E94HASH\u7B97\u6CD5\u4F5C\u4E3A\u6587\u4EF6\u540D\u79F0\u7684\u6458\u8981\uFF09");
		lblhashhash.setFont(new Font("华文新魏", Font.PLAIN, 14));
		lblhashhash.setForeground(new Color(0, 153, 204));
		lblhashhash.setBounds(143, 181, 458, 36);
		contentPane.add(lblhashhash);
		
		Label label_2 = new Label("\uFF08\u5DF2\u5931\u6548\uFF09");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("隶书", Font.PLAIN, 12));
		label_2.setBounds(107, 229, 61, 23);
		contentPane.add(label_2);
		
		textFieldFilePath = new JTextField();
		textFieldFilePath.setForeground(Color.BLACK);
		textFieldFilePath.setColumns(10);
		textFieldFilePath.setBackground(new Color(255, 240, 245));
		textFieldFilePath.setBounds(197, 146, 473, 23);
		contentPane.add(textFieldFilePath);
		
		JButton btnFilePath = new JButton("...");
		btnFilePath.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser  =new JFileChooser("测试\\Hash测试");
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
					
				}
			}
		});
		btnFilePath.setBackground(new Color(255, 250, 240));
		btnFilePath.setBounds(724, 145, 84, 23);
		contentPane.add(btnFilePath);
		
		JButton btnClose = new JButton("\u6E05\u7A7A");
		btnClose.setFont(new Font("隶书", Font.PLAIN, 18));
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setBorder(new LineBorder(new Color(51, 153, 204)));
		btnClose.setForeground(new Color(135, 206, 235));
		btnClose.setBackground(new Color(245, 245, 245));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxSHA3_224.setSelected(false);
				textFieldSHA3_224.setText(null);
				chckbxSHA3_256.setSelected(false);
				textFieldSHA3_256.setText(null);
				chckbxSHA3_384.setSelected(false);
				textFieldSHA3_384.setText(null);
				chckbxSHA3_512.setSelected(false);
				textFieldSHA3_512.setText(null);
				chckbxSHA_224.setSelected(false);
				textFieldSHA_224.setText(null);
				chckbxSHA_256.setSelected(false);
				textFieldSHA_256.setText(null);
				chckbxSHA_384.setSelected(false);
				textFieldSHA_384.setText(null);
				chckbxSHA_512.setSelected(false);
				textFieldSHA_512.setText(null);
				chckbxSHA1.setSelected(false);
				textFieldSHA1.setText(null);
			}
		});
		btnClose.setBounds(368, 622, 93, 37);
		contentPane.add(btnClose);
		
		JCheckBox checkBoxInPutMassage = new JCheckBox("");
		checkBoxInPutMassage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkBoxInPutMassage.setBounds(12, 105, 23, 23);
		contentPane.add(checkBoxInPutMassage);
		
		JCheckBox chckBoxInputFile = new JCheckBox("");
		chckBoxInputFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckBoxInputFile.setBounds(12, 136, 23, 35);
		contentPane.add(chckBoxInputFile);
		
		JButton btnCalculate = new JButton("\u8BA1\u7B97");
		btnCalculate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCalculate.setBorder(new LineBorder(new Color(102, 153, 204)));
		btnCalculate.setForeground(new Color(135, 206, 235));
		btnCalculate.setBackground(new Color(245, 245, 245));
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			 if(checkBoxInPutMassage.isSelected())           //字符串不为空时为字符串进行消息摘要
			{
				 boolean selectjudge=false;
				/*	if(chckbxMD5.isSelected())
				{
					MessageDigest md=MessageDigest.getInstance("MD5");
					byte[] msg=textFieldInPutMassage.getText().getBytes();
					
					md.update(msg);
					//可多次update,hash值为综合消息的哈希值
					
					byte[] digestvalue=md.digest();
					textFieldMD5.setText(Hex.toHexString(digestvalue).toUpperCase());
				}*/
					
					if(chckbxSHA3_224.isSelected())
					{   
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-224");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA3_224.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA3_256.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-256");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA3_256.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA3_384.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-384");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA3_384.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA3_512.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-512");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA3_512.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA_224.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-224");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA_224.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA_256.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-256");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA_256.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA_384.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-384");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA_384.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA_512.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-512");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA_512.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(chckbxSHA1.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-1");
						byte[] msg=textFieldInPutMassage.getText().getBytes();
						
						md.update(msg);
						//可多次update,hash值为综合消息的哈希值
						
						byte[] digestvalue=md.digest();
						textFieldSHA1.setText(Hex.toHexString(digestvalue).toUpperCase());
					}
					if(!selectjudge)
					{
						JOptionPane.showMessageDialog(null, "请选择算法！！！");
					}
			}
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			if(chckBoxInputFile.isSelected())
			{
				try {
					String fileName=textFieldFilePath.getText();
					

					FileInputStream fis;

					fis = new FileInputStream(fileName);//输入流，通用一个
					
					boolean selectjudge=false;
					if(chckbxSHA3_224.isSelected())
					{
						   selectjudge=true;
							MessageDigest md=MessageDigest.getInstance("SHA3-224");
							byte[] buffer=new byte[1024];
							int n=0;
							while((n=fis.read(buffer))!=-1)
							{
								md.update(buffer);
							}
							
							byte[] digestvalue=md.digest();
							
							String hashFileName=fileName;
							hashFileName=hashFileName+"SHA3-224.txt";
							FileOutputStream fos =new FileOutputStream(hashFileName);
							fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
							fos.close();                           //输出流关闭
					}
					if(chckbxSHA3_256.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-256");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA3-256.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA3_384.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-384");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA3-384.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA3_512.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA3-512");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA3-512.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA_224.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-224");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA-224.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA_256.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-256");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA-256.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA_384.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-384");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA-384.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA_512.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-512");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA-512.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					if(chckbxSHA1.isSelected())
					{
						selectjudge=true;
						MessageDigest md=MessageDigest.getInstance("SHA-1");
						byte[] buffer=new byte[1024];
						int n=0;
						while((n=fis.read(buffer))!=-1)
						{
							md.update(buffer);
						}
						
						byte[] digestvalue=md.digest();
						
						String hashFileName=fileName;
						hashFileName=hashFileName+"SHA1.txt";
						FileOutputStream fos =new FileOutputStream(hashFileName);
						fos.write(Hex.toHexString(digestvalue).toUpperCase().getBytes());
						fos.close();                           //输出流关闭
					}
					
					fis.close();                             //输入流关闭
					if(selectjudge){
						JOptionPane.showMessageDialog(null, "文件摘要成功！(生成的hash文件在原文件目录下)");
					}
					else{
						JOptionPane.showMessageDialog(null, "请选择算法！！！");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "未找到文件,失败！");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "没找到该算法！");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "输出路径有误！");
					e1.printStackTrace();
				}
			}
			if((!chckBoxInputFile.isSelected())&(!checkBoxInPutMassage.isSelected()))
			{
				JOptionPane.showMessageDialog(null, "请勾选操作项！");
			}
			}
		});
		btnCalculate.setFont(new Font("隶书", Font.PLAIN, 18));
		btnCalculate.setBounds(63, 623, 104, 36);
		contentPane.add(btnCalculate);
		
		
	}
}

/*public class HashTool extends JFrame {

	private JPanel contentPane;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HashTool frame = new HashTool();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the frame.
	 *//*
	public HashTool() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
*/