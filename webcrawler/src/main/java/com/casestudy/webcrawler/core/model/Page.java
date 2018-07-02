package com.casestudy.webcrawler.core.model;

import java.util.ArrayList;

public class Page {

	String pageURL;
	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	
	ArrayList<Link> staticLinks = new ArrayList<Link>();
	String status = "accessible";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Link> getStaticLinks() {
		return staticLinks;
	}

	public void setStaticLinks(ArrayList<Link> staticLinks) {
		this.staticLinks = staticLinks;
	}

}
