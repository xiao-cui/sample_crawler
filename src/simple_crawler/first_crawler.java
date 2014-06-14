package simple_crawler;

import java.io.*;
import java.net.*;

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
		}finally{
			//close inputstreamreader through keyword 'finnaly'
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
			System.out.print(result);
		}
		return result;
	}

	public static void main(String[] args) {
		//define a url
		String url="http://www.baidu.com";
		//visit the specified url and retrieve web content
		String result=sendGet(url);
		System.out.println(result);
	}

}
