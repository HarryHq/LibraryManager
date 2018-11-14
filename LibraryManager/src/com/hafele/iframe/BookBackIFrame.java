package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.BackBookDao;
import com.hafele.dao.BookInfoDao;
import com.hafele.dao.BookStyleDao;
import com.hafele.model.Admin;
import com.hafele.model.BackBook;
import com.hafele.model.BookStyle;
import com.hafele.util.MapUtil;
import com.hafele.util.MyDocument;

/**
* ͼ��黹����ʵ����
*/
@SuppressWarnings("serial")
public class BookBackIFrame extends JInternalFrame {

	private JTable table;
	private JTextField adminTxt;
	private JTextField currentlyDateTxt;
	private JTextField amerceMoneyTxt;
	private JTextField exceedDaysTxt;
	private JTextField realDaysTxt;
	private JTextField allotedDaysTxt;
	private JTextField borrowDateTxt;
	private JTextField readerIdTxt;
	private String[] columnNames = { "ͼ������", "ͼ��������","ͼ�����","��������","����������","����ʱ��","�黹ʱ��" };
	private DefaultTableModel model = new DefaultTableModel();
	private SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Admin userName = BookLoginIFrame.getUser(); 
	private String bookId=null;
	private String readerId=null;
	private String bookName=null;
	private String readerName=null;
	private String bookFee=null;
	private String borrowDate=null;
	private String returnDate=null;
	private void Resert() {
		borrowDateTxt.setText(null);
		allotedDaysTxt.setText(null);
		realDaysTxt.setText(null);
		allotedDaysTxt.setText(null);
		realDaysTxt.setText(null);
		amerceMoneyTxt.setText(null);
	}
	/**
	 * Create the frame.
	 */
	public BookBackIFrame() {
		setTitle("ͼ��黹����");
		setFrameIcon(new ImageIcon(BookBackIFrame.class.getResource("/icon/return_book_16px.png")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 480);

		final JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		basicInfoPanel.setPreferredSize(new Dimension(0, 200));
		getContentPane().add(basicInfoPanel, BorderLayout.NORTH);

		final JPanel readerIdPanel = new JPanel();
		final GridLayout gl_readerIdPanel = new GridLayout(0, 2);
		gl_readerIdPanel.setVgap(5);
		readerIdPanel.setLayout(gl_readerIdPanel);
		readerIdPanel.setPreferredSize(new Dimension(400, 20));
		basicInfoPanel.add(readerIdPanel);

		final JLabel readerIdLabel = new JLabel();
		readerIdLabel.setText("���߱�ţ�");
		readerIdPanel.add(readerIdLabel);

		readerIdTxt = new JTextField();
		readerIdTxt.setDocument(new MyDocument(13));
		readerIdTxt.addKeyListener(new readerISBNListenerlostFocus(model));
		readerIdPanel.add(readerIdTxt);

		final JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout());
		tablePanel.setPreferredSize(new Dimension(450, 130));
		basicInfoPanel.add(tablePanel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 120));
		tablePanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.addMouseListener(new TableListener());
		

		final JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		final JPanel fineMoneyPanel = new JPanel();
		final GridLayout gl_fineMoneyPanel = new GridLayout(0, 2);
		gl_fineMoneyPanel.setVgap(20);
		fineMoneyPanel.setLayout(gl_fineMoneyPanel);
		fineMoneyPanel.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		fineMoneyPanel.setPreferredSize(new Dimension(250, 230));
		panel_1.add(fineMoneyPanel);

		final JLabel borrowDateLabel = new JLabel();
		borrowDateLabel.setText("�������ڣ�");
		fineMoneyPanel.add(borrowDateLabel);

		borrowDateTxt = new JTextField();
		borrowDateTxt.setEditable(false);

		fineMoneyPanel.add(borrowDateTxt);

		final JLabel borrowdaysLabel = new JLabel();
		borrowdaysLabel.setText("�涨������");
		fineMoneyPanel.add(borrowdaysLabel);

		allotedDaysTxt = new JTextField();
		allotedDaysTxt.setEditable(false);
		fineMoneyPanel.add(allotedDaysTxt);

		final JLabel realDaysLabel = new JLabel();
		realDaysLabel.setText("ʵ��������");
		fineMoneyPanel.add(realDaysLabel);

		realDaysTxt = new JTextField();
		realDaysTxt.setEditable(false);
		fineMoneyPanel.add(realDaysTxt);

		final JLabel exceedDaysLabel = new JLabel();
		exceedDaysLabel.setText("����������");
		fineMoneyPanel.add(exceedDaysLabel);

		exceedDaysTxt = new JTextField();
		exceedDaysTxt.setEditable(false);
		fineMoneyPanel.add(exceedDaysTxt);

		final JLabel fineMoneyLabel = new JLabel();
		fineMoneyLabel.setText("�����");
		fineMoneyPanel.add(fineMoneyLabel);

		amerceMoneyTxt = new JTextField();
		amerceMoneyTxt.setEditable(false);
		fineMoneyPanel.add(amerceMoneyTxt);

		final JPanel systemPanel = new JPanel();
		systemPanel.setBorder(new TitledBorder(null, "ϵͳ��Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		systemPanel.setPreferredSize(new Dimension(280, 230));
		panel_1.add(systemPanel);

		final JPanel panel_7 = new JPanel();
		final GridLayout gridLayout_3 = new GridLayout(0, 2);
		gridLayout_3.setVgap(35);
		panel_7.setLayout(gridLayout_3);
		panel_7.setPreferredSize(new Dimension(260, 90));
		systemPanel.add(panel_7);

		final JLabel currentlyDateLabel = new JLabel();
		currentlyDateLabel.setText("��ǰʱ�䣺");
		panel_7.add(currentlyDateLabel);

		currentlyDateTxt = new JTextField();
		currentlyDateTxt.setEditable(false);
		currentlyDateTxt.setPreferredSize(new Dimension(0, 0));
		currentlyDateTxt.addActionListener(new TimeActionListener());
		currentlyDateTxt.setFocusable(false);
		panel_7.add(currentlyDateTxt);

		final JLabel adminLabel = new JLabel();
		adminLabel.setText("����Ա��");
		panel_7.add(adminLabel);

		adminTxt  =new JTextField(userName.getSys_name());
		adminTxt.setEditable(false);
		panel_7.add(adminTxt);

		final JButton buttonback = new JButton();
		buttonback.setIcon(new ImageIcon(BookBackIFrame.class.getResource("/icon/return_book_16px.png")));
		buttonback.setText("�黹");
		buttonback.addActionListener(new BookBackActionListener(model));
		systemPanel.add(buttonback);

		final JButton buttonExit= new JButton();
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		buttonExit.setIcon(new ImageIcon(BookBackIFrame.class.getResource("/icon/logoff_16px.png")));
		buttonExit.setText("�˳�");
		systemPanel.add(buttonExit);
		setVisible(true);
	}
	
	//ͼ��黹��ť����
	class BookBackActionListener implements ActionListener{
		private final DefaultTableModel model;

		BookBackActionListener(DefaultTableModel model) {
			this.model = model;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(readerId == null){
				JOptionPane.showMessageDialog(null, "��������߱�ţ�");
				return;
			}
			System.out.println(readerId == null);

			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�黹��ͼ�飡");
				return;	
			}
			
			int i = BackBookDao.updateBookBack(readerId,readerName,bookId,bookName,bookFee,borrowDate,returnDate);
			int j = BookInfoDao.updateBookInfo(bookId, 0);
			if(i == 1 && j == 1) {
				int selectedRow = table.getSelectedRow();
				model.removeRow(selectedRow);
				Resert();
				JOptionPane.showMessageDialog(null, "���������ɣ�");
			}
		}
	}
	
	//���table�����ݼ����¼�
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			java.util.Date currentDate=new java.util.Date();
			String bookAmerce = "";
			String allotedDays = "";
			int totalDays;
			int overdueDays;
			int selRow = table.getSelectedRow();
			List<BookStyle> list = BookStyleDao.selectBookStyle(table.getValueAt(selRow, 2).toString().trim());
			for(int i = 0; i < list.size(); i++) {
				BookStyle bookStyle = (BookStyle) list.get(i);
				bookAmerce = bookStyle.getAmerce();
				allotedDays = bookStyle.getBorrowDays();
			}
			borrowDateTxt.setText(table.getValueAt(selRow, 5).toString().trim());
			allotedDaysTxt.setText(allotedDays + "");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=null;
			try {
				date = df.parse(table.getValueAt(selRow, 5).toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			totalDays = differentDaysByMillisecond(currentDate,date);
			overdueDays = totalDays - Integer.parseInt(allotedDays);
			realDaysTxt.setText(totalDays + "");
			if(overdueDays > 0) {
				exceedDaysTxt.setText(overdueDays + "");
				Double totalAmerce = Double.valueOf(bookAmerce) * overdueDays;
				amerceMoneyTxt.setText(totalAmerce + "");
			} else {
				exceedDaysTxt.setText("û�г����涨����");
				amerceMoneyTxt.setText("0");
			}
			bookId = table.getValueAt(selRow, 1).toString().trim();
			bookName = table.getValueAt(selRow, 0).toString().trim();
			readerName = table.getValueAt(selRow, 3).toString().trim();
			bookFee = amerceMoneyTxt.getText();
			borrowDate = table.getValueAt(selRow, 5).toString();
			returnDate = currentlyDateTxt.getText().toString();
		}
	}

	//��ǰʱ������¼�
	class TimeActionListener implements ActionListener{
		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			currentlyDateTxt.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
	
	//���߱���ı�������¼�
	class readerISBNListenerlostFocus extends KeyAdapter {
		private final DefaultTableModel model;

		readerISBNListenerlostFocus(DefaultTableModel model) {
			this.model = model;
		}
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // �ж����ı����Ƿ�����س���
				if(table.getRowCount() != 0) {
					model.setRowCount(0);
					Resert();
				}
				add();
			}
		}

		private void add() {
			readerId = readerIdTxt.getText().trim();
			List<BackBook> list = BackBookDao.selectBookBack(readerId);
			for(int i = 0; i < list.size(); i++) {
				BackBook backBook = (BackBook) list.get(i);
				String str[] = new String[7];
				str[0] = backBook.getBookName();
				str[1] = backBook.getBookId();
				str[2] = String.valueOf(MapUtil.getMap().get(backBook.getTypeId()));
				str[3] = backBook.getReaderName();
				str[4] = backBook.getReaderId();
				str[5] = backBook.getBorrowDate();
				str[6] = backBook.getReturnDate();
				model.addRow(str);
			}
		}
	}
	
	/**
     * ͨ��ʱ����������ж�����ʱ��ļ��
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date currentDate,Date borrowDate)
    {
        int days = (int) ((currentDate.getTime() - borrowDate.getTime()) / (1000*3600*24));
        return days;
    }
}
