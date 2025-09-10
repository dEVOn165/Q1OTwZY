// 代码生成时间: 2025-09-10 13:48:40
package com.example.inventory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.List;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.mapper.InventoryMapper;

/**
 * Inventory Management System using MyBatis.
 * This class handles the inventory operations.
 */
public class InventoryManagement {

    // SqlSessionFactory object to get SqlSession
    private SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor to initialize the SqlSessionFactory.
     * @param inputStream the input stream of MyBatis configuration file.
     */
    public InventoryManagement(InputStream inputStream) {
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * Retrieves all inventory items.
     * @return List of InventoryItem objects.
     */
    public List<InventoryItem> getAllInventoryItems() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            InventoryMapper mapper = session.getMapper(InventoryMapper.class);
            return mapper.selectAllInventoryItems();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving inventory items", e);
        }
    }

    /**
     * Adds a new inventory item.
     * @param inventoryItem the InventoryItem to be added.
     * @return the inserted InventoryItem with generated ID.
     */
    public InventoryItem addInventoryItem(InventoryItem inventoryItem) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            InventoryMapper mapper = session.getMapper(InventoryMapper.class);
            mapper.insertInventoryItem(inventoryItem);
            session.commit();
            return inventoryItem;
        } catch (Exception e) {
            throw new RuntimeException("Error adding inventory item", e);
        }
    }

    /**
     * Updates an existing inventory item.
     * @param inventoryItem the InventoryItem to be updated.
     */
    public void updateInventoryItem(InventoryItem inventoryItem) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            InventoryMapper mapper = session.getMapper(InventoryMapper.class);
            mapper.updateInventoryItem(inventoryItem);
            session.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error updating inventory item", e);
        }
    }

    /**
     * Deletes an inventory item.
     * @param id the ID of the inventory item to be deleted.
     */
    public void deleteInventoryItem(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            InventoryMapper mapper = session.getMapper(InventoryMapper.class);
            mapper.deleteInventoryItem(id);
            session.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting inventory item", e);
        }
    }
}
