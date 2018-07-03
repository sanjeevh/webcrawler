package com.casestudy.webcrawler.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.casestudy.webcrawler.core.model.Site;
import com.casestudy.webcrawler.core.model.SiteMap;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class SimpleScrapperTest {
	
	Undertow server;

	@Before
	public void setUp() throws Exception {
		
		server = Undertow.builder()
                .addHttpListener(8081, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.getResponseSender().send("<html><body><a href=\"/somepagelink\"/> </body></html>");
                    }
                }).build();
        server.start();		
	}

	@After
	public void tearDown() throws Exception {
		
		server.stop();
	}

	@Test
	public void testGenerateSiteMap1() {
		
		Crawler crawler  = new Crawler(new SimpleScrapper(), "http://localhost:8081");
		
		SiteMap sitemap = crawler.generateSiteMap();
		
		assertNotNull(sitemap);
		
		
	}
	
	@Test
	public void testGenerateSiteMap2() {
		
		Crawler crawler  = new Crawler(new SimpleScrapper(), "http://localhost:8081");
		
		SiteMap sitemap = crawler.generateSiteMap();
		
		assertNotNull(sitemap instanceof Site);
		
		
	}

	@Test
	public void testGenerateSiteMap3() {
		
		Crawler crawler  = new Crawler(new SimpleScrapper(), "http://localhost:8081");
		
		Site sitemap = (Site) crawler.generateSiteMap();
		 
		
		assertEquals(sitemap.getPages().size(), 1);
		
		
	}


}
