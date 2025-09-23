// 代码生成时间: 2025-09-24 00:36:39
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebContentCrawler {

    // 构造方法
    public WebContentCrawler() {
    }

    // 方法：抓取网页内容
    public String fetchWebContent(String url) {
        try {
            // 创建HttpClient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建HttpGet实例
            HttpGet httpGet = new HttpGet(url);
            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 检查实体是否为空
                if (entity != null) {
                    // 将响应实体内容转换为字符串
                    String htmlContent = EntityUtils.toString(entity);
                    // 使用Jsoup解析HTML内容
                    return parseHtmlContent(htmlContent);
                }
            }
        } catch (IOException e) {
            // 错误处理
            System.err.println("Error fetching web content: " + e.getMessage());
        }
        return null;
    }

    // 方法：解析HTML内容
    private String parseHtmlContent(String htmlContent) {
        try {
            // 使用Jsoup解析HTML
            Document document = Jsoup.parse(htmlContent);
            // 假设我们要抓取所有段落内容
            Elements paragraphs = document.select("p");
            StringBuilder contentBuilder = new StringBuilder();
            for (Element paragraph : paragraphs) {
                // 将每个段落的内容添加到StringBuilder中
                contentBuilder.append(paragraph.text()).append("
");
            }
            return contentBuilder.toString().trim();
        } catch (Exception e) {
            // 错误处理
            System.err.println("Error parsing HTML content: " + e.getMessage());
        }
        return null;
    }

    // 主方法：用于测试WebContentCrawler类
    public static void main(String[] args) {
        WebContentCrawler crawler = new WebContentCrawler();
        String url = "https://example.com";
        String content = crawler.fetchWebContent(url);
        if (content != null) {
            System.out.println("Fetched content: 
" + content);
        } else {
            System.out.println("Failed to fetch content.");
        }
    }
}
