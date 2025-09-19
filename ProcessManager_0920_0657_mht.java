// 代码生成时间: 2025-09-20 06:57:32
package com.example.processmanager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * ProcessManager is a class that handles process management tasks using MyBatis framework.
 */
public class ProcessManager {

    /**
     * The factory for managing MyBatis sessions.
     */
    private SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor to initialize the SqlSessionFactory.
     * @param sqlSessionFactory The SqlSessionFactory instance.
     */
    public ProcessManager(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Retrieves a list of all running processes.
     * @return A list of process information.
     * @throws IOException If an I/O error occurs.
     */
    public List<ProcessInfo> getAllProcesses() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ProcessMapper processMapper = session.getMapper(ProcessMapper.class);
            return processMapper.selectAllProcesses();
        } catch (Exception e) {
            throw new IOException("Error retrieving processes", e);
        }
    }

    /**
     * Starts a new process.
     * @param command The command to execute.
     * @return The process ID of the newly started process.
     * @throws IOException If an I/O error occurs.
     */
    public int startProcess(String command) throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ProcessMapper processMapper = session.getMapper(ProcessMapper.class);
            int processId = processMapper.insertProcess(command);
            session.commit();
            return processId;
        } catch (Exception e) {
            throw new IOException("Error starting process", e);
        }
    }

    /**
     * Stops a process by its ID.
     * @param processId The ID of the process to stop.
     * @throws IOException If an I/O error occurs.
     */
    public void stopProcess(int processId) throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ProcessMapper processMapper = session.getMapper(ProcessMapper.class);
            processMapper.updateProcessStatus(processId, "STOPPED");
            session.commit();
        } catch (Exception e) {
            throw new IOException("Error stopping process", e);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("mybatis.config-location", "mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(props);

        ProcessManager processManager = new ProcessManager(sqlSessionFactory);
        try {
            List<ProcessInfo> processes = processManager.getAllProcesses();
            processes.forEach(process -> System.out.println(process));

            int newProcessId = processManager.startProcess("example-command");
            System.out.println("New process started with ID: " + newProcessId);

            processManager.stopProcess(newProcessId);
            System.out.println("Process with ID " + newProcessId + " stopped");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Data class representing process information.
 */
class ProcessInfo {
    private int id;
    private String command;
    private String status;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

/**
 * MyBatis mapper interface for process operations.
 */
interface ProcessMapper {
    List<ProcessInfo> selectAllProcesses();
    int insertProcess(String command);
    void updateProcessStatus(int processId, String status);
}