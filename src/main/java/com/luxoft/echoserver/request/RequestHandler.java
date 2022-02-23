package com.luxoft.echoserver.request;

import com.luxoft.echoserver.resource.ResourceReader;
import com.luxoft.echoserver.resource.ResponseWriter;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
public class RequestHandler {

    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;
    private final String path;

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser(socketWriter, socketReader);
        ResourceReader reader = new ResourceReader(socketWriter);
        ResponseWriter writer = new ResponseWriter(socketWriter);

        Request request = requestParser.parse();
        InputStream inputStream = reader.readResource(request.getUri(), path);
        writer.success(inputStream);
    }
}
