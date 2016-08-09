package com.example.view;

import java.util.List;

public class Page {

	private List<Line> page;
	
	private float lastLineCount;
	
	private float pageHeight;
	
	private boolean isHasJieFen;
	
	private String norender;
	
	
	public String getNorender() {
		return norender;
	}

	public void setNorender(String norender) {
		this.norender = norender;
	}

	public boolean isHasJieFen() {
		return isHasJieFen;
	}

	public void setHasJieFen(boolean isHasJieFen) {
		this.isHasJieFen = isHasJieFen;
	}

	public float getPageHeight() {
		return pageHeight;
	}

	public void setPageHeight(float pageHeight) {
		this.pageHeight = pageHeight;
	}

	public List<Line> getPage() {
		return page;
	}

	public void setPage(List<Line> page) {
		this.page = page;
	}

	public float getLastLineCount() {
		return lastLineCount;
	}

	public void setLastLineCount(float lastLineCount) {
		this.lastLineCount = lastLineCount;
	}
	
}
