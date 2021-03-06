package simple_crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
	
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
			in=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
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

	//retrieve Zhihu that are recommended by editors (i.e. questions on current page)
	static ArrayList<Zhihu> GetRecommendations(String content) {
		//use arraylist to store matched sub-string
		ArrayList<Zhihu> results=new ArrayList<Zhihu>();
		
		//define a pattern to retrieve question's url
		Pattern pattern=Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher matcher=pattern.matcher(content);
				
		//if found
		boolean isFind=matcher.find();
		while(isFind){
			//define a Zhihu object
			//retrieve question's title, question's content, and its answers through question_link
			Zhihu zhihuTemp=new Zhihu(matcher.group(1));
			//add to results
			results.add(zhihuTemp);
			//move to next matched sub-content
			isFind=matcher.find();
		}
		return results;
	}
}
