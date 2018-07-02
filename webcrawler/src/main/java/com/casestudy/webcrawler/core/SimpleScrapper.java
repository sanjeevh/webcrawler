package com.casestudy.webcrawler.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.casestudy.webcrawler.core.model.Link;
import com.casestudy.webcrawler.core.model.Page;
import com.casestudy.webcrawler.core.model.Site;
import com.casestudy.webcrawler.core.model.SiteMap;

public class SimpleScrapper implements Scrapper {
	
	Site simpleSiteMap = new Site();
	Map<String, Page> scrappedPages = new HashMap<String, Page>();
	
	List<String> elementsToVisit = new ArrayList<String>();
	
	public SimpleScrapper() {
		
		elementsToVisit.add("a");
		elementsToVisit.add("link");
		elementsToVisit.add("img");
		elementsToVisit.add("embed");
		elementsToVisit.add("script");
		elementsToVisit.add("form");
		elementsToVisit.add("object");
	}

	@Override
	public SiteMap generateSiteMap() {
		
		return simpleSiteMap;
	}

	@Override
	public void scrape(Element element, Document page, String url) {
		
		Page modelPage = null;
		
		if(!scrappedPages.containsKey(url))
		{
			 modelPage = new Page();
			 modelPage.setPageURL(url);
			 scrappedPages.put(url, modelPage);
			 simpleSiteMap.addPage(modelPage);
		} else
		{
			modelPage = scrappedPages.get(url);
		}
		
		Link link = getStaticLink(element);
		if(link != null)
		{
			modelPage.getStaticLinks().add(link);
		}
	}

	@Override
	public List<String> getElementsToVisit() {
		
		return null;
	}
	
	private void scrapeRootPage(Document rootPage) {
		
	}
	
	
	private Link getStaticLink(Element element)
	{
		
		Link link = null;
		String linkURL = null;
		
		if(element.hasAttr("href"))
			linkURL = element.absUrl("href");
		if(element.hasAttr("src"))
			linkURL = element.absUrl("src");
		if(element.hasAttr("action"))
			linkURL = element.absUrl("action");
		if(element.hasAttr("data"))
			linkURL = element.absUrl("data");
		
		if(linkURL != null) 
		{
			link = new Link();
			link.setUrl(linkURL);
			link.setType(element.tagName());
			
		}
		return link;
	}
	

}
