// 代码生成时间: 2025-09-14 23:36:13
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel表格自动生成器
 * 该类提供创建Excel表格并填充数据的功能。
 */
public class ExcelGenerator {

    private Workbook workbook;
    private Sheet sheet;
    private List<List<String>> data;

    /**
     * 构造函数，初始化Excel工作簿和工作表
     */
    public ExcelGenerator() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Data Sheet");
        data = new ArrayList<>();
    }

    /**
     * 添加标题行
     * @param headers Excel表格的标题行数据
     */
    public void addHeaderRow(List<String> headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
        }
    }

    /**
     * 添加数据行
     * @param rowData Excel表格的一行数据
     */
    public void addDataRow(List<String> rowData) {
        data.add(rowData);
    }

    /**
     * 填充数据到Excel表格
     */
    public void fillData() {
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data.get(i).size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(data.get(i).get(j));
            }
        }
    }

    /**
     * 将Excel表格写入到文件
     * @param filePath 输出文件的路径
     */
    public void writeToFile(String filePath) {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除所有数据，以便生成新的Excel文件
     */
    public void reset() {
        data.clear();
    }

    /**
     * 主方法，用于演示Excel生成器的使用
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        ExcelGenerator generator = new ExcelGenerator();
        generator.addHeaderRow(List.of("Name", "Age", "City"));

        // 添加一些示例数据
        generator.addDataRow(List.of("John Doe", "30", "New York"));
        generator.addDataRow(List.of("Jane Doe", "25", "Los Angeles"));

        generator.fillData();
        generator.writeToFile("example.xlsx");

        // 重置生成器以便下次使用
        generator.reset();
    }
}