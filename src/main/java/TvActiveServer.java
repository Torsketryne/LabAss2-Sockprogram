
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class TvActiveServer {
  private static boolean isTvOn = false;
  private static int currentChannel = 0; // default channel

  public static void main(String argv[]) throws Exception {
    String clientMessage;

    // Create server socket on port 6789
    ServerSocket welcomeSocket = new ServerSocket(6789);
    System.out.println("TV server is running...");

    while (true) {
      // Accept client connection
      Socket connectionSocket = welcomeSocket.accept();
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

      // Read client message
      clientMessage = inFromClient.readLine();
      if (clientMessage != null) {
        System.out.println("Received command: " + clientMessage);
        String response = handleCommand(clientMessage); // Process the command
        outToClient.println(response); // Send the response back to the client
      }

      // Close connection
      connectionSocket.close();
    }
  }

  private static String handleCommand(String clientMessage) {
    switch (command.toUpperCase()) {
      case "ON":
        if (!isTvOn) {
          isTvOn = ture;
          return "TV is turned ON";
        } else {
          return "TV is already ON";
        }
      case "OFF":
        if (!isTvOn) {
          isTvOn = false;
          return "TV is turned OFF";
        } else {
          return "TV is already OFF";
        }
      default:
        if (Command.startWith("Channel")){
          return changeChannel(command);
        }else{
          return "Invalid command";
        }
    }
  }
}