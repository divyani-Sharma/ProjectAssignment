package com.post.postApp.data;

public class ApiResponse {

	private Long dbPostId;
    private String httpOutboundResponse;

    public ApiResponse(Long dbPostId, String httpOutboundResponse) {
        this.dbPostId = dbPostId;
        this.httpOutboundResponse = httpOutboundResponse;
    }

	public Long getDbPostId() {
		return dbPostId;
	}

	public void setDbPostId(Long dbPostId) {
		this.dbPostId = dbPostId;
	}

	public String getHttpOutboundResponse() {
		return httpOutboundResponse;
	}

	public void setHttpOutboundResponse(String httpOutboundResponse) {
		this.httpOutboundResponse = httpOutboundResponse;
	}
    
    

}
