package com.luxoft.echoserver.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

    private final Socket clientSocket;
    private final String serverText;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            long currentTime = System.currentTimeMillis();
            outputStream.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                    this.serverText + " - " + currentTime + "").getBytes());
            outputStream.close();
            inputStream.close();
            System.out.println("Request processed: " + currentTime);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}