package Network;

import Engine.GameLoop;
import Engine.Main;
import Equipment.Tank;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.*;

public class GameClient extends Thread {

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private Tank tank;

    public GameClient(Tank tank, String ipAddress) {
        this.tank = tank;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024] ;
            DatagramPacket packet = new DatagramPacket(data , data.length) ;

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server > " + new String(packet.getData()));
        }
    }

    public void sendData (byte[] data) {
        DatagramPacket packet = new DatagramPacket(data , data.length , ipAddress , 1333) ;
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
