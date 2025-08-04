// 代码生成时间: 2025-08-04 16:26:31
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

/**
 * 统计数据分析器，用于从数据库中查询统计数据并进行分析。
 */
@Mapper
public interface StatisticsDataAnalyzer {

    /**<ol>
     * 查询统计数据
     *
     * @return 统计数据列表
     */
    @Select("SELECT * FROM statistics_data")
    List<Map<String, Object>> queryStatisticsData();

    /**
     * 计算总记录数
     *
     * @param dataList 统计数据列表
     * @return 统计数据的总记录数
     */
    default int calculateTotalRecords(List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be null or empty");
        }
        return dataList.size();
    }

    /**
     * 计算平均值
     *
     * @param dataList 统计数据列表
     * @return 平均值
     */
    default double calculateAverage(List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be null or empty");
        }
        double sum = dataList.stream()
                             .mapToDouble(map -> ((Number) map.get("value")).doubleValue())
                             .sum();
        return sum / dataList.size();
    }

    /**
     * 计算最大值
     *
     * @param dataList 统计数据列表
     * @return 最大值
     */
    default Object calculateMaxValue(List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be null or empty");
        }
        return dataList.stream()
                      .map(map -> map.get("value"))
                      .max((a, b) -> ((Number) a).doubleValue() > ((Number) b).doubleValue() ? 1 : -1)
                      .orElse(null);
    }

    /**
     * 计算最小值
     *
     * @param dataList 统计数据列表
     * @return 最小值
     */
    default Object calculateMinValue(List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be null or empty");
        }
        return dataList.stream()
                      .map(map -> map.get("value"))
                      .min((a, b) -> ((Number) a).doubleValue() < ((Number) b).doubleValue() ? 1 : -1)
                      .orElse(null);
    }
}
