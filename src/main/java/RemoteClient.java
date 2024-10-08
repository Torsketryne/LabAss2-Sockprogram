import java.io.*;
import java.net.*;

class RemoteClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Connect to the server at localhost on port 6789
        Socket clientSocket = new Socket("localhost", 6789);
        System.out.println("Connected to TV server...");

        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String sentence;
        String modifiedSentence;

        // Continuous loop to send commands to the server
        while (true) {
            System.out.print("Enter command (ON, OFF, CHANNEL <number>, GETCHANNEL, or EXIT): ");
            sentence = inFromUser.readLine(); // Read input from the user

            // Exit the client if the user inputs "EXIT"
            if (sentence.equalsIgnoreCase("EXIT")) {
                outToServer.println(sentence);
                System.out.println("Exiting Remote Client");
                break;
            }

            // Send the command to the server
            outToServer.println(sentence);

            // Receive and print the server response
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
        }

        // Close the connection after EXIT command
        clientSocket.close();
    }
}
