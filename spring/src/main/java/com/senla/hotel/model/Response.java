package com.senla.hotel.model;
/**
 * 
 * 
 * 
 * @author yury.soukhotski
 * 
 * form answer from request Name - name operation , Body - result operation or error alert 
 *
 */
public class Response {

	private String name;
	private String body;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
