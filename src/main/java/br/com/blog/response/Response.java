package br.com.blog.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Response<T> {

	private T data;
	private List<String> content;
	
	
	public Response() {
		
	}
	
	public List<String> getContent(){
		
		if( this.content == null ) {
			this.content = new ArrayList<String>();
		}
		
		return this.content;
	}
	
	
}
