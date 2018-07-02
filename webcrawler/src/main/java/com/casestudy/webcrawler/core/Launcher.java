package com.casestudy.webcrawler.core;

import java.io.IOException;

import com.casestudy.webcrawler.core.model.SiteMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Launcher {

	public static String launch(String url) {

		Scrapper scrapper = new SimpleScrapper();
		Crawler crawler = new Crawler(scrapper,url);
		
		String responseMessage = null;
		
		SiteMap siteMap = null ;
		try {
			siteMap = crawler.generateSiteMap();
		} catch (Exception e) {
			responseMessage = e.getMessage();
			
			e.printStackTrace();
			return "{\"error\" :" +  responseMessage + "}";
		}
		
		responseMessage = convertToJsonString(siteMap);
		
		System.out.println("sitemap completed \n" + responseMessage);	
		
		return responseMessage;
	}

	
	private static String convertToJsonString(SiteMap siteMap) {
		
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = null ;
        
        try {
            
            jsonStr = mapperObj.writeValueAsString(siteMap);
            
           
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return jsonStr ;
	}		

}
