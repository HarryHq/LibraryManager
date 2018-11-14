package com.hafele.util;


import java.sql.Date;

import com.hafele.dao.ReaderInfoDao;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReaderInfoDao dao = new ReaderInfoDao();
		long d=new java.util.Date().getTime();
		Date date = new Date(d);
//		dao.insertReader("Harry", "ÄÐ", "23", "Ñ§Éú", 3,"20144834638", 
//				"15347829652",BigDecimal.valueOf(30), date, "12345678");
		String order="2018-10-9";
		Date sDate=Date.valueOf(order);
		System.out.println(sDate);
		
		System.out.println(DateUtils.toSqlDate(new java.util.Date()).toString());

	}

}
