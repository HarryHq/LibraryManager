package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.hafele.dao.BookInfoDao;
import com.hafele.dao.BookStyleDao;
import com.hafele.model.BookInfo;
import com.hafele.model.BookStyle;
import com.hafele.util.DateUtils;
import com.hafele.util.Item;
import com.hafele.util.SearcISBNDemo;

import net.sf.json.JSONObject;

/**
* 
* ͼ����Ϣ��Ӵ���
*/
@SuppressWarnings("serial")
public class BookInfoAddIFrame extends JInternalFrame {
	private JTextField bookIDTxt;
	private JTextField bookNameTxt;
	private JTextField bookAuthorTxt;
	private JFormattedTextField orderDateTxt;
	private JTextField priceTxt;
	private JTextField publisherDateTxt;
	private JTextField publisherTxt;
	private JComboBox<Item> bookType;
	private DefaultComboBoxModel<Item> bookTypeModel;
	private SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	private int getBookStyleNumber(List<?> list){
		//tag����1ʱ����ʾ��ͼ�����ͱ�Ŵ���
		int tag = 0;
		Object[] bookInfos = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			BookInfo bookInfo = (BookInfo) list.get(i);
			bookInfos[i] = bookInfo.getBookId();
			if(bookInfos[i].equals(bookIDTxt.getText())) {
				tag = 1;
			}
		}
		return tag;
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public BookInfoAddIFrame() {
		setFrameIcon(new ImageIcon(BookInfoAddIFrame.class.getResource("/icon/book_add_16px.png")));
		setTitle("ͼ����Ϣ���");
		setIconifiable(true);
		setClosable(true);
		setClosable(true);								// ���ô���ɹرգ���������
		setBounds(100, 100, 450, 341);					// ���ô���λ�úʹ�С����������
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BookInfoAddIFrame.class.getResource("/icon/bookAdd.jpg")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(23))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		JButton addButton = new JButton("���");
		addButton.setIcon(new ImageIcon(BookInfoAddIFrame.class.getResource("/icon/add_16px.png")));
		addButton.addActionListener(new addBookActionListener());
		buttonPanel.add(addButton);
		
		JButton closeButton = new JButton("�ر�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		
		JButton searchButton = new JButton("��ѯ");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ISBN = bookIDTxt.getText().trim();
//				String result = SearcISBNDemo.getRequestJuhe(ISBN);//ʹ�þۺ�����API
				String result = SearcISBNDemo.getRequestJisu(ISBN);//ʹ�ü�������API			
				JSONObject jsonObject = JSONObject.fromObject(result);
				JSONObject jsonRssult = (JSONObject) jsonObject.get("result");
				bookNameTxt.setText(jsonRssult.get("title").toString());
				bookAuthorTxt.setText(jsonRssult.get("author").toString());
				publisherTxt.setText(jsonRssult.get("publisher").toString());
				publisherDateTxt.setText(jsonRssult.get("pubdate").toString());
				priceTxt.setText(jsonRssult.get("price").toString());
			}
		});
		searchButton.setIcon(new ImageIcon(BookInfoAddIFrame.class.getResource("/icon/Book_Search_16px.png")));
		buttonPanel.add(searchButton);
		closeButton.setIcon(new ImageIcon(BookInfoAddIFrame.class.getResource("/icon/logoff_16px.png")));
		buttonPanel.add(closeButton);
		
		JPanel infoPanel = new JPanel();
		getContentPane().add(infoPanel, BorderLayout.CENTER);
		
		JLabel bookIDLabel = new JLabel("ͼ���ţ�");
		
		JLabel bookNameLabel = new JLabel("ͼ�����ƣ�");
		
		JLabel publisherLabel = new JLabel("�����磺");
		
		JLabel publisherDateLabel = new JLabel("�������ڣ�");
		
		JLabel bookTypeLabel = new JLabel("ͼ�����");
		
		JLabel bookAuthorLabel = new JLabel("ͼ�����ߣ�");
		
		JLabel orderDateLabel = new JLabel("�������ڣ�");
		
		JLabel priceLabel = new JLabel("ͼ�鵥�ۣ�");
		
		bookIDTxt = new JTextField();
		bookIDTxt.setColumns(10);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		bookAuthorTxt = new JTextField();
		bookAuthorTxt.setColumns(10);
		
		orderDateTxt = new JFormattedTextField(myfmt.getDateInstance());
		orderDateTxt.setColumns(10);
		orderDateTxt.setValue(new java.util.Date());
		orderDateTxt.addKeyListener(new DateListener());
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		publisherDateTxt = new JTextField();
		publisherDateTxt.setColumns(10);
		
		publisherTxt = new JTextField();
		publisherTxt.setColumns(10);
		
		bookType = new JComboBox<Item>();
		bookTypeModel = (DefaultComboBoxModel<Item>) bookType.getModel();
		
		//�����ݿ��л�ȡͼ�����
		List<BookStyle> list = BookStyleDao.selectBookStyle();
		for(int i = 0; i < list.size(); i++) {
			BookStyle bookStyle = (BookStyle) list.get(i);
			Item item = new Item();
			item.setId((String)bookStyle.getBookStyleNumber());
			item.setName((String)bookStyle.getBookStyle());
			bookTypeModel.addElement(item);
		}
		
		GroupLayout gl_infoPanel = new GroupLayout(infoPanel);
		gl_infoPanel.setHorizontalGroup(
			gl_infoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(bookIDLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherDateLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_infoPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(publisherDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_infoPanel.createSequentialGroup()
							.addComponent(priceLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_infoPanel.createSequentialGroup()
							.addComponent(orderDateLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(orderDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_infoPanel.createSequentialGroup()
							.addComponent(bookAuthorLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_infoPanel.createSequentialGroup()
							.addComponent(bookTypeLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_infoPanel.setVerticalGroup(
			gl_infoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoPanel.createSequentialGroup()
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookIDLabel)
						.addComponent(bookTypeLabel)
						.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookNameLabel)
						.addComponent(bookAuthorLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(publisherLabel)
						.addComponent(orderDateLabel)
						.addComponent(orderDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(publisherDateLabel)
						.addComponent(priceLabel)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		infoPanel.setLayout(gl_infoPanel);

		//���ô���ɼ�
		this.setVisible(true);
	}
	//���ڸ�ʽ����
	public class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDateTxt.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "ʱ���ʽ��ʹ��\"yyyy-MM-dd\"��ʽ");
			}
		}
	}
	
	//��Ӱ�ť�¼�����
	class addBookActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String ISBN = bookIDTxt.getText().trim();
			//����
			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			String bookTypes=item.getId();
			String bookName = bookNameTxt.getText().trim();
			String bookAuthor = bookAuthorTxt.getText().trim();
			String publisher = publisherTxt.getText().trim();
			String orderDate = orderDateTxt.getText().trim();
			String publisherDate = publisherDateTxt.getText().trim();
			String price = priceTxt.getText().trim();
			if(getBookStyleNumber(BookInfoDao.selectBookInfo()) == 1) {
				JOptionPane.showMessageDialog(null, "��ͼ�����Ѵ��ڣ����������롣");
				return;
			}
			if(ISBN.length() == 0) {
				JOptionPane.showMessageDialog(null, "ͼ���Ų���Ϊ�ա�");
				return;
			}
			if(ISBN.length() != 13) {
				JOptionPane.showMessageDialog(null, "ͼ���ű���Ϊ13λ��");
				return;
			}
			if(bookName.length() == 0) {
				JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�ա�");
				return;
			}
			if(bookName.length() > 15) {
				JOptionPane.showMessageDialog(null, "ͼ�����Ʋ��ܳ���15���ַ���");
				return;
			}
			if(bookAuthor.length() == 0) {
				JOptionPane.showMessageDialog(null, "ͼ�����߲���Ϊ�ա�");
				return;
			}
			if(publisher.length() == 0) {
				JOptionPane.showMessageDialog(null, "�����粻��Ϊ�ա�");
				return;
			}
			if(orderDate.length() == 0) {
				JOptionPane.showMessageDialog(null, "�������ڲ���Ϊ�ա�");
				return;
			}
			if(publisherDate.length() == 0) {
				JOptionPane.showMessageDialog(null, "�������ڲ���Ϊ�ա�");
				return;
			}
			if(price.length() == 0) {
				JOptionPane.showMessageDialog(null, "ͼ�鵥�۲���Ϊ�ա�");
				return;
			}
			try {
				myfmt.parse(publisherDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "�������ڸ�ʽ��ʹ��\"yyyy-MM-dd\"��ʽ");
				return;
			}
			System.out.println(orderDate+"\n"+publisherDate);
			long d=new java.util.Date().getTime();
			Date date = new Date(d);
			int i = BookInfoDao.insertBookInfo(ISBN,bookTypes,bookName,bookAuthor,publisher,date,Date.valueOf(publisherDate),Double.valueOf(price));
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				resertTextFile();
			}
		}
		
		//����ı���
		private void resertTextFile() {
			bookIDTxt.setText(null);
			bookNameTxt.setText(null);
			bookAuthorTxt.setText(null);
			publisherTxt.setText(null);
			publisherDateTxt.setText(null);
			priceTxt.setText(null);
		}
	}
}
