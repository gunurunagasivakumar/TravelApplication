package com.capco.travel.custom.exception;
/**
 * this is base exception class
 * @author e5544354
 * */
public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private String msg;

	public BaseException(String msg) {
		super(msg);
		this.msg = msg;

	}

	public BaseException(Throwable msg) {
		super(msg);
	}

	public String getMsg() {
		return msg;
	}
}
