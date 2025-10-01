// 代码生成时间: 2025-10-02 00:00:23
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 代码高亮规则枚举，可以根据需要扩展其他语言的高亮规则
public enum HighlightRule {
    JAVA("java", "\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|if|goto|implements|import|instanceof|int|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\b"),
    SQL("sql", "\b(SELECT|UPDATE|DELETE|INSERT|FROM|WHERE|AND|OR|GROUP BY|HAVING|ORDER BY|AS|ON|IN|JOIN)\b");

    private String language;
    private String regex;

    public HighlightRule(String language, String regex) {
        this.language = language;
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public String getLanguage() {
        return language;
    }
}

// 代码高亮器类
public class CodeHighlighter {

    private SqlSessionFactory sqlSessionFactory;

    public CodeHighlighter(String config) {
        try {
            // 使用MyBatis配置文件创建SqlSessionFactory
            InputStream inputStream = CodeHighlighter.class.getClassLoader().getResourceAsStream(config);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 高亮代码的方法
    public String highlightCode(String language, String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be null or empty");
        }

        // 根据语言选择高亮规则
        HighlightRule rule = null;
        switch (language.toLowerCase()) {
            case "java":
                rule = HighlightRule.JAVA;
                break;
            case "sql":
                rule = HighlightRule.SQL;
                break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }

        // 使用正则表达式匹配并高亮代码
        Pattern pattern = Pattern.compile(rule.getRegex());
        Matcher matcher = pattern.matcher(code);
        StringBuffer highlightedCode = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(highlightedCode, "<span style='color: red;'>" + matcher.group() + "</span>");
        }
        matcher.appendTail(highlightedCode);

        return highlightedCode.toString();
    }

    // 关闭SqlSessionFactory资源
    public void close() {
        if (sqlSessionFactory != null) {
            sqlSessionFactory.close();
        }
    }
}

// 用于存储代码高亮样式的MyBatis映射接口
public interface HighlightMapper {
    // 可以根据需要添加更多的方法来获取不同的高亮规则
    List<HighlightRule> getHighlightRules();
}
