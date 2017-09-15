package com.intuit.tracker.service;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.intuit.tracker.util.ValueComparator;

@Service
public class ReferralService {
    private HashMap<String,Integer> serviceReferral = new HashMap<String,Integer>();
    
    public ReferralService(){
    	serviceReferral.put("google.com",3);
    	serviceReferral.put("yahoo.com",2);
    	serviceReferral.put("hotmail.com",33);
    	serviceReferral.put("gmail.com",23);
    	serviceReferral.put("newmail.com",313);
    } 
    
	public String checkServiceReferralForDomain(String domainId){
		return  serviceReferral.get(domainId) == null? "0" : serviceReferral.get(domainId)+"";		
	}
	
	public String retrieve3HighestSeen(){
		Comparator<String> comparator = new ValueComparator<String, Integer>(serviceReferral);
		TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
		result.putAll(serviceReferral);
		System.out.println(result);
		
		StringBuffer sb = new StringBuffer();
		 result.entrySet().stream()
			    .limit(3)
			    .collect(TreeMap::new, (m, e) -> sb.append("{\"url\":\""+e.getKey() +"\",\"count\":\""+ e.getValue() +"\"},"), Map::putAll);
		
		 System.out.println(sb);

		sb.setLength(sb.length()-1);
		return "{\"result\":["+sb+"]}";
	}
}
  