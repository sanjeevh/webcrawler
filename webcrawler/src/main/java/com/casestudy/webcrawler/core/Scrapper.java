package com.casestudy.webcrawler.core;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.casestudy.webcrawler.core.model.SiteMap;

public interface Scrapper {
	
	public SiteMap generateSiteMap();
	public void scrape( Element element, Document page, String url);
	public List<String> getElementsToVisit();
}
