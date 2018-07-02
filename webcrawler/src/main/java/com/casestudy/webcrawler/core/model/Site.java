package com.casestudy.webcrawler.core.model;

import java.util.ArrayList;

public class Site implements SiteMap {
	
	ArrayList<Page> pages = new ArrayList<Page>();

	public ArrayList<Page> getPages() {
		return pages;
	}

	public void addPage(Page page) {
		this.pages.add(page);
	}
	
	

}
