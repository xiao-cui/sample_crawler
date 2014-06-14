package simple_crawler;

import java.util.ArrayList;

public class Zhihu {
	public String question;
	public String zhihuUrl;
	public ArrayList<String> answers;
	
	public Zhihu() {
		question="";
		zhihuUrl="";
		answers=new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return "question:"+question+"\nurl:"+zhihuUrl+"\nanswer:"+answers+"\n";
	}
}