package com.nhn.http;

import java.io.StringWriter;
import java.io.Writer;

public class HttpResponse {
    private StringBuilder content;
    private Writer writer;
    public HttpResponse(Writer writer) {
        this.content = new StringBuilder();
        this.writer = writer;
    }

    public Writer getWriter() {
        return writer;
    }

    public String getContent() {
        return content.toString();
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}