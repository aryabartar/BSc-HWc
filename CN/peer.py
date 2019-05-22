import socket
import threading
import time
import sys

sem = threading.Semaphore()


def create_and_listen_on_TCP(client_UPD_address):
    def inform_client_from_server(client_UPD_address):
        """
        This method sends an UDP message to ask one client to establish a TCP connection.
        """
        time.sleep(1)
        message = "Accept"
        print("Sendong accept!")
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.sendto(message.encode(), client_UPD_address)
        print("client address was:", client_UPD_address)
        print("Accept sent!")

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Allocates random free port and accepts request only from specified IP
    sock.bind(('', 0))
    sock.listen(1)
    print("BB")
    while True:
        inform_client_from_server(client_UPD_address)
        print("CC")
        connection_sock, addr = sock.accept()
        print("Connection established with {}".format(addr))


def connect_to_TCP(server_address):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print("EE")
    sock.connect(server_address)
    print("Connection with server established!")


def listen_to_UDP(sock):

    while True:
        print("Running listen_to_UDP while")
        message, clientAddress = sock.recvfrom(2048)
        message = message.decode()

        print(message)
        if message == "hello":
            sem.acquire()
            print("AA")
            create_and_listen_on_TCP(clientAddress)
        elif message == "Accept":
            print("DD")
            connect_to_TCP(clientAddress)
            sem.release()


def send_UDP_broadcast(sock):
    serverName = '255.255.255.255'
    SEND_HELLO_INTERVAL = 2

    if sys.argv[1] == '1':
        serverPort = 12001
    else:
        serverPort = 12000

    while True:
        sem.acquire()
        sem.release()

        print("Running send_UDP_broadcast while")
        print(time.time())

        message = "hello".encode()
        try:
            sock.sendto(message, (serverName, serverPort))

        except socket.timeout:
            print("UDP hello message send timeout.\n\n")

        time.sleep(SEND_HELLO_INTERVAL)

    sock.close()


def create_and_run_threads():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

    if sys.argv[1] == '1':
        serverPort = 12000
    else:
        serverPort = 12001

    sock.bind(('', serverPort))

    rcv_UDP_thread = threading.Thread(
        target=send_UDP_broadcast, name="send_UDP_broadcast", args=(sock, ))
    rcv_UDP_thread.daemon = False
    rcv_UDP_thread.start()

    interval_send_thread = threading.Thread(
        target=listen_to_UDP, name="listen_to_UDP", args=(sock, ))
    interval_send_thread.daemon = False
    interval_send_thread.start()


create_and_run_threads()
