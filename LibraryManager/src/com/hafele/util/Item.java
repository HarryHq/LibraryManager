package com.hafele.util;
/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version ����ʱ�䣺2017��10��5�� ����11:34:52
* 
*/
public class Item {
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return getName();
	}
}
