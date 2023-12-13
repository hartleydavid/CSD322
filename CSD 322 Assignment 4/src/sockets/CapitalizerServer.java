/* Assignment 4 Server side file
 * David Hartley
 * 3/1/2023
 */
package sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//192.168.1.14
public class CapitalizerServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(59090)) {
            System.out.println("The capitalizer server is running...");

            while (true) {
                try (Socket socket = listener.accept()) {
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    //Create a data input stream to read input from the client
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    //Get input from client
                    String clientInput = dataInputStream.readUTF();

                    //If the exit keyword was entered: close socket and terminate
                    if (clientInput.equals("<exit>")){
                        System.out.println("Closing the Socket");
                        output.println("Socket has closed");
                        socket.close();
                        break;
                    }
                    //Convert to uppercase
                    clientInput = clientInput.toUpperCase();
                    //Send input back to the client
                    output.println(clientInput);
                }
            }
        }
    }
}