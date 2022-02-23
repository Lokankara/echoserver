package com.luxoft.echoserver.resource;

import java.io.*;

public class ResourceReader {

    private BufferedWriter bufferedWriter;

    ResponseWriter responseWriter = new ResponseWriter(bufferedWriter);

    public ResourceReader(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public InputStream readResource(String uri, String path) throws IOException {
        File resource = new File(path, uri);
        if (!resource.exists()) {
            responseWriter.error(404, " Not Found \r\n");
        }
        return new FileInputStream(resource);
    }
}