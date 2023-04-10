package com.sungchul;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebCrawler {
    public static void main(String[] args) throws Exception {
        String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%BA%A0%ED%95%91%EC%9E%A5";
        Document doc = Jsoup.connect(url).get();
        Elements titles = doc.select("a.sh_blog_title._sp_each_url._sp_each_title");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(titles.get(i).text());
        }
    }
}
