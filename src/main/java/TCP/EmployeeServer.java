package TCP;

import org.apache.logging.log4j.LogManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class EmployeeServer {

    private final static Logger LOGGER =
            Logger.getLogger(EmployeeServer.class.getName());

    public static void main(String[] args) throws IOException {

        //Create ServerSocket
        ServerSocket server = new ServerSocket(8181);

        while(true){
            //Accept and get a socket Object
            Socket socket = server.accept();

            LOGGER.info("Client Connected.");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            //Multithreading feature allows to handle several requests at the same time
            Thread thread = new ClientHandler(socket,dis,dos);
            thread.start();
        }

    }

}

class ClientHandler extends Thread{

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    //Constants for possible options
    final String GET_EMPL="1";
    final String ADD_EMPL="2";
    final String DELETE_EMPL="3";
    final String CLOSE="4";


    private final static Logger LOGGER =
            Logger.getLogger(ClientHandler.class.getName());

    public ClientHandler(Socket s,DataInputStream dis, DataOutputStream dos){
        this.s=s;
        this.dis=dis;
        this.dos=dos;
    }

    @Override
    public void run(){

        String received;
        String response;

        while(true){
            try {
                //Tell the client possible options
                dos.writeUTF("Select the action required\n" +
                        "1-list employees\n"+
                        "2-add employee\n"+
                        "3-delete employee\n"+
                        "4-close\n");

                //Read the input stream
                received=dis.readUTF();

                //In case of closing choosen
                if(received.contains(CLOSE)){
                    LOGGER.info("Client Disconnected");
                    break;
                }

                //Response message based on the request
                switch (received){
                    case GET_EMPL:
                        response=Employee.getEmployees().toString();
                        break;
                    case ADD_EMPL:
                        response="We are sorry,feature is not ready.";
                        break;
                    case DELETE_EMPL:
                        response="We are sorry,feature is not ready.";
                        break;
                    default:
                        response="Invalid Input!!!";
                        break;
                }

                //Send the response for client
                dos.writeUTF(response);


            }catch (IOException io){
                io.printStackTrace();
            }
        }

        try {
            this.dis.close();
            this.dos.close();
        }catch (IOException io){
            io.printStackTrace();
        }

    }



}
