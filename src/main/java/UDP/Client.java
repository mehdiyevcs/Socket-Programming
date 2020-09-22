package UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    //Static Scanner for inputing data
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        //Create socket for sending packets
        DatagramSocket ds = new DatagramSocket();
        DatagramPacket dp;

        //Get localhost address
        InetAddress inet = InetAddress.getLocalHost();

        //Arrays for received and sent messages accordingly
        byte[] rcv = new byte[65535];
        byte[] send = new byte[65535];

        System.out.println("Enter the message or write 'exit' to disconnect");

        while(true) {
            //Input the data and convert to byte
            String inp = in.nextLine();
            send = inp.getBytes();

            //if the 'exit' will be entered then break
            if (inp.contains("exit")){
                System.out.println("Disconnected!");
                break;
            }
            //Sent the input to the server
            dp = new DatagramPacket(send,send.length,inet,8081);
            ds.send(dp);

            //Receive the server message
            dp = new DatagramPacket(rcv,rcv.length);
            ds.receive(dp);

            System.out.println("Server's output:" +convertToString(rcv));
        }

    }

    //Byte Array to String
    public static String convertToString(byte[] arr){
        return new String(arr, StandardCharsets.UTF_8);
    }

}
