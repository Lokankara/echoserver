package com.luxoft.echoserver.request;

import com.luxoft.echoserver.resource.ResponseWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class RequestParser {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    RequestParser(BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.bufferedWriter = bufferedWriter;
    }

    Request parse() throws IOException {
        Request request = new Request();
        addResult(bufferedReader.readLine(), request);
        return request;
    }

    void addResult(String line, Request request) throws IOException {
        ResponseWriter responseWriter = new ResponseWriter(bufferedWriter);
        if (Objects.equals(line, null)) {
            responseWriter.error(400, " Bad Request\r\n");
        }
        String[] words = line.split(" ");
        if (words[0].equals("GET")) {
            request.setMethod(HttpMethod.GET);
        } else {
            request.setMethod(HttpMethod.POST);
            responseWriter.error(405, " Method not allowed\r\n");
        }
        request.setUri(words[1]);
    }
}