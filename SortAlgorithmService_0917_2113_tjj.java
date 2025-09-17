// 代码生成时间: 2025-09-17 21:13:45
package com.example.sortalgorithm;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Service class for sorting algorithms.
 * This class provides methods to sort data using various sorting algorithms.
 * It uses MyBatis framework for database operations.
 */
public class SortAlgorithmService {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor that initializes the SqlSessionFactory.
     * @param inputStream The input stream for the MyBatis configuration file.
     */
    public SortAlgorithmService(InputStream inputStream) {
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * Sorts the data using the specified sorting algorithm.
     * @param algorithm The sorting algorithm to use.
     * @return The sorted list of data.
     */
    public List<Integer> sortData(SortingAlgorithm algorithm) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Retrieve data from the database
            List<Integer> data = session.selectList("selectAllData");

            // Sort the data using the chosen algorithm
            Collections.sort(data, algorithm.getComparator());

            // Return the sorted data
            return data;
        } catch (Exception e) {
            // Handle any exceptions that occur during sorting or database operations
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Enum representing different sorting algorithms.
     */
    public enum SortingAlgorithm {
        BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, MERGE_SORT, QUICK_SORT;

        /**
         * Returns a comparator based on the chosen sorting algorithm.
         * @return A comparator for the sorting algorithm.
         */
        public Comparator<Integer> getComparator() {
            switch (this) {
                case BUBBLE_SORT:
                    return (o1, o2) -> Integer.compare(o2, o1);
                case SELECTION_SORT:
                    return (o1, o2) -> Integer.compare(o1, o2);
                case INSERTION_SORT:
                    return (o1, o2) -> Integer.compare(o1, o2);
                case MERGE_SORT:
                    return (o1, o2) -> Integer.compare(o1, o2);
                case QUICK_SORT:
                    return (o1, o2) -> Integer.compare(o1, o2);
                default:
                    throw new IllegalArgumentException("Invalid sorting algorithm");
            }
        }
    }
}
