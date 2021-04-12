package com.hs.app.dto;

public class Qa_Board_Dto {
	private int qa_idx;
	private String user_id;
	private String qa_title;
	private String qa_credate;
	private String qa_content;
	private int qa_viewcnt;
	private int qa_ref;
	private int qa_re_step;
	private int qa_re_level;
	private String qa_sel;
	
	public Qa_Board_Dto(){}
	
	public Qa_Board_Dto(int qa_idx, String user_id, String qa_title, String qa_credate, String qa_content,
			int qa_viewcnt,int qa_ref, int qa_re_step, int qa_re_level, String qa_sel) {
		super();
		this.qa_idx = qa_idx;
		this.user_id = user_id;
		this.qa_title = qa_title;
		this.qa_credate = qa_credate;
		this.qa_content = qa_content;
		this.qa_viewcnt = qa_viewcnt;
		this.qa_ref = qa_ref;
		this.qa_re_step = qa_re_step;
		this.qa_re_level = qa_re_level;
		this.qa_sel = qa_sel;
		
	}

	public String getQa_sel() {
		return qa_sel;
	}

	public void setQa_sel(String qa_sel) {
		this.qa_sel = qa_sel;
	}

	public int getQa_ref() {
		return qa_ref;
	}

	public void setQa_ref(int qa_ref) {
		this.qa_ref = qa_ref;
	}

	public int getQa_idx() {
		return qa_idx;
	}

	public void setQa_idx(int qa_idx) {
		this.qa_idx = qa_idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getQa_title() {
		return qa_title;
	}

	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}

	public String getQa_credate() {
		return qa_credate;
	}

	public void setQa_credate(String qa_credate) {
		this.qa_credate = qa_credate;
	}

	public String getQa_content() {
		return qa_content;
	}

	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}

	public int getQa_viewcnt() {
		return qa_viewcnt;
	}

	public void setQa_viewcnt(int qa_viewcnt) {
		this.qa_viewcnt = qa_viewcnt;
	}

	public int getQa_re_step() {
		return qa_re_step;
	}

	public void setQa_re_step(int qa_re_step) {
		this.qa_re_step = qa_re_step;
	}

	public int getQa_re_level() {
		return qa_re_level;
	}

	public void setQa_re_level(int qa_re_level) {
		this.qa_re_level = qa_re_level;
	}
	
	
	
	
}