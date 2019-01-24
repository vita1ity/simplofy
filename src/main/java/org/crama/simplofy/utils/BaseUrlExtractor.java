package org.crama.simplofy.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class BaseUrlExtractor {

	public static String getBaseEnvLinkURL() {
		 
		
		
	   String baseEnvLinkURL=null;
	   HttpServletRequest currentRequest =
	      ((ServletRequestAttributes)RequestContextHolder.
	       currentRequestAttributes()).getRequest();
	   // lazy about determining protocol but can be done too
	   baseEnvLinkURL = "http://" + currentRequest.getLocalName();
	   if(currentRequest.getLocalPort() != 80) {
	      baseEnvLinkURL += ":" + currentRequest.getLocalPort();
	   }
	   if(!StringUtils.isEmpty(currentRequest.getContextPath())) {
	      baseEnvLinkURL += currentRequest.getContextPath();
	   }            
	   return baseEnvLinkURL;
	}
	
}
