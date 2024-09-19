import java.io.*;
import java.net.*;
from socket import *

public class TvActiveServer {

    welcomeSocket = socket(AF_INET, SOCK_STREAM)
    welcomeSocket.bind(('', 6789))
    welcomeSocket.listen(1)
    connectionSocket, addr = welcomeSocket.accept()
    client_message = connectionSocket.recv(1000).decode()
    print("CLIENT: ", client_message)

    connectionSocket.close()
    welcomeSocket.close()

}
