import java.io.*;
import java.net.*;

/**
 * The {@code RemoteClient} class represents a client program that connects to a TV server and sends commands to control the TV.
 * <p>
 * The client can send the following commands to the server:
 * <ul>
 *     <li><b>ON</b>: Turns on the TV.</li>
 *     <li><b>OFF</b>: Turns off the TV.</li>
 *     <li><b>CHANNEL &lt;number&gt;</b>: Sets the TV channel to the specified number.</li>
 *     <li><b>GETCHANNEL</b>: Requests the current TV channel from the server.</li>
 *     <li><b>EXIT</b>: Closes the client and exits the program.</li>
 * </ul>
 * <p>
 * The client communicates with the server over a socket connection, receives responses from the server, and displays them in the console.
 * The program runs in a continuous loop until the user enters the {@code EXIT} command.
 * </p>
 *
 * <p><b>Usage:</b></p>
 * <pre>{@code
 * java RemoteClient
 * }</pre>
 *
 * <p><b>Example interaction:</b></p>
 * <pre>
 * Connected to TV server...
 * Enter command (ON, OFF, CHANNEL <number>, GETCHANNEL, or EXIT): ON
 * FROM SERVER: TV turned ON
 * </pre>
 *
 * @author Daniel, Nikolai, Bakri, Vineet
 * @version 1.0
 * @since 2024-10-08
 */
public class RemoteClient {

    /**
     * The entry point of the client program. This method establishes a connection to the TV server,
     * sends user commands, and prints server responses.
     *
     * @param args Command line arguments (not used).
     * @throws Exception if an I/O error occurs when trying to connect or communicate with the server.
     */
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
