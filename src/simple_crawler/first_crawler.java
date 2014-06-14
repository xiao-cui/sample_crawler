package simple_crawler;

//实例来自http://blog.csdn.net/pleasecallmewhy/article/details/17538809

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class first_crawler{
	
	static String sendGet(String url) {
		//define a variable storing web content
		String result="";
		//define a butter
		BufferedReader in=null;
		
		try{
			//convert string to url
			URL realUrl=new URL(url);
			//initiate a connection to the specified url
			URLConnection connection=realUrl.openConnection();
			//start connect
			connection.connect();
			//initiate bufferedreader
			in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//define a variable storing web content temporarily line by line
			String line;
			while ((line=in.readLine())!=null) {
				//explore web content line by line and write to 'result'
				result+=line;
			}
		}catch(Exception e){
			System.out.println("发送GET请求出现异常"+e);
			e.printStackTrace();
		}finally{
			//close inputstreamreader through keyword 'finnaly'
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return result;
	}

	static String RegexString(String targetStr, String patternStr) {
		//define a pattern
		Pattern pattern=Pattern.compile(patternStr);
		//define a matcher
		Matcher matcher=pattern.matcher(targetStr);
		//if found
		if(matcher.find()){
			return matcher.group(1);
		}
		return "Nothing";
	}
	
	public static void main(String[] args) {
		//define a url
		String url="http://www.zhihu.com/explore/recommendations";
		//visit the specified url and retrieve web content
		String result=sendGet(url);
		//use pattern to find src content
		String imgSrc=RegexString(result, "question_link.+?>(.+?)<");
		System.out.println(imgSrc);
	}

}
