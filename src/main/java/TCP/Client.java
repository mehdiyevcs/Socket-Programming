package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {

    //Creating Logger for the class
    private final static Logger LOGGER =
            Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {

        try {
            Scanner in = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 8181
            Socket socket = new Socket(ip, 8181);
            LOGGER.info("Connection Estableshed successfully");
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while(true){

                //Show possible options received from server
                System.out.println(dis.readUTF());

                //Input the chosen option
                String response=in.nextLine();
                dos.writeUTF(response);

                if(response.contains("4")){
                    LOGGER.info("Client disconnected");
                    socket.close();
                    break;
                }

                //Get the server response
                System.out.println(dis.readUTF());

            }

            in.close();
            dis.close();
            dos.close();

        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
