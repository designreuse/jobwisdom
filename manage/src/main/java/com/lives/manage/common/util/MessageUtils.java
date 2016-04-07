package com.lives.manage.common.util;

import java.util.List;

public class MessageUtils {
private String message;
private List<?> ls;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public List<?> getLs() {
	return ls;
}
public void setLs(List<?> ls) {
	this.ls = ls;
}
public MessageUtils() {
	// TODO Auto-generated constructor stub
}
public MessageUtils(String message, List<?> ls) {
	super();
	this.message = message;
	this.ls = ls;
}

}
