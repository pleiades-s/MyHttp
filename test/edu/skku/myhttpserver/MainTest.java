package edu.skku.myhttpserver;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private HttpServer server;

    @BeforeEach
    void setUp() throws IOException {
        // Run server
        server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        List<Main.AbstractHttpHandler> handlers = List.of(
                new Main.EchoHandler()
        );

        for (Main.AbstractHttpHandler handler: handlers) {
            server.createContext(handler.getPath(), handler);
        }

        server.setExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        server.start();
    }

    @Test
    void sendGET() throws IOException {
        URL obj = new URL("https://localhost:8080/echo");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }
    @AfterEach
    void tearDown() {
        // Close server
        server.stop(0);
    }
}