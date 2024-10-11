import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * The {@code TvActiveServer} class is an abstract server class that provides the basic structure
 * for a TV server, including running the server and handling client connections.
 * Subclasses should implement the {@code handleCommand} method to process specific client commands.
 * <p>
 * The server listens for incoming connections from clients on port 6789 and processes client messages in a continuous loop.
 * </p>
 *
 * <p><b>Usage:</b></p>
 * <pre>{@code
 * public class SmartTv extends TvActiveServer {
 *     @Override
 *     public String handleCommand(String clientMessage) {
 *         // Handle the client command and return a response
 *     }
 * }
 * }</pre>
 *
 * @author Daniel, Nikolai, Bakri, Vineet
 * @version 1.0
 * @since 2024-10-08
 */
public abstract class TvActiveServer {

  /**
   * Starts the TV server, listens for client connections, and processes commands.
   * This method runs in a continuous loop until the client sends the "EXIT" command.
   *
   * @throws Exception If an error occurs while establishing the server or processing client connections.
   */
  public void runServer() throws Exception {
    // Create server socket on port 6789
    ServerSocket welcomeSocket = new ServerSocket(6789);
    System.out.println("TV server is running...");

    while (true) {
      // Accept client connection
      Socket connectionSocket = welcomeSocket.accept();
      System.out.println("Client connected.");

      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

      // Continuous loop to keep the connection open with the client
      String clientMessage;
      while ((clientMessage = inFromClient.readLine()) != null) {
        System.out.println("Received command: " + clientMessage);
        String response = handleCommand(clientMessage); // Process the command
        outToClient.println(response); // Send the response back to the client
        if (clientMessage.equalsIgnoreCase("EXIT")) {
          System.out.println("Client disconnected.");
          break; // Exit the loop if the client sends "EXIT"
        }
      }

      // Close connection after client disconnects
      connectionSocket.close();
    }
  }

  /**
   * Abstract method that must be implemented by subclasses to handle specific client commands.
   *
   * @param clientMessage The command sent by the client.
   * @return A response message to be sent back to the client.
   */
  public abstract String handleCommand(String clientMessage);
}
