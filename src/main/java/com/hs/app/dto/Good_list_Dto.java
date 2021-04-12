package com.hs.app.dto;

public class Good_list_Dto {
	
	private int good_idx;
	private int review_idx;
	private String user_id;
	
	public Good_list_Dto() {}
	
	public Good_list_Dto(int good_idx, int review_idx, String user_id) {
		this.good_idx = good_idx;
		this.review_idx = review_idx;
		this.user_id = user_id;
	}

	public int getGood_idx() {
		return good_idx;
	}

	public void setGood_idx(int good_idx) {
		this.good_idx = good_idx;
	}

	public int getReview_idx() {
		return review_idx;
	}

	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}

	public String getUser_idx() {
		return user_id;
	}

	public void setUser_idx(String user_id) {
		this.user_id = user_id;
	}
	
	
	

}
