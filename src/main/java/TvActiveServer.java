import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public abstract class TvActiveServer {

  public void runServer() throws Exception {
    // Create server socket on port 6789
    ServerSocket welcomeSocket = new ServerSocket(6789);
    System.out.println("TV server is running...");

   while (true) {
       Socket connectionSocket = null;
       try {
           // Accept client connection
           connectionSocket = welcomeSocket.accept();
           System.out.println("Client connected.");
       } catch (IOException e) {
           System.err.println("Error accepting client connection: " + e.getMessage());
           e.printStackTrace();
       }

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

  public abstract String handleCommand(String clientMessage);
}
