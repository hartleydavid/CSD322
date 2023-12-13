/* Assignment 4 Client side files
 * David Hartley
 * 3/1/2023
 */

package sockets;

import java.io.DataOutputStream;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

public class CapitalizerClient {
    public static void main(String[] args) throws IOException {
        //If no argument is passed in the command line/ configuration
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }

        //192.168.1.14
        //"localhost"
        do {
            //Create socket and connect to server
            Socket socket = new Socket(args[0], 59090);
            //Create output stream to send data to server
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //Create scanner to read server response
            Scanner in = new Scanner(socket.getInputStream());
            //Prompt user for input
            String userInput = promptUser();


            //Send user input to server and get server response
            dataOutputStream.writeUTF(userInput);
            System.out.println("Server response: " + in.nextLine());

            //Exit Case. Server socket closes, now close client socket and terminate program
            if(userInput.equals("<exit>")){
                System.out.println("Closing the socket...");
                socket.close();
                break;
            }
        } while (true);
    }

    /** Function prompts the user for input and returns what the user typed
     *
     * @return String of user input
     */
    private static String promptUser(){
        //Create scanner for user input
        Scanner readInput = new Scanner(System.in);
        //Prompt user and return input
        System.out.println("Please enter a line of text:");
        return readInput.nextLine();
    }
}