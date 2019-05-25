import socket
import threading
import time
import sys
import json

import chat
import funnyName

sem = threading.Semaphore(1)
users_sockets = {}


def create_TCP_socket():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    random_name = funnyName.get_name()
    return sock, random_name


def create_and_listen_on_TCP(client_UPD_address):
    def inform_client_from_server(client_UPD_address, TCP_portno):
        """
        This method sends an UDP message to ask one client to establish a TCP connection.
        """
        message = str(json.dumps({"Accept": "OK", "portno": TCP_portno}))
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.sendto(message.encode(), client_UPD_address)
        sock.close()

    TCP_sock, random_user_name = create_TCP_socket()
    # Allocates random free port and accepts request only from specified IP
    TCP_sock.bind(('', 0))
    TCP_sock.listen(1)
        
    inform_client_from_server(client_UPD_address, TCP_sock.getsockname()[1])
    connection_sock, addr = TCP_sock.accept()
    chat.start_chat(connection_sock, random_user_name)


def connect_to_TCP(server_ip, server_port):
    sock, random_user_name = create_TCP_socket()
    sock.connect((server_ip, server_port))
    chat.start_chat(sock, random_user_name)


def listen_to_UDP(sock):
    def check_accept_protocol(message):
        message_dict = json.loads(message)
        if message_dict['Accept']:
            return True, message_dict['portno']
        else:
            return True, None

    while True:
        print("REady to receave from.")
        message, clientAddress = sock.recvfrom(2048)
        message = message.decode()

        sem.acquire()
        if message == "hello":
            create_and_listen_on_TCP(clientAddress)
        elif check_accept_protocol(message)[0]:
            connect_to_TCP(clientAddress[0], check_accept_protocol(message)[1])
        sem.release()


def send_UDP_broadcast(sock):
    serverName = '255.255.255.255'
    SEND_HELLO_INTERVAL = 1

    if sys.argv[1] == '1':
        serverPort = 12001
    else:
        serverPort = 12000

    while True:
        sem.acquire()
        sem.release()
        
        message = "hello".encode()
        try:
            sock.sendto(message, (serverName, serverPort))
            print("Sent hello message!")
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
