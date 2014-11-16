import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
      BufferedReader inFromUser =
         new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      String sentence = inFromUser.readLine();
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      String modifiedSentence = new String(receivePacket.getData());
      System.out.println("FROM SERVER:" + modifiedSentence);
      clientSocket.close();
   }
}
/*

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

final DatagramSocket socket = new DatagramSocket();
final string String MULTICAST_GROUP_ID = "224.0.0.224";
final int PORT = 2244;
byte[] buf = new byte[256];
buf = "Hello World!".getBytes();
final InetAddress group = InetAddress.getByName(MULTICAST_GROUP_ID);
DatagramPacket packet = new DatagramPacket(buf, buf.length, group, PORT);
socket.send(packet);
socket.close();
*/