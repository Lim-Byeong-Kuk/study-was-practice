package org.example;

import org.example.domain.Calculator;
import org.example.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
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


                    // 프로토콜의 첫번째 라인이 requestLine 이기 때문에 첫 번째 라인을 전달
                    // 요청이 2번 오는 문제 발생 왜 두번 오는지는 모름
                    // 첫번 째 요청을 읽을 때 readLine 하면 null 이어서 NullPointerException 생기는것 같음
                    // 첫번째 요청은 null만 있으므로 while 문을 통해 무시하게 만들어버림
                    // 두번 째 요청중에 첫번째 라인 requestLine 만 읽고 응답 보낸 후 break; 하도록 함

                    String line ="";

                    System.out.println("before while");
                    while((line = br.readLine()) != null) {
                        System.out.println("start while");
                        System.out.println("br.readLine() -> " + line);

                        //while 문이 끝나면 line 은 null 일 수 밖에 없다.

                        HttpRequest httpRequest = new HttpRequest(line);


                        // GET http://localhost:8080/calculate?operand1=10&operator=-&operand2=55
                        // GET 요청이면서 path 가 /calculate 이면
                        if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                            QueryStrings queryStrings = httpRequest.getQueryString();

                            int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                            String operator = queryStrings.getValue("operator");
                            int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                            int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                            byte[] body = String.valueOf(result).getBytes();

                            HttpResponse response = new HttpResponse(dos);
                            response.response200Header("application/json", body.length);
                            response.responseBody(body);
                        }

                        System.out.println("end while");
                        break;
                    }



                }
            }
        }
    }
}
