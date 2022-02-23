package com.luxoft.echoserver.thread;

import com.luxoft.echoserver.request.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultiThreadedServer implements Runnable {

    private int port;
    private String path;
    private boolean isStopped = false;
    private Thread runningThread = null;
    private ServerSocket serverSocket = null;

    public void setPath(String path) {
        this.path = path;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            WorkerRunnable thread = new WorkerRunnable(clientSocket, "Multithreaded Server");
            new Thread(thread).start();
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException exception) {
            throw new RuntimeException("Error closing server", exception);
        }
    }

    private void openServerSocket() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server start work on port " + port);
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    RequestHandler requestHandler = new RequestHandler(socketReader, socketWriter, path);
                    requestHandler.handle();
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Cannot open port 50000", exception);
        }
    }
}