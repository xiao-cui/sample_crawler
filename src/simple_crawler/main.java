package simple_crawler;

import java.util.ArrayList;

public class main {
	public static void main(String[] args) {
		//define a url
		String url="http://www.zhihu.com/explore/recommendations";
		//visit the specified url and retrieve web content
		String content=Spider.sendGet(url);
		//retrieve recommended questions
		ArrayList<Zhihu> myZhihu=Spider.GetRecommendations(content);
		System.out.print(myZhihu);
	}
}
