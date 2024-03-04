package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;

    public HttpRequest(String line) throws IOException {
        // NullPointerException
        this.requestLine = new RequestLine(line);
    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest();
    }

    public boolean matchPath(String path) {
        return requestLine.matchPath(path);
    }

    public QueryStrings getQueryString() {
        return requestLine.getQueryStrings();
    }



}
