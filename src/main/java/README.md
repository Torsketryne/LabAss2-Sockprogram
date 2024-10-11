# Smart TV Remote Control System

This project simulates a smart TV that can be controlled remotely by clients using commands like turning on/off the TV, changing channels, and getting the current channel.

## Files

- **`TvActiveServer.java`**: Abstract class that handles the server side of the communication.
- **`SmartTv.java`**: Implements the TV server, handling commands like ON, OFF, CHANNEL, and GETCHANNEL.
- **`RemoteClient.java`**: Client that sends commands to the TV server and receives responses.

## Which Classes to Run

1. **Run `SmartTv`**:
   This class runs the server which waits for commands from the clients.
   - Run this in one terminal window.

2. **Run `RemoteClient`**:
   This class acts as the client. It connects to the `SmartTv` server and allows you to send commands.
   - You can open multiple terminals and run this class as separate clients.

## Example Commands

- **ON**: Turns the TV on.
- **OFF**: Turns the TV off.
- **CHANNEL <number>**: Sets the TV to a specific channel (1-10).
- **GETCHANNEL**: Gets the current channel.
- **EXIT**: Disconnects the client.

## Example Interaction

```text
Connected to TV server...
Enter command: ON
FROM SERVER: TV is on.
Enter command: CHANNEL 3
FROM SERVER: Channel set to 3
Enter command: GETCHANNEL
FROM SERVER: Current channel: 3
```
```

You can place this `README.md` in the project’s root directory (outside the `src` folder).