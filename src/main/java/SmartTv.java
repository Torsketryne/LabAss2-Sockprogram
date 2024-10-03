public class SmartTv extends TvActiveServer {

    private boolean isOn;
    private int currentChannel;
    private int totalChannels;

    public static void main(String[] args) throws Exception {
        SmartTv tv = new SmartTv();
        tv.runServer();
    }

    public SmartTv() {
        this.isOn = false;
        this.currentChannel = 1;
        this.totalChannels = 10;
    }

    private void turnOn() {
        isOn = true;
    }

    private void turnOff() {
        isOn = false;
    }

    private String getChannel() {
        String Answer;
        if (!isOn) {
            Answer = "TV is off.";
        } else {
            Answer = "Current channel: " + currentChannel;
        }
        return Answer;
    }

    private String getChannels() {
        String Answer;
        if (!isOn) {
            Answer = "TV is off.";
        } else {
            Answer = "Total channels: " + totalChannels;
        }
        return Answer;
    }

    private String setChannel(int channel) {
        String Answer;
        if (!isOn) {
            Answer = "TV is off.";
        } else if (channel > 0 && channel <= totalChannels) {
            currentChannel = channel;
            Answer = "Channel set to " + channel;
        } else {
            Answer = "Invalid channel number.";
        }
        return Answer;
    }

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
            case "GETCHANNELS":
                response = getChannels();
                break;
            default:
                response = "Command not supported.";
        }
        return response;
    }
}
