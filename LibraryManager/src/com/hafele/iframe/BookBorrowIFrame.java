package com.hafele.iframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.BookInfoDao;
import com.hafele.dao.BookStyleDao;
import com.hafele.dao.BorrowDao;
import com.hafele.dao.ReaderInfoDao;
import com.hafele.model.Admin;
import com.hafele.model.BookInfo;
import com.hafele.model.BookStyle;
import com.hafele.model.Borrow;
import com.hafele.model.ReadersInfo;
import com.hafele.util.MapUtil;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��10��6�� ����11:08:20
* ͼ�����������
*/
@SuppressWarnings("serial")
public class BookBorrowIFrame extends JInternalFrame {
	private JTable table;
	private JTextField currentlyDateTxt;
	private JTextField adminTxt;
	private JTextField bookIDTxt;
	private JTextField bookNameTxt;
	private JTextField bookTypeTxt;
	private JTextField bookPriceTxt;
	private JTextField readerIDTxt;
	private JTextField readerNameTxt;
	private JTextField readerPhoneTxt;
	private JTextField readerDepositTxt;
	private Map<?, ?> map = MapUtil.getMap();
	private DefaultTableModel model = new DefaultTableModel();
	private String[] columnNames = { "ͼ����", "��������", "Ӧ������", "���߱��" };
	private SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Admin userName = BookLoginIFrame.getUser(); 
	private int getBorrow(List<?> list){
		//tag����1ʱ����ʾ�ñ��ͼ���ѽ����δ�黹
		int tag = 0;
		Object[] bookId = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			Borrow borrow = (Borrow) list.get(i);
			bookId[i] = borrow.getBookId();
			if(bookId[i].equals(bookIDTxt.getText())) {
				tag = 1;
			}
		}
		return tag;
	}
		

	//������д��table�ؼ���
	@SuppressWarnings("deprecation")
	private void add() {
		String str[] = new String[4];
		str[0] = bookIDTxt.getText().trim();
		str[1] = String.valueOf(myfmt.format(new java.util.Date()));
		str[2] = getBackTime().toLocaleString();
		str[3] = readerIDTxt.getText().trim();
		model.addRow(str);
	}

	//���㻹������
	@SuppressWarnings("deprecation")
	private Date getBackTime() {
		String days = "0";
		List<BookStyle> list = BookStyleDao.selectBookStyle(bookTypeTxt.getText().trim());
		for(int i = 0; i < list.size(); i++) {
			BookStyle bookType = (BookStyle) list.get(i);
			days = bookType.getBorrowDays();
		}
		java.util.Date date = new java.util.Date();
		date.setDate(date.getDate() + Integer.parseInt(days));
		return date;
	}
	
	/**
	 * Create the frame.
	 */
	public BookBorrowIFrame() {
		setTitle("ͼ��ݽ��Ĺ���");
		setFrameIcon(new ImageIcon(BookBorrowIFrame.class.getResource("/icon/borrow_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 550, 470);
		
		JPanel panel_readerInfo = new JPanel();
		
		JPanel panel_bookInfo = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_admin = new JPanel();
		
		JPanel panel_button = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_readerInfo, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_bookInfo, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
							.addComponent(panel_admin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_bookInfo, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_readerInfo, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_admin, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel readerIDLabel = new JLabel("���߱�ţ�");
		
		JLabel readerNameLabel = new JLabel("����������");
		
		JLabel readerPhoneLabel = new JLabel("��ϵ�绰��");
		
		JLabel readerDepositLabel = new JLabel("Ѻ��");
		
		readerIDTxt = new JTextField();
		readerIDTxt.setColumns(10);
		readerIDTxt.addKeyListener(new ISBNListenerlostFocus());
		
		readerNameTxt = new JTextField();
		readerNameTxt.setColumns(10);
		readerNameTxt.setEditable(false);
		
		readerPhoneTxt = new JTextField();
		readerPhoneTxt.setColumns(10);
		readerPhoneTxt.setEditable(false);
		
		readerDepositTxt = new JTextField();
		readerDepositTxt.setColumns(10);
		readerDepositTxt.setEditable(false);
		
		GroupLayout gl_panel_readerInfo = new GroupLayout(panel_readerInfo);
		gl_panel_readerInfo.setHorizontalGroup(
			gl_panel_readerInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_readerInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_readerInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_readerInfo.createSequentialGroup()
							.addComponent(readerIDLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerIDTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_readerInfo.createSequentialGroup()
							.addComponent(readerNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerNameTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_readerInfo.createSequentialGroup()
							.addComponent(readerPhoneLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerPhoneTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_readerInfo.createSequentialGroup()
							.addComponent(readerDepositLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerDepositTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_panel_readerInfo.setVerticalGroup(
			gl_panel_readerInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_readerInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_readerInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(readerIDLabel)
						.addComponent(readerIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_readerInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(readerNameLabel)
						.addComponent(readerNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_readerInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(readerPhoneLabel)
						.addComponent(readerPhoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_readerInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(readerDepositLabel)
						.addComponent(readerDepositTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel_readerInfo.setLayout(gl_panel_readerInfo);
		
		JLabel bookIDLabel = new JLabel("ͼ���ţ�");
		
		JLabel bookNameLabel = new JLabel("ͼ�����ƣ�");
		
		JLabel bookTypeLabel = new JLabel("ͼ�����");
		
		JLabel bookPriceLabel = new JLabel("ͼ��۸�");
		
		bookIDTxt = new JTextField();
		bookIDTxt.setColumns(10);
		bookIDTxt.addKeyListener(new bookISBNListenerlostFocus());
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		bookNameTxt.setEditable(false);
		
		bookTypeTxt = new JTextField();
		bookTypeTxt.setColumns(10);
		bookTypeTxt.setEditable(false);
		
		bookPriceTxt = new JTextField();
		bookPriceTxt.setColumns(10);
		bookPriceTxt.setEditable(false);
		
		GroupLayout gl_panel_bookInfo = new GroupLayout(panel_bookInfo);
		gl_panel_bookInfo.setHorizontalGroup(
			gl_panel_bookInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_bookInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_bookInfo.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_bookInfo.createSequentialGroup()
							.addComponent(bookIDLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookIDTxt))
						.addGroup(gl_panel_bookInfo.createSequentialGroup()
							.addComponent(bookNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_bookInfo.createSequentialGroup()
							.addComponent(bookTypeLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_bookInfo.createSequentialGroup()
							.addComponent(bookPriceLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookPriceTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel_bookInfo.setVerticalGroup(
			gl_panel_bookInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_bookInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_bookInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookIDLabel)
						.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_bookInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookNameLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_bookInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookTypeLabel)
						.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_bookInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookPriceLabel)
						.addComponent(bookPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		panel_bookInfo.setLayout(gl_panel_bookInfo);
		
		JLabel currentlyDateTimeLabel = new JLabel("��ǰʱ�䣺");
		
		JLabel currentlyAdminLabel = new JLabel("����Ա��");
		
		currentlyDateTxt = new JTextField();
		currentlyDateTxt.setColumns(10);
		currentlyDateTxt.setEditable(false);
		currentlyDateTxt.addActionListener(new TimeActionListener());
		
		adminTxt = new JTextField();
		adminTxt.setText(userName.getSys_name());
		adminTxt.setColumns(10);
		adminTxt.setEditable(false);
		
		GroupLayout gl_panel_admin = new GroupLayout(panel_admin);
		gl_panel_admin.setHorizontalGroup(
			gl_panel_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_admin.createSequentialGroup()
					.addGap(112)
					.addGroup(gl_panel_admin.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_admin.createSequentialGroup()
							.addComponent(currentlyDateTimeLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(currentlyDateTxt, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_admin.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(currentlyAdminLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(adminTxt, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_panel_admin.setVerticalGroup(
			gl_panel_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_admin.createSequentialGroup()
					.addGroup(gl_panel_admin.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentlyDateTimeLabel)
						.addComponent(currentlyDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_admin.createParallelGroup(Alignment.BASELINE)
						.addComponent(adminTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentlyAdminLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_admin.setLayout(gl_panel_admin);
		
		JButton borrowButton = new JButton("���");
		borrowButton.addActionListener(new BorrowActionListener());
		borrowButton.setIcon(new ImageIcon(BookBorrowIFrame.class.getResource("/icon/Borrow_Book_16px.png")));
		panel_button.add(borrowButton);
		
		JButton resetButton = new JButton("���");
		resetButton.addActionListener(new ClearActionListener(model));
		resetButton.setIcon(new ImageIcon(BookBorrowIFrame.class.getResource("/icon/reset_button_16px.png")));
		panel_button.add(resetButton);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		
		getContentPane().setLayout(groupLayout);

		//���ô���ɼ�
		this.setVisible(true);
	}
	
	//�����ť�����¼�
	class BorrowActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String bookId = bookIDTxt.getText().trim();
			String readerId = readerIDTxt.getText().trim();
			String borrowDate=myfmt.format(new java.util.Date());
			String backDate=myfmt.format(getBackTime());
			
			int i = BorrowDao.insertBorrow(bookId, readerId, java.sql.Timestamp.valueOf(borrowDate), java.sql.Timestamp.valueOf(backDate));
			int j = BookInfoDao.updateBookInfo(bookId,1);//1��ʾͼ����
			if(i == 1 && j == 1) {
				JOptionPane.showMessageDialog(null, "ͼ�������ɣ�");
				resertData();
			}else {
				JOptionPane.showMessageDialog(null, "ͼ�����ʧ�ܣ������²�����");
			}
		}
	}
	
	//�����ť�����¼�
	class ClearActionListener implements ActionListener{
		@SuppressWarnings("unused")
		private final DefaultTableModel model;

		ClearActionListener(DefaultTableModel model) {
			this.model = model;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getRowCount() != 0) {
				resertData();
			} else {
				JOptionPane.showMessageDialog(null, "�������ʱû�����ݣ�����н��Ĳ���");
			}
		}
	}
	
	//��ǰʱ������¼�
	class TimeActionListener implements ActionListener{

		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentlyDateTxt.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
	
	//���߱���ı���س��¼�����
	class ISBNListenerlostFocus extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '\n') {//�ж��ı����Ƿ�����س�
				String readerId = readerIDTxt.getText().trim();
				 List<ReadersInfo> list = ReaderInfoDao.selectReader(readerId);
				 if(list.isEmpty() && !readerId.isEmpty()) {
					 JOptionPane.showMessageDialog(null, "�˶��߱��û��ע�ᣬ��ѯ������߱���Ƿ�����");
				 }
				 for(int i = 0; i < list.size(); i++) {
					 ReadersInfo readersInfo = (ReadersInfo) list.get(i);
					 readerNameTxt.setText(readersInfo.getReaderName());
					 readerPhoneTxt.setText(readersInfo.getReaderTelNumber());
					 readerDepositTxt.setText(readersInfo.getGuaranteeDeposit().toString()+"Ԫ");
				 }
			}
		}
	}
	
	//ͼ�����ı���س��¼�����
	class bookISBNListenerlostFocus extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '\n') {//�ж��ı����Ƿ�����س�
				if(readerIDTxt.getText().trim().length() != 0 && bookIDTxt.getText().trim().length() != 0) {
					if(getBorrow(BorrowDao.selectBorrow()) == 1) {
						JOptionPane.showMessageDialog(null,
								"��ͼ���Ŷ�Ӧ��ͼ���ѽ����δ�黹��������ѡ��");
						return;
					}
					String bookId = bookIDTxt.getText().trim();
					List<BookInfo> list = BookInfoDao.selectBookInfo(bookId);
					for(int i = 0; i < list.size(); i++) {
						BookInfo bookInfo = (BookInfo) list.get(i);
						bookNameTxt.setText(bookInfo.getBookName());
						bookTypeTxt.setText(String.valueOf(map.get(bookInfo.getBookStyleNumber())));
						bookPriceTxt.setText(bookInfo.getBookPrice().toString() + "Ԫ");
					}
					String readerId = readerIDTxt.getText().trim();
					List<ReadersInfo> readerList = ReaderInfoDao.selectReader(readerId);//�˶����Ƿ������ݱ��д���
					List<BookInfo> bookList = BookInfoDao.selectBookInfo(bookId);//��ͼ���Ƿ������ݱ��д���
					if(!readerId.isEmpty() && readerList.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"�˶��߱��û��ע�ᣬ��ѯ������߱���Ƿ�����");
						return;
					}
					if(!bookId.isEmpty() && bookList.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"��ͼ���û�д��飬��ѯ����ͼ�����Ƿ�����");
						return;
					}
					add();
				}else {
					JOptionPane.showMessageDialog(null,
							"��������߻�ͼ�������룡");
					return;
				}
			} 
		}		
	}

	//�������
	public void resertData() {
		model.removeRow(table.getRowCount() - 1);
		readerIDTxt.setText(null);
		readerNameTxt.setText(null);
		readerPhoneTxt.setText(null);
		readerDepositTxt.setText(null);
		bookIDTxt.setText(null);
		bookNameTxt.setText(null);
		bookTypeTxt.setText(null);
		bookPriceTxt.setText(null);
	}
}
