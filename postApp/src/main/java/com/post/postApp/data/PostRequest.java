package com.post.postApp.data;

public class PostRequest {
	
	private String postName;
    private String postContents;
    
	/**
	 * @param postName
	 * @param postContents
	 */
	public PostRequest(String postName, String postContents) {
		super();
		this.postName = postName;
		this.postContents = postContents;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostContents() {
		return postContents;
	}
	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}
    
    
}
