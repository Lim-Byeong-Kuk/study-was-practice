package org.example.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpRequestMessage {
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestMessage.class);

    public HttpRequestMessage(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");

                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */
                // InputStream 을 Reader 로 바꾸고 싶다.
                // 라인 바이 라인으로 읽고 싶기 때문, 그래서 InputStreamReader 로 감싸줌
                // 그리고 한번더 BufferedReader 로 감싸면 라인 by 라인으로 읽기 가능
                try(InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

                    String line;
                    while((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }
        }
    }
}