/**
 * The {@code SmartTv} class represents a TV server that extends the {@code TvActiveServer} class.
 * It simulates a TV that can be turned on or off, have its channels changed, and respond to client commands.
 * <p>
 * The server listens for client commands and processes them to control the TV.
 * </p>
 * <p>
 * Supported commands include:
 * <ul>
 *     <li><b>ON</b>: Turns on the TV.</li>
 *     <li><b>OFF</b>: Turns off the TV.</li>
 *     <li><b>CHANNEL &lt;number&gt;</b>: Sets the TV channel to the specified number.</li>
 *     <li><b>GETCHANNEL</b>: Retrieves the current channel of the TV.</li>
 * </ul>
 * </p>
 *
 * <p><b>Usage:</b></p>
 * <pre>{@code
 * SmartTv smartTv = new SmartTv();
 * smartTv.runServer(); // Start the server and listen for client commands
 * }</pre>
 *
 * @author Daniel, Nikolai, Bakri, Vineet
 * @version 1.0
 * @since 2024-10-08
 */

public class SmartTv extends TvActiveServer {

    private boolean isOn;
    private int currentChannel;
    private int totalChannels;

    /**
     * Constructs a new {@code SmartTv} with default settings:
     * <ul>
     *     <li>TV is off initially.</li>
     *     <li>Current channel is set to 1.</li>
     *     <li>Total number of available channels is set to 10.</li>
     * </ul>
     */
    public SmartTv() {
        this.isOn = false;
        this.currentChannel = 1;
        this.totalChannels = 10;
    }

    /**
     * Turns the TV on.
     */
    private void turnOn() {
        isOn = true;
    }

    /**
     * Turns the TV off.
     */
    private void turnOff() {
        isOn = false;
    }

    /**
     * Retrieves the current channel of the TV.
     *
     * @return A string message indicating the current channel or that the TV is off.
     */
    private String getChannel() {
        if (!isOn){
            return "TV is off.";
        }else{
            return "Current channel: " + currentChannel;
        }
    }

    /**
     * Sets the TV to a specific channel.
     *
     * @param channel The channel number to set.
     * @return A string message indicating the result of the channel change, or an error if the TV is off or the channel is invalid.
     */
    private String setChannel(int channel) {
        if (!isOn) {
            return "TV is off.";
        } else if (channel > 0 && channel <= totalChannels) {
            currentChannel = channel;
            return "Channel set to " + channel;
        } else {
            return  "Invalid channel number.";
        }
    }

    /**
     * Handles a client command and performs the appropriate action (e.g., turn on/off the TV, change channels).
     *
     * @param clientMessage The command sent by the client.
     * @return A response message indicating the result of the command.
     */
    @Override
    public String handleCommand(String clientMessage) {
        String response;
        String[] parts = clientMessage.split(" ");
        String command = parts[0].toUpperCase();
        switch (command) {
            case "ON":
                turnOn();
                response = "TV is on.";
                break;
            case "OFF":
                turnOff();
                response = "TV is off.";
                break;
            case "CHANNEL":
                if (parts.length > 1) {
                    int channel = Integer.parseInt(parts[1]);
                    response = setChannel(channel);
                } else {
                    response = "Invalid command.";
                }
                break;
            case "GETCHANNEL":
                response = getChannel();
                break;
            default:
                response = "Command not supported.";
        }
        return response;
    }

    /**
     * Main method to start the TV server.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SmartTv smartTv = new SmartTv();
        try{
            smartTv.runServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
