// 代码生成时间: 2025-08-12 05:39:56
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

/**
 * 网页内容抓取工具
 *
 * @author Your Name
 * @version 1.0
 */
public class WebContentCrawler {

    /**
     * 抓取网页内容
     *
     * @param url 网页的URL
     * @return 网页内容
     */
    public String fetchWebContent(String url) {
        try {
            // 使用Jsoup连接到网页
            Document doc = Jsoup.connect(url).get();

            // 获取网页的HTML内容
            String html = doc.html();
            return html;
        } catch (IOException e) {
            // 错误处理
            e.printStackTrace();
            return "Error fetching web content";
        }
    }

    /**
     * 抓取网页中指定标签的内容
     *
     * @param url 网页的URL
     * @param tagName 要抓取的标签名称
     * @return 指定标签的内容
     */
    public String fetchTagContent(String url, String tagName) {
        try {
            // 使用Jsoup连接到网页
            Document doc = Jsoup.connect(url).get();

            // 根据标签名称获取元素
            Elements elements = doc.select(tagName);
            if (!elements.isEmpty()) {
                return elements.html();
            } else {
                return "No elements found for tag: " + tagName;
            }
        } catch (IOException e) {
            // 错误处理
            e.printStackTrace();
            return "Error fetching tag content";
        }
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        WebContentCrawler crawler = new WebContentCrawler();
        String url = "http://example.com";
        String tagName = "div";

        // 抓取网页内容
        String htmlContent = crawler.fetchWebContent(url);
        System.out.println("Web Content: " + htmlContent);

        // 抓取指定标签内容
        String tagContent = crawler.fetchTagContent(url, tagName);
        System.out.println("Tag Content: " + tagContent);
    }
}
