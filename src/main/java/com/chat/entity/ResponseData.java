package com.chat.entity;

import java.util.HashMap;

import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;

public class ResponseData extends HashMap<String, Object> {

	private static final long serialVersionUID = -1408552980412560939L;
	private static final Integer SUCCESS_STATUS = 200;
	private static final Integer ERROR_STATUS = -1;
	private static final String SUCCESS_MSG = "一切正常";

	public ResponseData() {
		super();
	}

	public ResponseData(int code) {
		super();
		setStatus(code);
	}

	public ResponseData(HttpStatus status) {
		super();
		setStatus(status.value());
		setMsg(status.getReasonPhrase());
	}

	public ResponseData success() {
		put("msg", SUCCESS_MSG);
		put("status", SUCCESS_STATUS);
		return this;
	}

	public ResponseData setData(String key, Object obj) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> data = (HashMap<String, Object>) get("data");
		if (data == null) {
			data = new HashMap<String, Object>();
			put("data", data);
		}
		data.put(key, obj);
		return this;
	}

	public ResponseData success(String msg) {
		put("msg", msg);
		put("status", SUCCESS_STATUS);
		return this;
	}

	public ResponseData error(String msg) {
        put("msg", msg);
        put("status", ERROR_STATUS);
        return this;
    }
	
	public ResponseData setStatus(int status) {
		put("status", status);
		return this;
	}

	public ResponseData setMsg(String msg) {
		put("msg", msg);
		return this;
	}

	public ResponseData setValue(String key, Object val) {
		put(key, val);
		return this;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
