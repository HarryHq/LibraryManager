package com.hafele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hafele.model.BackBook;
import com.hafele.util.DbHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��10��10�� ����4:14:31
* ͼ��黹���ݿ������
*/
public class BackBookDao {

	//��ȡͼ��黹��Ϣ
	public static List<BackBook> selectBookBack(String readerId) {
		List<BackBook> list=new ArrayList<BackBook>();
		String sql = "SELECT A.bookid, A.bookname, A.bookstylenumber, B.borrowdate, B.backDate, C.readerid, C.reader_name FROM system_books A INNER JOIN borrow_record B ON A.bookid = B.bookid INNER JOIN system_readers C ON B.readerid = C.readerid WHERE (C.readerid = '"+readerId+"' and A.isborrowed=1)";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				BackBook backBook = new BackBook();
				backBook.setBookId(re.getString("bookid"));
				backBook.setBookName(re.getString("bookname"));
				backBook.setTypeId(re.getString("bookstylenumber"));
				backBook.setReaderId(re.getString("readerid"));
				backBook.setReaderName(re.getString("reader_name"));
				backBook.setBorrowDate(re.getString("borrowdate"));
				backBook.setReturnDate(re.getString("backDate"));
				list.add(backBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "�쳣�����������Ƿ�����");
		}
		DbHelper.close(conn);
		return list;
	}

	//ͼ��黹
	public static int updateBookBack(String readerId, String readerName, String bookId, String bookName, String bookFee,
			String borrowDate, String returnDate) {
		int i = 0;
		String sql1 = "insert into return_record(bookid,readerid,returndate)values('"+bookId+"','"+readerId+"','"+returnDate+"')";
		String sql2 = "insert into reader_fee(readerid,readername,bookid,bookname,bookfee,borrowdate)values('"+readerId+"','"+readerName+"','"+bookId+"','"+bookName+"','"+bookFee+"','"+borrowDate+"')";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			int x = conn.createStatement().executeUpdate(sql1);
			int y = conn.createStatement().executeUpdate(sql2);
			if(x == 1 && y == 1) {
				i = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();			
			JOptionPane.showMessageDialog(null, "�쳣�����������Ƿ�����");
		}finally {			
			DbHelper.close(conn);
		}
		return i;
	}

}
