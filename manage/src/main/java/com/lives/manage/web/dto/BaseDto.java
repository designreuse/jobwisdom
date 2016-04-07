package com.lives.manage.web.dto;

/**
 * 数据传输基础对象
 */
public class BaseDto {
	/**	返回码  */
	public Integer code;
	/**	返回值  */
	public Object msg;
	public BaseDto(){
		
	}
	public BaseDto(Integer code, Object msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BaseDto [code=" + code + ", msg=" + msg + "]";
	}
}
