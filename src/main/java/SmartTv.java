public class SmartTv extends TvActiveServer {

    private boolean isOn;
    private int currentChannel;
    private int totalChannels;

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
        if (!isOn){
            return "TV is off.";
        }else{
            return "Current channel: " + currentChannel;
        }
    }

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
}
