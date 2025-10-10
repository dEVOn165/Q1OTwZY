// 代码生成时间: 2025-10-10 17:34:49
// SmartQuestionBankSystem.java

/**
 * 智能题库系统
 * @author Your Name
 * @version 1.0
 */
public class SmartQuestionBankSystem {

    private QuestionMapper questionMapper; // MyBatis Mapper接口

    /**
     * 构造函数
     * @param questionMapper MyBatis Mapper接口实例
     */
    public SmartQuestionBankSystem(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    /**
     * 添加题目
     * @param question 题目对象
     * @return boolean 操作结果
     */
    public boolean addQuestion(Question question) {
        try {
            int result = questionMapper.insert(question);
            return result > 0;
        } catch (Exception e) {
            // 错误处理
            System.err.println("Error adding question: " + e.getMessage());
            return false;
        }
    }

    /**
     * 删除题目
     * @param questionId 题目ID
     * @return boolean 操作结果
     */
    public boolean deleteQuestion(int questionId) {
        try {
            int result = questionMapper.delete(questionId);
            return result > 0;
        } catch (Exception e) {
            // 错误处理
            System.err.println("Error deleting question: " + e.getMessage());
            return false;
        }
    }

    /**
     * 更新题目
     * @param question 题目对象
     * @return boolean 操作结果
     */
    public boolean updateQuestion(Question question) {
        try {
            int result = questionMapper.update(question);
            return result > 0;
        } catch (Exception e) {
            // 错误处理
            System.err.println("Error updating question: " + e.getMessage());
            return false;
        }
    }

    /**
     * 查询题目
     * @param questionId 题目ID
     * @return Question 题目对象
     */
    public Question findQuestionById(int questionId) {
        try {
            return questionMapper.selectById(questionId);
        } catch (Exception e) {
            // 错误处理
            System.err.println("Error finding question: " + e.getMessage());
            return null;
        }
    }

    /**
     * 查询所有题目
     * @return List<Question> 题目列表
     */
    public List<Question> findAllQuestions() {
        try {
            return questionMapper.selectAll();
        } catch (Exception e) {
            // 错误处理
            System.err.println("Error finding all questions: " + e.getMessage());
            return null;
        }
    }
}
