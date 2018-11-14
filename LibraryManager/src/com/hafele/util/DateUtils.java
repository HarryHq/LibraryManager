package com.hafele.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class DateUtils {
	
	/**
	 * ��java.util.Date��������ת����java.sql.Date��������
	 * @param date ����һ��java.util.Date���Ͳ���
	 * @return ���� 2018-04-24 ��ʽ��java.sql.Date����
	 */
	public static Date toSqlDate(java.util.Date date) {
		java.sql.Date sqlDate=new Date(date.getTime());		
		return sqlDate;		
	}
	
	/**
	 * ��java.util.Date��������ת����java.sql.Time��������
	 * @param date ����һ��java.util.Date���Ͳ���
	 * @return ���� 22:26:28 ��ʽ��java.sql.Timeʱ��
	 */
	public static Time toSqlTime(java.util.Date date) {
		Time time= new Time(date.getTime());
		return time;		
	}
	
	/**
	 * ��java.util.Date��������ת����java.sql.Timestamp��������
	 * @param date ����һ��java.util.Date���Ͳ���
	 * @return ���� 2018-04-24 22:26:28.904 ��ʽ��java.sql.Timestamp��������
	 */
	public static Timestamp toSqlTimestamp(java.util.Date date) {
		Timestamp timestamp=new Timestamp(date.getTime());
		return timestamp;		
	}

}

