package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostalCode {
    public static PostalCode parseJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, PostalCode.class);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    String format;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    String regex;

}
