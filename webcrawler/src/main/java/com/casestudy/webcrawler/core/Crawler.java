package com.casestudy.webcrawler.core;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.casestudy.webcrawler.core.model.SiteMap;;

public class Crawler {
	
	private Scrapper scrapper ; 
	private String rootPage ; 
	
	private Set<String> pageLinksVisited = new HashSet<String>();
	
	private int crawlDepth = 4 ;
	
	
	public Crawler(Scrapper scrapper, String rootURL) {
		this.scrapper = scrapper;
		this.rootPage = rootURL;
	}

	
	public Scrapper getScrapper() {
		return scrapper;
	}

	public void setScrapper(Scrapper scrapper) {
		this.scrapper = scrapper;
	}

	
	public SiteMap generateSiteMap() {
		
		SiteMap siteMap = null ;
		
		
		parsePage(this.rootPage, true, 0);
		
		siteMap = scrapper.generateSiteMap();
		
		return siteMap;
		
	}
	
	
	private void parsePage(String url, boolean isRootPapge, int currentDepth) {
		
		if(currentDepth >= crawlDepth)
		{
			return ;
		}
		System.out.println("parsing page - " + url + " current depth - " + currentDepth);
		List elementsToVisit = this.scrapper.getElementsToVisit();
		
		Document page = null;
		
		try {
			
			page = Jsoup.connect(url).validateTLSCertificates(false).get();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			if( isRootPapge ) 
			{ throw new RuntimeException("Error parsing url - " + url ); }
			
			return;
		}
		
		//log(doc.title());
		
		Elements allChildElements = page.getAllElements() ;// select("a");
		for (Element element : allChildElements) {
			
			//log( headline.attr("title") + " href - " +  headline.absUrl("href"));
			scrapper.scrape(element, page, url);
		}
		
		//get all the local links 
		Elements allanchorLinks = page.select("a");
	
		for ( Element element : allanchorLinks )
		{
			String pageLink = element.absUrl("href");
			if(isLocalLink(rootPage,pageLink)){
				if(! pageLinksVisited.contains(pageLink) ) { 
					pageLinksVisited.add(pageLink);
					parsePage(pageLink, false, currentDepth + 1 );
					
				} 
			}
			
		}
		currentDepth = 0;
	
		
	}

	void log(String label) {
		System.out.println(label);
	}
	
	private boolean isLocalLink(String rootURL, String currentPageURL)
	{
		boolean isLocal = false;
		
		return (!rootURL.isEmpty() && currentPageURL.startsWith(rootURL));
		
		
	}
	
}
