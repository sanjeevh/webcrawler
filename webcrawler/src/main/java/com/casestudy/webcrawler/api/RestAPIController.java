package com.casestudy.webcrawler.api;

import java.util.HashMap;
import java.util.Map;



import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.webcrawler.core.Launcher;

@RestController
@RefreshScope
public class RestAPIController {

	@RequestMapping(
			  value = "/crawl", 
			  params = { "url" }, 
			  method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE )
			@ResponseBody
			public String invokeCrawler(
			  @RequestParam("url") String url) {
		
			String responseMessage =  Launcher.launch(url);
			  
			return responseMessage;
			
	}
}
