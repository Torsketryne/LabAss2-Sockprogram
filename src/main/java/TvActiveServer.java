
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public abstract class TvActiveServer {
  //private static boolean isTvOn = false;
  //private static int currentChannel = 0; // default channel

  public void runServer() throws Exception {
    // Create server socket on port 6789
    ServerSocket welcomeSocket = new ServerSocket(6789);
    System.out.println("TV server is running...");

    while (true) {
      // Accept client connection
      Socket connectionSocket = welcomeSocket.accept();
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

      // Read client message
      String clientMessage = inFromClient.readLine();
      if (clientMessage != null) {
        System.out.println("Received command: " + clientMessage);
        String response = handleCommand(clientMessage); // Process the command
        outToClient.println(response); // Send the response back to the client
      }

      // Close connection
      connectionSocket.close();
    }
  }

  //Should be fixed after setting up smartTv class
  public abstract String handleCommand(String clientMessage);


}