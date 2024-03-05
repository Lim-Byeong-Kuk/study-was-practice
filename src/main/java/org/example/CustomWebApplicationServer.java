package org.example;

import org.example.domain.Calculator;
import org.example.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException, InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");
                Thread.sleep(500);

                /**
                 * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
                Thread.sleep(500);

            }
        }
    }
}
