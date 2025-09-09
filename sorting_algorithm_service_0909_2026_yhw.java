// 代码生成时间: 2025-09-09 20:26:21
package com.example.service;

import com.example.model.Algorithm;
import com.example.mapper.AlgorithmMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import java.util.Collections;

public class SortingAlgorithmService {

    private final SqlSessionFactory sqlSessionFactory;

    public SortingAlgorithmService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Retrieves a list of algorithms from the database.
     *
     * @return List of Algorithm objects.
     */
    public List<Algorithm> retrieveAlgorithms() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AlgorithmMapper mapper = session.getMapper(AlgorithmMapper.class);
            return mapper.selectAllAlgorithms();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve algorithms", e);
        }
    }

    /**
     * Sorts the given list of algorithms using the specified algorithm type.
     *
     * @param algorithms List of Algorithm objects to be sorted.
     * @param algorithmType Type of the algorithm to use for sorting.
     * @return Sorted list of Algorithm objects.
     */
    public List<Algorithm> sortAlgorithms(List<Algorithm> algorithms, String algorithmType) {
        if (algorithms == null || algorithmType == null) {
            throw new IllegalArgumentException("Algorithms and algorithm type must not be null");
        }

        switch (algorithmType.toLowerCase()) {
            case "bubble":
                bubbleSort(algorithms);
                break;
            case "quick":
                quickSort(algorithms, 0, algorithms.size() - 1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm type: " + algorithmType);
        }

        return algorithms;
    }

    /**
     * Bubble Sort Algorithm.
     *
     * @param algorithms List of Algorithm objects to be sorted.
     */
    private void bubbleSort(List<Algorithm> algorithms) {
        for (int i = 0; i < algorithms.size() - 1; i++) {
            for (int j = 0; j < algorithms.size() - i - 1; j++) {
                if (algorithms.get(j).compareTo(algorithms.get(j + 1)) > 0) {
                    Collections.swap(algorithms, j, j + 1);
                }
            }
        }
    }

    /**
     * Quick Sort Algorithm.
     *
     * @param algorithms List of Algorithm objects to be sorted.
     * @param low Lower index of the range to be sorted.
     * @param high Upper index of the range to be sorted.
     */
    private void quickSort(List<Algorithm> algorithms, int low, int high) {
        if (low < high) {
            int pi = partition(algorithms, low, high);
            quickSort(algorithms, low, pi - 1);
            quickSort(algorithms, pi + 1, high);
        }
    }

    /**
     * Partition method for Quick Sort.
     *
     * @param algorithms List of Algorithm objects to be sorted.
     * @param low Lower index of the range to be partitioned.
     * @param high Upper index of the range to be partitioned.
     * @return Pivot index.
     */
    private int partition(List<Algorithm> algorithms, int low, int high) {
        Algorithm pivot = algorithms.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (algorithms.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(algorithms, i, j);
            }
        }
        Collections.swap(algorithms, i + 1, high);
        return i + 1;
    }
}
