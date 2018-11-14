package com.hafele.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class DateUtils {
	
	/**
	 * 把java.util.Date类型日期转换成java.sql.Date类型日期
	 * @param date 传入一个java.util.Date类型参数
	 * @return 返回 2018-04-24 格式的java.sql.Date日期
	 */
	public static Date toSqlDate(java.util.Date date) {
		java.sql.Date sqlDate=new Date(date.getTime());		
		return sqlDate;		
	}
	
	/**
	 * 把java.util.Date类型日期转换成java.sql.Time类型日期
	 * @param date 传入一个java.util.Date类型参数
	 * @return 返回 22:26:28 格式的java.sql.Time时间
	 */
	public static Time toSqlTime(java.util.Date date) {
		Time time= new Time(date.getTime());
		return time;		
	}
	
	/**
	 * 把java.util.Date类型日期转换成java.sql.Timestamp类型日期
	 * @param date 传入一个java.util.Date类型参数
	 * @return 返回 2018-04-24 22:26:28.904 格式的java.sql.Timestamp类型日期
	 */
	public static Timestamp toSqlTimestamp(java.util.Date date) {
		Timestamp timestamp=new Timestamp(date.getTime());
		return timestamp;		
	}

}

