package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.hafele.dao.ReaderInfoDao;
import com.hafele.util.DateUtils;
import com.hafele.util.MyDocument;


/**
* ������Ϣ���
*/
@SuppressWarnings("serial")
public class ReaderAddIFrame extends JInternalFrame {
	private JTextField nameTxt;//��������
	private JTextField ageTxt;//��������
	private JTextField occupationTxt;//����ְҵ��Ϣ
	private JTextField idNumberTxt;//����֤������
	private JTextField telNumberTxt;//���ߵ绰����
	private JTextField depositTxt;//Ѻ��
	private JFormattedTextField registrationDateTxt;//ע������
	private JTextField readerNumTxt;//����֤���
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<?> validDocumentation;
	private String [] array;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public ReaderAddIFrame() {
		super();
		setIconifiable(true);
		setTitle("\u8BFB\u8005\u4FE1\u606F\u6DFB\u52A0");
		setFrameIcon(new ImageIcon(ReaderAddIFrame.class.getResource("/icon/read_16px.png")));
		setClosable(true);
		setBounds(100, 100, 505, 364);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReaderAddIFrame.class.getResource("/icon/readerAdd.jpg")));
		getContentPane().add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_info = new JPanel();
		
		JPanel panel_button = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_info, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
				.addComponent(panel_button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_info, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
					.addGap(21))
		);
		
		JLabel label_1 = new JLabel("\u59D3        \u540D\uFF1A");
		
		JLabel label_2 = new JLabel("\u6027        \u522B\uFF1A");
		
		JLabel label_3 = new JLabel("\u5E74        \u9F84\uFF1A");
		
		JLabel label_4 = new JLabel("\u804C        \u4E1A\uFF1A");
		
		JLabel label_5 = new JLabel("\u6709\u6548\u8BC1\u4EF6\uFF1A");
		
		JLabel label_6 = new JLabel("\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		
		JLabel label_7 = new JLabel("\u7535        \u8BDD\uFF1A");
		
		JLabel label_8 = new JLabel("\u62BC        \u91D1\uFF1A");
		
		JLabel label_9 = new JLabel("\u6CE8\u518C\u65E5\u671F\uFF1A");
		
		JLabel label_10 = new JLabel("\u8BFB\u4E66\u7F16\u53F7\uFF1A");
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		
		ageTxt = new JTextField();
		ageTxt.setDocument(new MyDocument(2));//���Ƹ��ı������������λ��
		ageTxt.addKeyListener(new NumberListener());
		ageTxt.setColumns(10);
		
		occupationTxt = new JTextField();
		occupationTxt.setColumns(10);
		
		idNumberTxt = new JTextField();
		idNumberTxt.setColumns(10);
		
		telNumberTxt = new JTextField();
		telNumberTxt.setDocument(new MyDocument(11));//���Ƹ��ı������������λ��
		telNumberTxt.addKeyListener(new NumberListener());
		telNumberTxt.setColumns(10);
		
		depositTxt = new JTextField();
		depositTxt.setColumns(10);
		
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		registrationDateTxt = new JFormattedTextField(myfmt.getDateInstance());
		registrationDateTxt.setColumns(10);
		registrationDateTxt.setValue(DateUtils.toSqlDate(new java.util.Date()));
		registrationDateTxt.addKeyListener(new DateListener());
		
		readerNumTxt = new JTextField();
		readerNumTxt.setColumns(10);
		
		validDocumentation = new JComboBox();
		array = new String[] {"���֤","����֤","ѧ��֤","����֤"};
		validDocumentation.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			validDocumentation.setSelectedIndex(i);
			validDocumentation.setSelectedItem(array);
		}
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel_info = new GroupLayout(panel_info);
		gl_panel_info.setHorizontalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label_3)
						.addComponent(label_5)
						.addComponent(label_7)
						.addComponent(label_9))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nameTxt, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addComponent(ageTxt, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addComponent(telNumberTxt, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addComponent(registrationDateTxt, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addComponent(validDocumentation, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(label_10)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerNumTxt, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(label_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(depositTxt, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(label_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idNumberTxt, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGroup(gl_panel_info.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_info.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(occupationTxt, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_panel_info.setVerticalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(label_2)
								.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(occupationTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(label_6)
						.addComponent(idNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(validDocumentation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(label_8)
						.addComponent(depositTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(telNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9)
						.addComponent(label_10)
						.addComponent(registrationDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(readerNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JRadioButton radioButton = new JRadioButton("\u7537");
		panel_1.add(radioButton);
		buttonGroup.add(radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u5973");
		panel_1.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton);
		
		panel_info.setLayout(gl_panel_info);
		
		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.setIcon(new ImageIcon(ReaderAddIFrame.class.getResource("/icon/save_button_16px.png")));
		button_save.addActionListener(new ButtonSaveListener(radioButton));
		
		JButton button_return = new JButton("\u8FD4\u56DE");
		button_return.setIcon(new ImageIcon(ReaderAddIFrame.class.getResource("/icon/return_button16px.png")));
		button_return.addActionListener(new CloseActionListener());
		
		GroupLayout gl_panel_button = new GroupLayout(panel_button);
		gl_panel_button.setHorizontalGroup(
			gl_panel_button.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_button.createSequentialGroup()
					.addGap(113)
					.addComponent(button_save)
					.addGap(100)
					.addComponent(button_return)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		gl_panel_button.setVerticalGroup(
			gl_panel_button.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_button.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_button.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_save, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(button_return, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_button.setLayout(gl_panel_button);
		panel.setLayout(gl_panel);
		
		//���ô���ɼ�
		setVisible(true);
	}
	//ע�����ڸ�ʽ����
	public class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(registrationDateTxt.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "ʱ���ʽ��ʹ��\"2017-09-22\"��ʽ");
			}
		}
	}
	//���ּ����¼�
	public class NumberListener extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	//���水ť�¼�����
	public class ButtonSaveListener implements ActionListener{
		private final JRadioButton button1;

		ButtonSaveListener(JRadioButton button1) {
			this.button1 = button1;
		}
		@Override
		public void actionPerformed(final ActionEvent e) {
			if(nameTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "������������Ϊ�գ�");
				return;
			}
			if(ageTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�������䲻��Ϊ�գ�");
				return;
			}
			if(occupationTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "����ְҵ����Ϊ�գ�");
				return;
			}
			if(occupationTxt.getText().length() > 20) {
				JOptionPane.showMessageDialog(null, "����ְҵ��Ϣ���ܳ���20���ַ���");
				return;
			}
			if(idNumberTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "����֤�����벻��Ϊ�գ�");
				return;
			}
			if(idNumberTxt.getText().length() < 10) {
				JOptionPane.showMessageDialog(null, "����֤�����벻��С��10λ����");
				return;
			}
			if(telNumberTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���ߵ绰���벻��Ϊ�գ�");
				return;
			}
			if(telNumberTxt.getText().length() > 11 || telNumberTxt.getText().length() < 0) {
				JOptionPane.showMessageDialog(null, "�绰����λ��С��11λ��");
				return;
			}
			if(depositTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Ѻ����Ϊ�գ�");
				return;
			}
			if(registrationDateTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "ʱ���ʽ��ʹ��\"2017-09-22\"��ʽ");
				return;
			}
			if(readerNumTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���߱�Ų���Ϊ�գ�");
				return;
			}
			if(readerNumTxt.getText().length() != 8) {
				JOptionPane.showMessageDialog(null, "���߱����Ϊ8λ��");
				return;
			}
			//sex����1Ϊ������sex����2ΪŮ��
			String sex = "1";
			if(!button1.isSelected()) {
				sex = "2";
			}
			System.out.println(registrationDateTxt.getText());
			long d=new java.util.Date().getTime();
			Date date = new Date(d);
//			System.out.println(nameTxt.getText()+sex.trim()+ageTxt.getText().trim()+occupationTxt.getText().trim()+validDocumentation.getSelectedIndex()+idNumberTxt.getText().trim()+telNumberTxt.getText().trim()+depositTxt.getText().trim()+registrationDateTxt.getText().trim()+readerNumTxt.getText().trim());
			int i = ReaderInfoDao.insertReader(nameTxt.getText().trim(),sex.trim(),ageTxt.getText().trim(),
					occupationTxt.getText().trim(),validDocumentation.getSelectedIndex(),
					idNumberTxt.getText().trim(),telNumberTxt.getText().trim(),
					BigDecimal.valueOf(Double.valueOf(depositTxt.getText().trim())),
					date,readerNumTxt.getText().trim());
			
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				doDefaultCloseAction();
			}
		}
		
	}
	//���ذ�ť�¼�����
	public class CloseActionListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
		
	}
}
