package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Server {

    static DatagramSocket ds;
    static DatagramPacket dp;
    final static int server_port=8081;
    static byte[] msg_rcv = new byte[65535];//byte array for received messages
    static byte[] msg_send = new byte[65535];//byte array for sending messages

    //Creating Logger for the class
    private final static Logger LOGGER =
            Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException {
        ds = new DatagramSocket(server_port);
        LOGGER.info("Server has sarted");

        while (true){

            //Receiving packet
            dp = new DatagramPacket(msg_rcv,msg_rcv.length);
            ds.receive(dp);

            if (convertToString(msg_rcv).contains("exit")) {
                LOGGER.info("Client Disconnected.");
            }else{
                //Output The Client Message
                System.out.println("Client Message: " + convertToString(msg_rcv));
            }
            //Send response to the Client
            msg_send="Message received!!!".getBytes();
            dp = new DatagramPacket(msg_send,msg_send.length,dp.getAddress(),dp.getPort());
            ds.send(dp);

            //Log that the message sent
            LOGGER.info("Response sent!");
        }

    }


    //Byte array to string
    public static String convertToString(byte[] arr){
        return new String(arr, StandardCharsets.UTF_8);
    }

}
