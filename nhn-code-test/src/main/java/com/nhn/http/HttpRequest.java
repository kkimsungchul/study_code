package com.nhn.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String requestURL;
    private Map<String, String> parameters;

    public HttpRequest(String requestURL) {
        this.requestURL = requestURL;
        this.parameters = new HashMap<>();
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void setParameter(String name, String value) {
        parameters.put(name, value);
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }
}
