package com.luxoft.echoserver;

import com.luxoft.echoserver.thread.MultiThreadedServer;

public class Starter {
    public static void main(String[] args) {

        MultiThreadedServer threadedServer = new MultiThreadedServer();
        threadedServer.setPort(50000);
        threadedServer.setPath("src\\main\\resources\\webapp\\index.html");
        while (true) {
            new Thread(threadedServer).start();
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception.getMessage(), exception);
            } finally {
                threadedServer.stop();
                System.out.println("Stopping Server");
            }
        }
    }
}
