package com.yjy.read.util;


import com.yjy.read.entity.Author;
import com.yjy.read.entity.Book;
import com.yjy.read.entity.Chapter;
import com.yjy.read.entity.ChapterContent;
import com.yjy.read.service.AuthorService;
import com.yjy.read.service.BookService;
import com.yjy.read.service.ChapterContentService;
import com.yjy.read.service.ChapterService;
import com.yjy.read.util.vo.ChapterBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JsoupUtil {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterContentService chapterContentService;

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    public static void main(String[] args) throws Exception {
//        String html = "<html><head><title>First parse</title></head>\"\n" +
//                "  + \"<body><p>Parsed HTML into a doc.</p></body></html>";
//        Document document = Jsoup.parse(html);
//        System.out.println(document.title());
//        System.out.println(document.body());


        String str = "https://book.qidian.com/info/1021617576";
        //handler1(str, 1021617576);

    }


    public void handler(String urlStr) {

//        List<String> resultList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(urlStr).get();

            Author author = CreateByUrl(doc);

            authorService.insertIfNotExists(author);
            String authorName = author.getName();

            Book book = CreateBook(doc);
            book.setAuthor(authorName);
            bookService.save(book);

            List<ChapterBean> beans = parseBySelector(doc);

            for (ChapterBean bean : beans) {
                String result = handler2(bean.getLink());
                if (Objects.equals(result, "")) return;

                ChapterContent content = new ChapterContent();
                content.setContent(result);
                chapterContentService.save(content);
                long contentId = content.getId();

                Chapter chapter = new Chapter();
                chapter.setContentId(contentId);
                chapter.setBookId(book.getId());
                chapter.setName(bean.getName());
//                resultList.add(result);
                chapterService.save(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(resultList.size());

    }

    public Author CreateByUrl(Document doc) {
        Elements author_name = doc.select("meta[property=og:novel:author]");
        String authorName = author_name.first().attr("content");
        Author author = new Author();
        author.setName(authorName);

        return author;
    }

    public Book CreateBook(Document doc) {
        Book book = new Book();
        Elements book_name = doc.select("meta[property=og:novel:book_name]");
        String bookName = book_name.first().attr("content");
        Elements book_description = doc.select("meta[property=og:description]");
        String description = book_description.first().attr("content");
        Elements emElements = doc.select("p em");
        Elements citeElements = doc.select("p cite");


        book.setName(bookName);
        book.setDescription(description);

        String emText = emElements.first().text();
        String citeText = citeElements.first().text();

        if (citeText.contains("万")) {
            Integer count = (int) (Float.parseFloat(emText) * 10000);
            book.setCount(count);
        }
        Element spanElement = doc.select("span:contains(完本)").first();

        if (spanElement != null) {
            String type = spanElement.text();
            book.setType(type);
        }
        return book;
    }

    public String handler2(String urlStr) throws Exception {
//        String url = "https://read.qidian.com/chapter/O9zPuzOQBNt7DVpbqm07HA2/CR0cESE6Ba_M5j8_3RRvhw2/";
        Document document = Jsoup.connect(urlStr).get();
        Elements vipEle = document.select("vip.vip-limit-wrap");
        if (!vipEle.isEmpty()) {
            return "";
        }
        Elements divEle = document.select("main p");
        System.out.println(divEle.text());
        return divEle.text();
    }

    public List<ChapterBean> parseBySelector(Document doc) {
        List<ChapterBean> links = new ArrayList<>();
        // css选择器
        Elements elements = doc.select("h2.book_name > a");
        for (Element h2Ele : elements) {
            Element aEle = h2Ele.getElementsByTag("a").get(0);

            ChapterBean bean = new ChapterBean();
            bean.setLink("https:" + aEle.attr("href"));
            bean.setName(aEle.text());
            links.add(bean);
        }
        return links;
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
