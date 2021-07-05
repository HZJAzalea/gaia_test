package com.cn.utils;

import com.alibaba.fastjson.JSONPath;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternUtil {
	
	/**
	 * 定义正则表达式
	 */
    private static String compareResultRegex = "([\\$\\.\\w\\[\\]]+)=(\\w+)";

	/**
	 * @Title: getMatcher   
	 * @Description: TODO
	 * @param: @param regex
	 * @param: @param data
	 * @param: @return      
	 * @return: Matcher      
	 * @throws
	 */
	public static Matcher getMatcher(String regex,String data){

		Pattern pattern = null;
		Matcher matcher = null;
		
		try{
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return matcher;
	}
	
	/**
	 * @Title: compareResult
	 * @Description: TODO
	 * @param: @param expResult
	 * @param: @param actResult
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int compareResult(String expResult,String actResult){

		int flag = 0;
		List<Integer> list = new ArrayList<Integer>();
		
		Matcher matcher = getMatcher(compareResultRegex,expResult);

		while (matcher.find()){
		    if(actResult == null){
		        flag = 0;
            }else {
                flag = JSONPath.read(actResult, matcher.group(1)).toString().equals(matcher.group(2)) ? 1:0;
            }
		    list.add(flag);
		}
		
		if(!list.contains(0)){
			flag = 1;
		}
		
		return flag;
	}


}
