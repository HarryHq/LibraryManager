package com.hafele;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.hafele.iframe.BookLoginIFrame;


/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��9��20�� ����9:58:53
* ͼ�����ϵͳ������
*/
@SuppressWarnings("serial")
public class LibraryMain extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	//�������
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new BookLoginIFrame();//��¼����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);
		iframe.toFront();//���嶥��
	}
	//�����幹�췽��
	public LibraryMain() {
		setTitle("ͼ��ݹ���ϵͳ");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(800, 720);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LibraryMain.class.getResource("/icon/book_200px.png")));
		JMenuBar menuBar = createMenu(); // ���ô����˵����ķ���
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // ���ô����������ķ���
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // ���屳��

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/icon/backImg.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
		//���ô������
		this.setLocationRelativeTo(null);
		
		
	}
	
	//�����˵���
	public JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu basicDataMaintenance = new JMenu("��������ά��");
		basicDataMaintenance.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/data_16px.png")));
		menuBar.add(basicDataMaintenance);
		
		JMenu readerInformationManagement = new JMenu("������Ϣ����");
		readerInformationManagement.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/read_16px.png")));
		basicDataMaintenance.add(readerInformationManagement);
		
		JMenuItem readerInformationAdd = new JMenuItem("������Ϣ���");
		readerInformationAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/add user_16px.png")));
		readerInformationManagement.add(readerInformationAdd);
		readerInformationAdd.addActionListener(MenuActions.READER_ADD);
		
		JMenuItem readerInformationSetting = new JMenuItem("������Ϣ�޸�");
		readerInformationSetting.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/change_16px.png")));
		readerInformationManagement.add(readerInformationSetting);
		readerInformationSetting.addActionListener(MenuActions.READER_MODIFY);
		
		JMenu bookTypeManagement = new JMenu("ͼ��������");
		bookTypeManagement.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/type_16px.png")));
		basicDataMaintenance.add(bookTypeManagement);
		
		JMenuItem bookTypeAdd = new JMenuItem("ͼ��������");
		bookTypeAdd.addActionListener(MenuActions.BOOKSTYLE_ADD);
		bookTypeAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/add_16px.png")));
		bookTypeManagement.add(bookTypeAdd);
		
		JMenuItem bookTypeModify = new JMenuItem("ͼ������޸�");
		bookTypeModify.addActionListener(MenuActions.BOOKTYPE_MODIFY);
		bookTypeModify.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/change_16px.png")));
		bookTypeManagement.add(bookTypeModify);
		
		JMenu bookInformationManagement = new JMenu("ͼ����Ϣ����");
		bookInformationManagement.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/info_16px.png")));
		basicDataMaintenance.add(bookInformationManagement);
		
		JMenuItem bookInformationAdd = new JMenuItem("ͼ����Ϣ���");
		bookInformationAdd.addActionListener(MenuActions.BOOK_ADD);
		bookInformationAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/add_16px.png")));
		bookInformationManagement.add(bookInformationAdd);
		
		JMenuItem bookInformationModify = new JMenuItem("ͼ����Ϣ�޸�");
		bookInformationModify.addActionListener(MenuActions.BOOK_MODIFY);
		bookInformationModify.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/change_16px.png")));
		bookInformationManagement.add(bookInformationModify);
		
		JMenuItem safeExitSystem = new JMenuItem("��ȫ�˳�");
		safeExitSystem.addActionListener(MenuActions.EXIT);
		
		JMenuItem switchUser = new JMenuItem("�л��û���¼");
		switchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BookLoginIFrame().setVisible(true);
			}
		});
		switchUser.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/switch_user_16px.png")));
		basicDataMaintenance.add(switchUser);
		safeExitSystem.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/logoff_16px.png")));
		basicDataMaintenance.add(safeExitSystem);
		
		JMenu bookBorrowManagement = new JMenu("���Ĺ���");
		bookBorrowManagement.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/read_16px.png")));
		menuBar.add(bookBorrowManagement);
		
		JMenuItem bookBorrow = new JMenuItem("ͼ�����");
		bookBorrow.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/Borrow_Book_16px.png")));
		bookBorrow.addActionListener(MenuActions.BORROW);
		bookBorrowManagement.add(bookBorrow);
		
		JMenuItem bookReturn = new JMenuItem("ͼ��黹");
		bookReturn.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/return_book_16px.png")));
		bookReturn.addActionListener(MenuActions.GIVE_BACK);
		bookBorrowManagement.add(bookReturn);
		
		JMenuItem bookSearch = new JMenuItem("ͼ������");
		bookSearch.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/Book_Search_16px.png")));
		bookSearch.addActionListener(MenuActions.BOOK_SEARCH);
		bookBorrowManagement.add(bookSearch);
		
		JMenu systemMaintenance = new JMenu("ϵͳά��");
		systemMaintenance.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/settings_16px.png")));
		menuBar.add(systemMaintenance);
		
		JMenuItem modifyPassword = new JMenuItem("���Ŀ���");
		modifyPassword.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/change_password_16px.png")));
		modifyPassword.addActionListener(MenuActions.MODIFY_PASSWORD);
		systemMaintenance.add(modifyPassword);
		
		
		JMenu userManagement = new JMenu("�û�����");
		userManagement.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/reader_16px.png")));
		systemMaintenance.add(userManagement);
		
		JMenuItem adminUserAdd = new JMenuItem("�û����");
		adminUserAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/User_add_16px.png")));
		adminUserAdd.addActionListener(MenuActions.USER_ADD);
		userManagement.add(adminUserAdd);
		
		JMenuItem adminUserModify = new JMenuItem("�û��޸���ɾ��");
		adminUserModify.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/User_Settings_16px.png")));
		adminUserModify.addActionListener(MenuActions.USER_MODIFY);
		userManagement.add(adminUserModify);
		
		JMenu helper = new JMenu("����");
		helper.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/help_16px.png")));
		menuBar.add(helper);
		
		JMenuItem aboutUs = new JMenuItem("��������");
		aboutUs.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/aboutus_16px.png")));
		aboutUs.addActionListener(MenuActions.ABOUT_US);
		helper.add(aboutUs);
		return menuBar;
	}
	
	//����������
	private JToolBar createToolBar(){
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(SystemColor.controlHighlight);
		toolBar.setFloatable(false);
		
		JButton buttonBookInfoAdd = new JButton(MenuActions.BOOK_ADD);
		buttonBookInfoAdd.setBackground(SystemColor.controlHighlight);
		buttonBookInfoAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/bookAddtb.png")));
		toolBar.add(buttonBookInfoAdd);
		
		JButton buttonBookInfoModify = new JButton(MenuActions.BOOK_MODIFY);
		buttonBookInfoModify.setBackground(SystemColor.controlHighlight);
		buttonBookInfoModify.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/bookModiAndDeltb.png")));
		toolBar.add(buttonBookInfoModify);
		
		JButton buttonBookTypeAdd = new JButton(MenuActions.BOOKSTYLE_ADD);
		buttonBookTypeAdd.setBackground(SystemColor.controlHighlight);
		buttonBookTypeAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/bookTypeAddtb.png")));
		toolBar.add(buttonBookTypeAdd);
		
		JButton buttonBookBorrowManagement = new JButton(MenuActions.BORROW);
		buttonBookBorrowManagement.setBackground(SystemColor.controlHighlight);
		buttonBookBorrowManagement.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/bookBorrowtb.png")));
		toolBar.add(buttonBookBorrowManagement);
		
		JButton buttonNewBookCheck = new JButton(MenuActions.BOOK_SEARCH);
		buttonNewBookCheck.setBackground(SystemColor.controlHighlight);
		buttonNewBookCheck.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/newbookChecktb.png")));
		toolBar.add(buttonNewBookCheck);
		
		JButton buttonReaderInfoAdd = new JButton(MenuActions.READER_ADD);
		buttonReaderInfoAdd.setBackground(SystemColor.controlHighlight);
		buttonReaderInfoAdd.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/readerAddtb.png")));
		toolBar.add(buttonReaderInfoAdd);
		
		JButton buttonReaderInfoModify = new JButton(MenuActions.READER_MODIFY);
		buttonReaderInfoModify.setBackground(SystemColor.controlHighlight);
		buttonReaderInfoModify.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/readerModiAndDeltb.png")));
		toolBar.add(buttonReaderInfoModify);
		
		JButton buttonExit = new JButton(MenuActions.EXIT);
		buttonExit.setBackground(SystemColor.controlHighlight);
		buttonExit.setIcon(new ImageIcon(LibraryMain.class.getResource("/icon/exittb.png")));
		toolBar.add(buttonExit);
		
		return toolBar;
	}
}
