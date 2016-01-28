package com.test.hello.domain;

public class Hi {

    private final long id;
    private final String content;

    public Hi(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
