import socket
import threading
import time

def listen_to_UDP(): 
    # serverPort = 12000
    serverPort = 12002
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    serverSocket.bind(('', serverPort))
    print ("Server mode, Listening.")
    
    while 1:
        print ("Peer is in while, listening")
        message, clientAddress = serverSocket.recvfrom(2048)
        modifiedMessage = message.upper()
        serverSocket.sendto(modifiedMessage, clientAddress)

def send_UDP_broadcast():
    serverName = '255.255.255.255'
    serverPort = 12000

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

    while True: 
        time.sleep(3)
        message = "hello".encode()
        sock.sendto(message,(serverName, 12000))
        # sock.sendto(message,(serverName, 12001))
        modifiedMessage, serverAddress = sock.recvfrom(2048)
        print (modifiedMessage)
    
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