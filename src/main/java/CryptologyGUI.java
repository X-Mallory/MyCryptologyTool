import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.DebugGraphics;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.security.Security;

import javax.swing.border.SoftBevelBorder;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class CryptologyGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					CryptologyGUI frame = new CryptologyGUI();
					frame.setLocationRelativeTo(null);
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
	public CryptologyGUI() {
		setResizable(false);
		setBackground(SystemColor.activeCaption);
		setType(Type.POPUP);
		setFont(new Font("隶书", Font.PLAIN, 18));
		setTitle("\u5BC6\u7801\u5B66\u52A9\u624B");
		setIconImage(Toolkit.getDefaultToolkit().getImage("t01d065a20589e6ac3c.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 681);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setForeground(new Color(65, 105, 225));
		contentPane.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(175, 238, 238)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5BC6\u7801\u5B66\u52A9\u624B");
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("华文隶书", Font.PLAIN, 40));
		label.setBounds(262, 10, 233, 98);
		contentPane.add(label);
		
		JButton btnEnCryptoButton = new JButton("\u6587\u4EF6\u52A0\u5BC6>>>");
		btnEnCryptoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnCryptoButton.setMnemonic(KeyEvent.VK_FIND);
		btnEnCryptoButton.setFocusable(false);
		btnEnCryptoButton.setFocusPainted(false);
		btnEnCryptoButton.setFocusTraversalKeysEnabled(false);
		btnEnCryptoButton.setDoubleBuffered(true);
		btnEnCryptoButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnEnCryptoButton.setBackground(SystemColor.controlHighlight);
		btnEnCryptoButton.setFont(new Font("隶书", Font.PLAIN, 25));
		btnEnCryptoButton.setForeground(new Color(30, 144, 255));
		btnEnCryptoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnCryptoFile enCryptoFile=new EnCryptoFile();
				enCryptoFile.setLocationRelativeTo(null);
				enCryptoFile.setVisible(true);
				enCryptoFile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btnEnCryptoButton.setBounds(84, 139, 250, 48);
		contentPane.add(btnEnCryptoButton);
		
		JButton btnDecrypto = new JButton("\u6587\u4EF6\u89E3\u5BC6>>>");
		btnDecrypto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDecrypto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeCryptoFile deCryptoFile=new DeCryptoFile();
				deCryptoFile.setLocationRelativeTo(null);
				deCryptoFile.setVisible(true);
				deCryptoFile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btnDecrypto.setMnemonic(KeyEvent.VK_FIND);
		btnDecrypto.setForeground(new Color(30, 144, 255));
		btnDecrypto.setFont(new Font("隶书", Font.PLAIN, 25));
		btnDecrypto.setFocusable(false);
		btnDecrypto.setFocusTraversalKeysEnabled(false);
		btnDecrypto.setFocusPainted(false);
		btnDecrypto.setDoubleBuffered(true);
		btnDecrypto.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnDecrypto.setBackground(SystemColor.controlHighlight);
		btnDecrypto.setBounds(427, 139, 247, 48);
		contentPane.add(btnDecrypto);
		
		JButton btnSignature = new JButton("\u6570\u5B57\u7B7E\u540D\u751F\u6210>>>");
		btnSignature.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignatureFile signature=new SignatureFile();
				signature.setLocationRelativeTo(null);
				signature.setVisible(true);
				signature.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btnSignature.setMnemonic(KeyEvent.VK_FIND);
		btnSignature.setForeground(new Color(30, 144, 255));
		btnSignature.setFont(new Font("隶书", Font.PLAIN, 25));
		btnSignature.setFocusable(false);
		btnSignature.setFocusTraversalKeysEnabled(false);
		btnSignature.setFocusPainted(false);
		btnSignature.setDoubleBuffered(true);
		btnSignature.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnSignature.setBackground(SystemColor.controlHighlight);
		btnSignature.setBounds(84, 227, 250, 48);
		contentPane.add(btnSignature);
		
		JButton btnVerifySignature = new JButton("\u6570\u5B57\u7B7E\u540D\u9A8C\u8BC1>>>");
		btnVerifySignature.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerifySignature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerifySignature verifySignature=new VerifySignature();
				verifySignature.setLocationRelativeTo(null);
				verifySignature.setVisible(true);
				verifySignature.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btnVerifySignature.setMnemonic(KeyEvent.VK_FIND);
		btnVerifySignature.setForeground(new Color(30, 144, 255));
		btnVerifySignature.setFont(new Font("隶书", Font.PLAIN, 25));
		btnVerifySignature.setFocusable(false);
		btnVerifySignature.setFocusTraversalKeysEnabled(false);
		btnVerifySignature.setFocusPainted(false);
		btnVerifySignature.setDoubleBuffered(true);
		btnVerifySignature.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnVerifySignature.setBackground(SystemColor.controlHighlight);
		btnVerifySignature.setBounds(427, 227, 247, 48);
		contentPane.add(btnVerifySignature);
		
		JButton btnMac = new JButton("MAC\u7801\u751F\u6210>>>");
		btnMac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MACValue macValue=new MACValue();
				macValue.setLocationRelativeTo(null);
				macValue.setVisible(true);
			    macValue.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btnMac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMac.setMnemonic(KeyEvent.VK_FIND);
		btnMac.setForeground(new Color(30, 144, 255));
		btnMac.setFont(new Font("隶书", Font.PLAIN, 25));
		btnMac.setFocusable(false);
		btnMac.setFocusTraversalKeysEnabled(false);
		btnMac.setFocusPainted(false);
		btnMac.setDoubleBuffered(true);
		btnMac.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnMac.setBackground(SystemColor.controlHighlight);
		btnMac.setBounds(84, 305, 250, 48);
		contentPane.add(btnMac);
		
		JButton btnVerifyMAC = new JButton("MAC\u7801\u6821\u9A8C>>>");
		btnVerifyMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerifyMAC verifyMAC=new VerifyMAC();
				verifyMAC.setLocationRelativeTo(null);
				verifyMAC.setVisible(true);
				verifyMAC.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btnVerifyMAC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerifyMAC.setMnemonic(KeyEvent.VK_FIND);
		btnVerifyMAC.setForeground(new Color(30, 144, 255));
		btnVerifyMAC.setFont(new Font("隶书", Font.PLAIN, 25));
		btnVerifyMAC.setFocusable(false);
		btnVerifyMAC.setFocusTraversalKeysEnabled(false);
		btnVerifyMAC.setFocusPainted(false);
		btnVerifyMAC.setDoubleBuffered(true);
		btnVerifyMAC.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnVerifyMAC.setBackground(SystemColor.controlHighlight);
		btnVerifyMAC.setBounds(427, 305, 247, 48);
		contentPane.add(btnVerifyMAC);
		
		JButton btnAESProperty = new JButton("AES\u7B97\u6CD5\u6027\u80FD\u56FE\u793A>>>");
		btnAESProperty.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnAESProperty.setMnemonic(KeyEvent.VK_FIND);
		btnAESProperty.setForeground(new Color(30, 144, 255));
		btnAESProperty.setFont(new Font("隶书", Font.PLAIN, 25));
		btnAESProperty.setFocusable(false);
		btnAESProperty.setFocusTraversalKeysEnabled(false);
		btnAESProperty.setFocusPainted(false);
		btnAESProperty.setDoubleBuffered(true);
		btnAESProperty.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnAESProperty.setBackground(SystemColor.controlHighlight);
		btnAESProperty.setBounds(84, 387, 250, 48);
		contentPane.add(btnAESProperty);
		
		JButton btnHashProperty = new JButton("HASH\u7B97\u6CD5\u6027\u80FD\u56FE\u793A>>>");
		btnHashProperty.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnHashProperty.setMnemonic(KeyEvent.VK_FIND);
		btnHashProperty.setForeground(new Color(30, 144, 255));
		btnHashProperty.setFont(new Font("隶书", Font.PLAIN, 25));
		btnHashProperty.setFocusable(false);
		btnHashProperty.setFocusTraversalKeysEnabled(false);
		btnHashProperty.setFocusPainted(false);
		btnHashProperty.setDoubleBuffered(true);
		btnHashProperty.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnHashProperty.setBackground(SystemColor.controlHighlight);
		btnHashProperty.setBounds(427, 387, 247, 48);
		contentPane.add(btnHashProperty);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(126, 472, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("___________________________________________________________________________________");
		label_2.setForeground(new Color(100, 149, 237));
		label_2.setBounds(84, 533, 661, 27);
		contentPane.add(label_2);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setForeground(new Color(100, 149, 237));
		lblAbout.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblAbout.setBounds(341, 570, 75, 27);
		contentPane.add(lblAbout);
		
		JLabel lblNewLabel = new JLabel("Cryptographic assistant  2017  By Suguoyu");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(223, 595, 342, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnHashValue = new JButton("HASH\u503C\u8BA1\u7B97>>>");
		btnHashValue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHashValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashTool hashTool=new HashTool();
				hashTool.setLocationRelativeTo(null);
				hashTool.setVisible(true);  
		        hashTool.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
			}
		});
		btnHashValue.setMnemonic(KeyEvent.VK_FIND);
		btnHashValue.setForeground(new Color(30, 144, 255));
		btnHashValue.setFont(new Font("隶书", Font.PLAIN, 25));
		btnHashValue.setFocusable(false);
		btnHashValue.setFocusTraversalKeysEnabled(false);
		btnHashValue.setFocusPainted(false);
		btnHashValue.setDoubleBuffered(true);
		btnHashValue.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(135, 206, 250), new Color(30, 144, 255), new Color(135, 206, 235)));
		btnHashValue.setBackground(SystemColor.controlHighlight);
		btnHashValue.setBounds(84, 463, 250, 48);
		contentPane.add(btnHashValue);
	}
}
