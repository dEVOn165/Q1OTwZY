// 代码生成时间: 2025-08-26 10:18:43
package com.example.networkchecker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Network connection status checker class.
 * This class provides a method to check the network connectivity
 * by trying to connect to a specified URL.
 */
public class NetworkStatusChecker {

    /**
     * Checks the network connectivity by trying to connect to a default URL.
     * 
     * @return true if network is connected, false otherwise.
     */
    public boolean checkNetworkConnectivity() {
        return checkNetworkConnectivity("http://www.google.com");
    }

    /**
     * Checks the network connectivity by trying to connect to a specified URL.
     * 
     * @param urlString The URL to connect to for checking network connectivity.
     * @return true if network is connected, false otherwise.
     */
    public boolean checkNetworkConnectivity(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000); // 5 seconds timeout
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            // Log the exception and return false to indicate network is not connected.
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        NetworkStatusChecker checker = new NetworkStatusChecker();
        boolean isConnected = checker.checkNetworkConnectivity();
        System.out.println("Network is connected: " + isConnected);
    }
}
