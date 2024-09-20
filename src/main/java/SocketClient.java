import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private final String hostname;
    private final int port;

    public SocketClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1238;
        SocketClient client = new SocketClient(hostname, port);
        client.start();
    }

    public void start() {
        try (Socket socket = new Socket(hostname, port);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Smart TV. Enter commands:");
            String command;
            while (true) {
                System.out.print("> ");
                command = scanner.nextLine();
                out.println(command); // Send command to the server
                String response = in.readLine(); // Receive server's response
                System.out.println(response);

                // Exit if the command is "turn off" and TV is already off
                if (command.equalsIgnoreCase("turn off") && response.equals("TV turned off.")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

