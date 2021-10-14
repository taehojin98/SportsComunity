package com.sport.jth.repository;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class NewsCrawling {

	public String setURL(String category) {
		String naverNewsURL = "https://m.sports.naver.com/" + category + "/index";
		return naverNewsURL;
	}
		
	public Elements getBaseballNews(String category) {
		
		try {
			Document doc = Jsoup.connect(setURL(category)).get();
			Elements temp = doc.getElementsByAttributeValue("class", "lst_content");
			
			return temp;
		} catch (IOException e) {
			throw new RuntimeException("오류가 발생하였습니다.");
		}
		
	}
	
}
