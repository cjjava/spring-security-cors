package com.cjoop.cors.vo;

import java.io.Serializable;

/**
 * 针对服务端发送给客户端的消息对象
 * @author 陈均
 *
 */
public class Message implements Serializable{	
	private static final long serialVersionUID = 1L;
	/**
	 * 消息状态码
	 */
	protected int code;
	/**
	 * 消息内容
	 */
	protected String text;
	/**
	 * 消息附加数据
	 */
	protected Object data;

	public Message() {
	}

	public Message(int code, String text) {
		this.text = text;
		this.code = code;
	}

	public Message(int code, String text, Object data) {
		this(code, text);
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
