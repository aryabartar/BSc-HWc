package Network;

import Engine.GameLoop;
import Engine.Main;

import java.io.IOException;
import java.net.*;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private GameLoop main;

    public GameServer(GameLoop main) {
        this.main = main;
        try {
            this.socket = new DatagramSocket(1333);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String massage = new String(packet.getData());
            System.out.println("Client [" + packet.getAddress() +":" + packet.getPort() + "] > " + new String(packet.getData()));
            sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
