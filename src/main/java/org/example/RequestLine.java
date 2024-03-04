package org.example;

import java.util.Objects;

public class RequestLine {

    private final String method; // GET
    private final String urlPath; // /calculate
    private QueryStrings queryStrings;   // operand1=10&operator=-&operand2=55

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryString);
    }

    /**
     * GET /calculate?operand1=10&operator=-&operand2=55 HTTP/1.1
     */
    public RequestLine(String requestLine) {

        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];    // GET
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String requestPath) {
        return urlPath.equals(requestPath);
    }


    public String getMethod() {
        return method;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public QueryStrings getQueryStrings() {
        return queryStrings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(getMethod(), that.getMethod()) && Objects.equals(getUrlPath(), that.getUrlPath()) && Objects.equals(getQueryStrings(), that.getQueryStrings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethod(), getUrlPath(), getQueryStrings());
    }
}
