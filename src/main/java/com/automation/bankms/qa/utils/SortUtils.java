package com.automation.bankms.qa.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortUtils {

	public boolean islistsortedinascendingorder(ArrayList<String> arrlist)
	{
		List<String> checkingsort=new ArrayList<String>(arrlist);
		Collections.sort(checkingsort,String.CASE_INSENSITIVE_ORDER);
		
		return arrlist.equals(checkingsort);
		
		
	}
	
	public boolean islistsortedindescendingorder(ArrayList<String> arrlist)
	{
		List<String> checkingreversesort=new ArrayList<String>(arrlist);
		Collections.sort(checkingreversesort,String.CASE_INSENSITIVE_ORDER.reversed());
		
		return arrlist.equals(checkingreversesort);
		
		
	}
	
	public boolean ismobilenumbersortedinascendingorder(List<Long> arrlist)
	{
		List<Long> checkingsort=new ArrayList<Long>(arrlist);
		Collections.sort(checkingsort);
		
		return arrlist.equals(checkingsort);
		
		
	}
	
	public boolean ismobilenumbersortedindescendingorder(List<Long> arrlist)
	{
		List<Long> checkingsort=new ArrayList<Long>(arrlist);
		Collections.sort(checkingsort,Collections.reverseOrder());
		
		return arrlist.equals(checkingsort);
		
		
	}

}
