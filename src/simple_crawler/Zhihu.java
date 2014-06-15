package simple_crawler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhihu {
	public String question;
	public String questionDescription;
	public String zhihuUrl;
	public ArrayList<String> answers;
	
	public Zhihu(String url) {
		question="";
		questionDescription="";
		zhihuUrl="";
		answers=new ArrayList<String>();
		
		//determine if url is matched
		//if not, covert it according to pre-defined pattern
		if(getRealUrl(url)){
			System.out.println("正在抓取"+zhihuUrl);
			//according to zhihuUrl, retrieve question title and its answers
			String content=Spider.sendGet(zhihuUrl);
			Pattern pattern;
			Matcher matcher;
			//looking for question's title, surrounded by a pair of <h2></h2>
			pattern=Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
			matcher=pattern.matcher(content);
			if(matcher.find()){
				question=matcher.group(1);
			}
			//looking for question's content, surrounded by a pair of <div></div>
			pattern=Pattern.compile("zh-question-detail.+?<div.+?>(.*?)</div>");
			matcher=pattern.matcher(content);
			if(matcher.find()){
				questionDescription=matcher.group(1);
			}
			//looking for answers, surrounded by a pair of <div></div>
			//retrieve multiple answers by while-loop
			pattern=Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
			matcher=pattern.matcher(content);
			boolean isFind=matcher.find();
			while(isFind){
				answers.add(matcher.group(1));
				isFind=matcher.find();
			}
		}
	}
	
	@Override
	public String toString() {
		return "question:"+question+"\n"+"description:"+questionDescription+"\n"+"url:"+zhihuUrl+"\n"+"answer:"+answers+"\n";
	}
	
	//处理url
	/*
	 * 在Zhihu的构造方法里面,通过url获取所有的详细数据.
	 * 我们先要对url进行一个处理,因为有的针对回答的,它的url是:
	 * http://www.zhihu.com/question/22355264/answer/21102139
	 * 有的针对问题的,它的url是:
	 * http://www.zhihu.com/question/22355264
	 * 那么我们显然需要的是第二种,所以需要用正则把第一种链接裁切成第二种
	 */
	boolean getRealUrl(String url) {
		//将http://www.zhihu.com/question/22355264/answer/21102139
		//转换成http://www.zhihu.com/question/22355264
		//否则不变
		Pattern pattern=Pattern.compile("question/(.*?)/");
		Matcher matcher=pattern.matcher(url);
		if(matcher.find()){
			zhihuUrl="http://www.zhihu.com/question/"+matcher.group(1);
		}else{
			return false;
		}
		return true;
	}
	
	// 根据自己的url抓取自己的问题和描述和答案  
    public boolean getAll() {
        return true;  
    }
    
    //给知乎的Zhihu封装类加个函数，用来格式化写入到本地时的排版
    public String writeString(){
    	String result="";
    	result+="Question: "+question+"\r\n";
    	result+="Description: "+questionDescription+"\r\n";
    	result+="URL: "+zhihuUrl+"\r\n";
    	for(int i=0; i<answers.size(); i++){
    		result+="Answer"+"["+i+"]: "+answers.get(i)+"\r\n";
    	}
    	result+="\r\n\r\n\r\n";
    	
    	//把<br>换成io流里面的\r\n，再把所有的html标签都删除，这样看起来便会清晰很多
    	result=result.replaceAll("<br>", "\r\n");
    	result=result.replaceAll("<.*?>", "");
    	return result;
    }
    
	
}