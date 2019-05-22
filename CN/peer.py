import socket
import threading
import time
import sys

def listen_to_UDP(): 
    
    if sys.argv[1] == '1' :
        serverPort = 12000
    else:
        serverPort = 12001

    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    serverSocket.bind(('', serverPort))
    
    while True:
        message, clientAddress = serverSocket.recvfrom(2048)
        modifiedMessage = message.upper()
        serverSocket.sendto(modifiedMessage, clientAddress)

def send_UDP_broadcast():
    serverName = '255.255.255.255'
    SEND_HELLO_INTERVAL = 4

    if sys.argv[1] == '1':
        serverPort = 12001
    else:
        serverPort = 12000

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
    sock.settimeout(0.4)

    while True: 
        time.sleep(SEND_HELLO_INTERVAL)
        print (time.time())
        message = "hello".encode()
        try:
            sock.sendto(message,(serverName, serverPort))
            modifiedMessage, serverAddress = sock.recvfrom(2048)
            print (modifiedMessage.decode(), "\n\n")
        except socket.timeout:
            print ("UDP hello message send timeout.\n\n")
    
    sock.close()

def create_and_run_threads():
    interval_send_thread = threading.Thread(
        target=listen_to_UDP, name="listen_to_UDP", args=())
    rcv_UDP_thread = threading.Thread(
        target=send_UDP_broadcast, name="send_UDP_broadcast", args=())

    interval_send_thread.daemon = False
    rcv_UDP_thread.daemon = False

    interval_send_thread.start()
    rcv_UDP_thread.start()


create_and_run_threads()
# time.sleep(1000000)