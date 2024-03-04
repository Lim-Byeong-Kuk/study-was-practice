package org.example;


import org.example.practice.HttpRequestMessage;

import java.io.IOException;

/**
 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
 * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리 하도록 한다.
 * Step3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
 */

// GET /calculate?operand1=10&operator=*&operand2=5 HTTP/1.1
public class Main {
    public static void main(String[] args) throws IOException {

        // HttpRequest 모양 확인하기
//        new HttpRequestMessage(8080).start();

        // 계산기
        new CustomWebApplicationServer(8080).start();
    }
}