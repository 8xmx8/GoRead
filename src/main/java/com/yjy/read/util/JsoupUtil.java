package com.yjy.read.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {


    public static void main(String[] args) {
//        String html = "<html><head><title>First parse</title></head>\"\n" +
//                "  + \"<body><p>Parsed HTML into a doc.</p></body></html>";
//        Document document = Jsoup.parse(html);
//        System.out.println(document.title());
//        System.out.println(document.body());


        String str = "https://book.qidian.com/info/1021617576";
//        handler1(str);
    }

    public String handler2(String urlStr) throws Exception {
//        String url = "https://read.qidian.com/chapter/O9zPuzOQBNt7DVpbqm07HA2/CR0cESE6Ba_M5j8_3RRvhw2/";
        Document document = Jsoup.connect(urlStr).get();
        Elements vipEle = document.select("div.vip-limit-wrap");
        if (!vipEle.isEmpty()) {
            return "";
        }
        Element divEle = document.select("div.read-content.j_readContent").first();
        return divEle.text();
    }


    public void parseByDom(Document doc) {
//            System.out.println(doc.title());
//            Element element = doc.getElementById("j-catalogWrap");

        Elements elements = doc.getElementsByTag("h2");
        System.out.println(elements.size());

        for (Element h2Ele : elements) {
            Element aEle = h2Ele.getElementsByTag("a").get(0);
            String linkHref = aEle.attr("href");
            String linkText = aEle.text();
            System.out.println(linkText + " 链接为 " + linkHref);
        }
    }

}



