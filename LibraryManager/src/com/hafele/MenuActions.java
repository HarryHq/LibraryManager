package com.hafele;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.hafele.iframe.AboutUsIFrame;
import com.hafele.iframe.BookBackIFrame;
import com.hafele.iframe.BookBorrowIFrame;
import com.hafele.iframe.BookInfoAddIFrame;
import com.hafele.iframe.BookInfoModiAndDelIFrame;
import com.hafele.iframe.BookSearchIFrame;
import com.hafele.iframe.BookTypeAddIFrame;
import com.hafele.iframe.BookTypeModifyFrame;
import com.hafele.iframe.ChangePasswordIFrame;
import com.hafele.iframe.ReaderAddIFrame;
import com.hafele.iframe.ReaderModiAndDelIFrame;
import com.hafele.iframe.UserAddIFrame;
import com.hafele.iframe.UserModiAndDelIFrame;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��9��21�� ����5:25:24
* �˵��Ͱ�ť��Action����
*/
public class MenuActions {

	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��
	
	public static PasswordModiAction MODIFY_PASSWORD;// �޸����봰�嶯��
	public static ReaderAddAction READER_ADD; // ������Ϣ��Ӵ��嶯��
	public static ReaderModiAction READER_MODIFY; // ������Ϣ�޸Ĵ��嶯��
	public static BookTypeAddAction BOOKSTYLE_ADD;//ͼ��������Ӵ��嶯��
	public static BookTypeModiAction BOOKTYPE_MODIFY; // ͼ�������޸Ĵ��嶯��
	public static BookInfoAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
	public static BookModiAction BOOK_MODIFY; // ͼ����Ϣ�޸Ĵ��嶯��
	public static BorrowAction BORROW; // ͼ����Ĵ��嶯��
	public static GiveBackAction GIVE_BACK; // ͼ��黹���嶯��
	public static BookSearchAction BOOK_SEARCH; // ͼ���������嶯��
	public static UserAddAction USER_ADD;//����Ա��Ϣ���
	public static UserModiAndDelAction USER_MODIFY;//����Ա��Ϣ�޸Ļ�ɾ��
	public static AboutUsAction ABOUT_US;//��������
	public static ExitAction EXIT; // ϵͳ�˳�����
	
	static {
		frames = new HashMap<String, JInternalFrame>();
		MODIFY_PASSWORD = new PasswordModiAction();
		READER_ADD = new ReaderAddAction();
		READER_MODIFY = new ReaderModiAction();
		BOOKSTYLE_ADD = new BookTypeAddAction();
		BOOKTYPE_MODIFY = new BookTypeModiAction();
		BOOK_ADD = new BookInfoAddAction();
		BOOK_MODIFY = new BookModiAction();
		BORROW = new BorrowAction();
		GIVE_BACK = new GiveBackAction();
		BOOK_SEARCH = new BookSearchAction();
		USER_ADD = new UserAddAction();
		USER_MODIFY = new UserModiAndDelAction();
		ABOUT_US = new AboutUsAction();
		EXIT = new ExitAction();
	}
	
	@SuppressWarnings("serial")
	private static class AboutUsAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!frames.containsKey("��������")||frames.get("��������").isClosed()) {
				AboutUsIFrame iframe = new AboutUsIFrame();
				frames.put("��������", iframe);
				LibraryMain.addIFame(iframe);
			}
		}	
	}
	
	@SuppressWarnings("serial")
	private static class BookSearchAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���ѯ")||frames.get("ͼ���ѯ").isClosed()) {
				BookSearchIFrame iframe = new BookSearchIFrame();
				frames.put("ͼ���ѯ", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class GiveBackAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!frames.containsKey("ͼ��黹����")||frames.get("ͼ��黹����").isClosed()) {
				BookBackIFrame iframe = new BookBackIFrame();
				frames.put("ͼ��黹����", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BorrowAction extends AbstractAction{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ĺ���")||frames.get("ͼ����Ĺ���").isClosed()) {
				BookBorrowIFrame iframe = new BookBorrowIFrame();
				frames.put("ͼ����Ĺ���", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookModiAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ϣ�޸���ɾ��")||frames.get("ͼ����Ϣ�޸���ɾ��").isClosed()) {
				BookInfoModiAndDelIFrame iframe = new BookInfoModiAndDelIFrame();
				frames.put("ͼ����Ϣ�޸���ɾ��", iframe);
				LibraryMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookInfoAddAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ϣ���")||frames.get("ͼ����Ϣ���").isClosed()) {
				BookInfoAddIFrame iframe = new BookInfoAddIFrame();
				frames.put("ͼ����Ϣ���", iframe);
				LibraryMain.addIFame(frames.get("ͼ����Ϣ���"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookTypeModiAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ�������޸���ɾ��")||frames.get("ͼ�������޸���ɾ��").isClosed()) {
				BookTypeModifyFrame iframe=new BookTypeModifyFrame();
				frames.put("ͼ�������޸���ɾ��", iframe);
				LibraryMain.addIFame(frames.get("ͼ�������޸���ɾ��"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookTypeAddAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("ͼ��������")||frames.get("ͼ��������").isClosed()) {
				BookTypeAddIFrame iframe=new BookTypeAddIFrame();
				frames.put("ͼ��������", iframe);
				LibraryMain.addIFame(frames.get("ͼ��������"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class PasswordModiAction extends AbstractAction {
		
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("�޸�����")||frames.get("�޸�����").isClosed()) {
				ChangePasswordIFrame iframe=new ChangePasswordIFrame();
				frames.put("�޸�����", iframe);
				LibraryMain.addIFame(frames.get("�޸�����"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class UserAddAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("��ӹ���Ա")||frames.get("��ӹ���Ա").isClosed()) {
				UserAddIFrame iframe=new UserAddIFrame();
				frames.put("��ӹ���Ա", iframe);
				LibraryMain.addIFame(frames.get("��ӹ���Ա"));
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	private static class UserModiAndDelAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("����Ա��Ϣ�޸���ɾ��")||frames.get("����Ա��Ϣ�޸���ɾ��").isClosed()) {
				UserModiAndDelIFrame iframe=new UserModiAndDelIFrame();
				frames.put("����Ա��Ϣ�޸���ɾ��", iframe);
				LibraryMain.addIFame(frames.get("����Ա��Ϣ�޸���ɾ��"));
			}
		}
		
	}
	@SuppressWarnings("serial")
	private static class ReaderAddAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("������Ϣ���")||frames.get("������Ϣ���").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("������Ϣ���", iframe);
				LibraryMain.addIFame(frames.get("������Ϣ���"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class ReaderModiAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("������Ϣ�޸���ɾ��")||frames.get("������Ϣ�޸���ɾ��").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("������Ϣ�޸���ɾ��", iframe);
				LibraryMain.addIFame(frames.get("������Ϣ�޸���ɾ��"));
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	private static class ExitAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			int result=JOptionPane.showConfirmDialog(null, "�Ƿ��˳�ϵͳ");
			if(result==0){
				System.exit(0);
			}
		}
	}
	private MenuActions() {
		super();
	}
}
