package com.steve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Listening to broadcast from devices.
 */
public class BroadcastListenerThread implements Runnable {
    private DatagramSocket datagramSocket;

    public BroadcastListenerThread(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void run() {
        System.out.println("UDP Receiver thread started.");
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket inPacket = new DatagramPacket(buf, buf.length);
            try {
                datagramSocket.receive(inPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String json = new String(inPacket.getData(), 0, inPacket.getLength());
            FileUtils.writeDevicesList(json);
        }
    }
}
