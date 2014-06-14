package simple_crawler;

import java.util.ArrayList;

public class main {
	public static void main(String[] args) {
		//define a url
		String url="http://www.zhihu.com/explore/recommendations";
		//visit the specified url and retrieve web content
		String content=Spider.sendGet(url);
		//use pre-defined pattern to find questions and their answers
		ArrayList<Zhihu> myZhihu=Spider.GetZhihu(content);
		System.out.print(myZhihu);
	}
}
