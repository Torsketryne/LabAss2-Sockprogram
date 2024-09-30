public class SmartTv {

    private boolean isOn;
    private int currentChannel;
    private int totalChannels;

    public static void main(String[] args) {
        SmartTv tv = new SmartTv();
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
}
