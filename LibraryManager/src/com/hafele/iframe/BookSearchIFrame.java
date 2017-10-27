package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.hafele.dao.BookInfoDao;
import com.hafele.model.BookInfo;
import com.hafele.util.MapUtil;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��10��11�� ����11:04:32
* ��˵��
*/
@SuppressWarnings("serial")
public class BookSearchIFrame extends JInternalFrame {

	private JTextField queryConditionTxt;
	private JTable table_allResult, table_searchResult;
	@SuppressWarnings("rawtypes")
	private JComboBox ComboBox;
	private JScrollPane scrollPane_allBook, scrollPane_result;
	private String columnNames[] = { "ͼ����", "ͼ�����", "ͼ������", "����", "������", "��������", "��������", "����", "�Ƿ���" };
	
	@SuppressWarnings("rawtypes")
	private Object[][] getselect(List list) {
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i = 0; i < list.size(); i++) {
			BookInfo bookInfo = (BookInfo) list.get(i);
			String bookTypeName = String.valueOf(MapUtil.getMap().get(bookInfo.getBookStyleNumber()));
			String isborrowed;
			if(bookInfo.getIsBorrowed().equals("1")) {
				isborrowed = "��";
			}else {
				isborrowed = "��";
			}
			results[i][0] = bookInfo.getBookId();
			results[i][1] = bookTypeName;
			results[i][2] = bookInfo.getBookName();
			results[i][3] = bookInfo.getBookAuthor();
			results[i][4] = bookInfo.getBookPub();
			results[i][5] = bookInfo.getBookInDate();
			results[i][6] = bookInfo.getBookPubDate();
			results[i][7] = bookInfo.getBookPrice();
			results[i][8] = isborrowed;
		}
		return results;
	}
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BookSearchIFrame() {
		super();
		setFrameIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/Book_Search_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("ͼ���ѯ");
		setBounds(100, 100, 500, 400);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);

		final JPanel conditionQueryPanel = new JPanel();
		conditionQueryPanel.setLayout(new BorderLayout());
		tabbedPane.addTab("������ѯ", null, conditionQueryPanel, null);

		final JPanel selectQueryPanel = new JPanel();
		selectQueryPanel.setBorder(new TitledBorder(null, "��ѡ���ѯ��Ŀ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		selectQueryPanel.setPreferredSize(new Dimension(0, 50));
		conditionQueryPanel.add(selectQueryPanel, BorderLayout.NORTH);
		
        ComboBox=new JComboBox();
		String[] array={"ͼ������","ͼ������"};
		for(int i=0;i<array.length;i++){
			ComboBox.addItem(array[i]);
		}
		selectQueryPanel.add(ComboBox);
		queryConditionTxt = new JTextField();
		queryConditionTxt.setColumns(20);
		selectQueryPanel.add(queryConditionTxt);
        
		final JPanel searchResultPanel = new JPanel();
		searchResultPanel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		conditionQueryPanel.add(searchResultPanel);

		
		scrollPane_result = new JScrollPane();
		scrollPane_result.setPreferredSize(new Dimension(400, 200));
		searchResultPanel.add(scrollPane_result);

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));
		conditionQueryPanel.add(buttonPanel, BorderLayout.SOUTH);

		final JButton searchButton = new JButton();
		searchButton.setIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/Book_Search_16px.png")));
		searchButton.setText("��ѯ");
		buttonPanel.add(searchButton);

		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String name=(String)ComboBox.getSelectedItem();
				if(name.equals("ͼ������")){
					Object[][] results=getselect(BookInfoDao.selectBookName(queryConditionTxt.getText().trim()));
					table_searchResult = new JTable(results,columnNames);
					scrollPane_result.setViewportView(table_searchResult);
				}
				else if(name.equals("ͼ������")){
					Object[][] results=getselect(BookInfoDao.selectBookAuthor(queryConditionTxt.getText().trim()));
					table_searchResult = new JTable(results,columnNames);
					scrollPane_result.setViewportView(table_searchResult);
				}
			}
	    });
		
		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		exitButton.setIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/logoff_16px.png")));
		exitButton.setText("�˳�");
		buttonPanel.add(exitButton);
		
		setVisible(true);
		
		final JPanel allBookInfoPanel = new JPanel();
		tabbedPane.addTab("��ʾͼ��ȫ����Ϣ", null, allBookInfoPanel, null);
         
		scrollPane_allBook = new JScrollPane();
		scrollPane_allBook.setPreferredSize(new Dimension(450, 250));
		allBookInfoPanel.add(scrollPane_allBook);
		
		Object[][] results=getselect(BookInfoDao.selectBookInfo());
		table_allResult = new JTable(results,columnNames);
		table_allResult.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane_allBook.setViewportView(table_allResult);
		
		//���ô���ɼ�
		setVisible(true);
	}
}
