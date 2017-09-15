package com.intuit.tracker.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tracker.service.ReferralService;

@RestController
@RequestMapping("/urlreferral")
public class ReferralController {
	@Autowired
	ReferralService rService;
	 
	@RequestMapping("/getcount")
	public String getNumberOfTimesDomainReferral(@RequestParam("url") String url){
		
		UrlValidator urlValidator = new UrlValidator();
		if(urlValidator.isValid(url)){
			int slashslash = url.indexOf("//") + 2;
			String domain = url.substring(slashslash, url.indexOf('/', slashslash));			
			return rService.checkServiceReferralForDomain(domain); 
		}
		return url;
	}
	
	@RequestMapping(value="/get3highestseen", method=RequestMethod.GET, produces={"application/json"} )
	public String getNumberOfTimesDomainReferral( HttpServletResponse response){
		
		  response.setStatus(HttpServletResponse.SC_OK);
			return rService.retrieve3HighestSeen(); 
		
	}
}
