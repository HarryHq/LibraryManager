package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hafele.dao.AdminDao;
import com.hafele.model.Admin;
import com.hafele.util.MD5Util;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��9��27�� ����5:06:02
* �޸����봰��
*/
@SuppressWarnings("serial")
public class ChangePasswordIFrame extends JInternalFrame {
	private JTextField userNameTxt;
	private JPasswordField oldPasswordTxt;
	private JPasswordField newPasswordTxt;
	private JPasswordField verifyNewPasswordTxt;
	private JButton modifyButton;
	private JButton resetButton;
	private Admin userName = BookLoginIFrame.getUser(); 

	public ChangePasswordIFrame() {
		super();
		setTitle("�޸�����");
		setFrameIcon(new ImageIcon(ChangePasswordIFrame.class.getResource("/icon/change_password_16px.png")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 300, 270);
		
		JLabel noticeLabel = new JLabel("<html>\u6CE8\uFF1A\u6BCF\u4E2A<b>\u64CD\u4F5C\u5458</b>\u53EA\u80FD\u4FEE\u6539\u81EA\u5DF1\u7684\u5BC6\u7801\u3002</html>");
		noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noticeLabel.setForeground(Color.RED);
		noticeLabel.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		getContentPane().add(noticeLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("\u767B  \u5F55  \u540D\uFF1A");
		
		JLabel label = new JLabel("\u65E7  \u5BC6  \u7801\uFF1A");
		
		JLabel label_1 = new JLabel("\u65B0  \u5BC6  \u7801\uFF1A");
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		
		userNameTxt = new JTextField();
		userNameTxt.setText(userName.getSys_name());
		userNameTxt.setColumns(10);
		
		oldPasswordTxt = new JPasswordField();
		oldPasswordTxt.setColumns(10);
		oldPasswordTxt.setEchoChar('*');//���������Ļ����ַ�
		
		newPasswordTxt = new JPasswordField();
		newPasswordTxt.setColumns(10);
		newPasswordTxt.setEchoChar('*');//���������Ļ����ַ�
		
		verifyNewPasswordTxt = new JPasswordField();
		verifyNewPasswordTxt.setColumns(10);
		verifyNewPasswordTxt.setEchoChar('*');//���������Ļ����ַ�
		verifyNewPasswordTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					modifyButton.doClick();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(oldPasswordTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPasswordTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(verifyNewPasswordTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(oldPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(newPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(verifyNewPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panelButton = new JPanel();
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String oldPassword =new String(oldPasswordTxt.getPassword());
				String newPassword =new String(newPasswordTxt.getPassword());
				String verifyNewPassword =new String(verifyNewPasswordTxt.getPassword());
				String oldPasswordMD5 = MD5Util.convertMD5(oldPassword);
				String newPasswordMD5 = MD5Util.convertMD5(newPassword);
				String verifyNewPasswordMD5 = MD5Util.convertMD5(verifyNewPassword);
//				System.out.println("�����룺"+oldPasswordMD5+"����:1��"+newPasswordMD5+"������2��"+verifyNewPasswordMD5);
				if(oldPasswordMD5.equals(userName.getSys_password())) {
					if(newPasswordMD5.equals(verifyNewPasswordMD5)) {
						userName.setSys_password(newPasswordMD5);
						int i = AdminDao.updatePassword(userName.getSys_password(),userName.getSys_name());
						if(i == 1) {
							oldPasswordTxt.setText(null);
							newPasswordTxt.setText(null);
							verifyNewPasswordTxt.setText(null);
							JOptionPane.showMessageDialog(getContentPane(), "�����޸ĳɹ���");
							doDefaultCloseAction();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "�����޸�ʧ�ܡ�");
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "������������벻һ�£����������롣");
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "���������������ȷ�����롣");
				}
			}
		});
		modifyButton.setIcon(new ImageIcon(ChangePasswordIFrame.class.getResource("/icon/change_16px.png")));
		panelButton.add(modifyButton);
		
		resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				oldPasswordTxt.setText(null);
				newPasswordTxt.setText(null);
				verifyNewPasswordTxt.setText(null);
			}
		});
		resetButton.setIcon(new ImageIcon(ChangePasswordIFrame.class.getResource("/icon/reset_button_16px.png")));
		panelButton.add(resetButton);
		
		//���ô���ɼ�
		this.setVisible(true);
	}
}
