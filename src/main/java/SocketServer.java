import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private boolean isOn = false;
    private int currentChannel = 1;
    private final int totalChannels = 10; // Example total channels

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 1238;
        new SocketServer().startServer(port);
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Smart TV Server started on port: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String command;
            while ((command = in.readLine()) != null) {
                String response = processCommand(command);
                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }

    private String processCommand(String command) {
        switch (command.toLowerCase()) {
            case "turn on":
                isOn = true;
                return "TV turned on.";
            case "turn off":
                isOn = false;
                return "TV turned off.";
            case "get channel":
                return isOn ? "Current channel: " + currentChannel : "TV is off.";
            case "get channels":
                return isOn ? "Total channels: " + totalChannels : "TV is off.";
            default:
                if (command.startsWith("set channel ")) {
                    if (!isOn) return "TV is off.";
                    try {
                        int channel = Integer.parseInt(command.split(" ")[2]);
                        if (channel > 0 && channel <= totalChannels) {
                            currentChannel = channel;
                            return "Channel set to " + channel;
                        } else {
                            return "Invalid channel number.";
                        }
                    } catch (NumberFormatException e) {
                        return "Invalid command format.";
                    }
                }
                return "Unknown command.";
        }
    }
}
