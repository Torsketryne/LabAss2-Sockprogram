import java.io.*;
import java.net.*;

public class TvActiveServer {

  public static void main(String argv[]) throws Exception {
    String client_message;
    // String capitalizedSentence;

    ServerSocket welcomeSocket = new ServerSocket(6789);
    while (true) {

      Socket connectionSocket = welcomeSocket.accept();
      BufferedReader inFromClient =
          new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);
      client_message = inFromClient.readLine();

//            capitalizedSentence = client_message.toUpperCase();
//            outToClient.println(capitalizedSentence);
    }
  }
}