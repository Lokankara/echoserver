package com.luxoft.echoserver.resource;

import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class ResponseWriter {

    private final BufferedWriter bufferedWriter;

    public void success(InputStream inputStream) throws IOException {
        try {
            bufferedWriter.write("HTTP/1.1 200 OK\n\r");
            byte[] buffer = new byte[2048];
            while (inputStream.read(buffer) > 0) {
                String content = new String(buffer, StandardCharsets.UTF_8);
                bufferedWriter.write(content);
            }
        } catch (IOException exception) {
            error(404, " Not Found \r\n");
        }
    }

    public void error(int code, String message) throws IOException {
        bufferedWriter.write("HTTP/1.1 " + code + " " + message);
    }
}