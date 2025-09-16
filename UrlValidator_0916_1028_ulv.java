// 代码生成时间: 2025-09-16 10:28:34
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * URL Validator class to check the validity of a given URL.
 */
public class UrlValidator {

    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Method to validate the URL.
     *
     * @param url The URL to be validated.
     * @return true if the URL is valid, false otherwise.
     */
    public boolean isValidUrl(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpHead request = new HttpHead(url);
                request.setHeader("User-Agent", USER_AGENT);
                CloseableHttpResponse response = httpClient.execute(request);

                try {
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == HttpStatus.SC_OK) {
                        return true;
                    } else {
                        return false;
                    }
                } finally {
                    EntityUtils.consume(response.getEntity());
                }
            } finally {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        UrlValidator validator = new UrlValidator();
        String testUrl = "http://example.com";

        if (validator.isValidUrl(testUrl)) {
            System.out.println("The URL is valid.");
        } else {
            System.out.println("The URL is not valid.");
        }
    }
}