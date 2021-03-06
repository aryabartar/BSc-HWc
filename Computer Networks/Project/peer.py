import socket
import threading
import time
import sys
import json
import random
import signal


import chat
import funnyName

from sys import stdout

write = stdout.write
in_TCP_chat = False
in_connection = False
all_sockets = []
main_socket = None
RANDOM_ID = random.randint(0, 100000)


def close_all_sockets_except_main_and_this(this_socket):
    global all_sockets
    for sock in all_sockets:
        if sock == this_socket:
            continue
        sock.close()
    all_sockets = [this_socket]


def add_socket_to_all_sockets(sock):
    global all_sockets
    all_sockets.append(sock)


def print_waiting(message="Waiting"):
    def print_message():
        write(message + " ")
        stdout.flush()

    global in_TCP_chat

    print_counter = 0
    right_print_direction = True

    print_message()

    privious_in_TCP_chat = in_TCP_chat

    while True:
        time.sleep(0.5)

        if (privious_in_TCP_chat != in_TCP_chat) and (not in_TCP_chat):
            print_counter = 0
            right_print_direction = True
            print_message()
            privious_in_TCP_chat = in_TCP_chat
            continue

        privious_in_TCP_chat = in_TCP_chat

        if in_TCP_chat == True:
            continue

        if right_print_direction:
            print_counter += 1
            if print_counter == 3:
                right_print_direction = False
            write(".")
            stdout.flush()

        else:
            print_counter -= 1
            if print_counter == 0:
                right_print_direction = True
            write("\b")
            write(" ")
            write("\b")
            stdout.flush()


def create_TCP_socket():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    add_socket_to_all_sockets(sock)

    return sock


def start_TCP_chat(connection_sock, random_user_name):
    global in_TCP_chat
    in_TCP_chat = True
    chat.start_chat(connection_sock, random_user_name)
    in_TCP_chat = False


def create_and_listen_on_TCP(client_UPD_address):
    def inform_client_from_server(client_UPD_address, TCP_portno):
        """
        This method sends an UDP message to ask one client to establish a TCP connection.
        """
        message = str(json.dumps(
            {"Accept": "OK", "portno": TCP_portno, "id": RANDOM_ID}))
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.sendto(message.encode(), client_UPD_address)
        sock.close()

    global in_TCP_chat
    global in_connection

    TCP_sock = create_TCP_socket()

    # Allocates random free port and accepts request only from specified IP
    TCP_sock.bind(('', 0))
    TCP_sock.listen()

    inform_client_from_server(client_UPD_address, TCP_sock.getsockname()[1])

    if not in_TCP_chat:
        TCP_sock.settimeout(3)
        
        try:
            connection_sock, addr = TCP_sock.accept()
            TCP_sock.settimeout(None)

        except:
            #timeout
            return


        if not in_TCP_chat:
            try:
                random_name = funnyName.get_name()
            except:
                random_name = "Arya"
            start_TCP_chat(connection_sock, random_name)


def connect_to_TCP(server_ip, server_port):
    sock = create_TCP_socket()

    try:
        sock.settimeout(3)
        sock.connect((server_ip, server_port))
        sock.settimeout(None)

        try:
            random_name = funnyName.get_name()
        except:
            random_name = "Arya"
        start_TCP_chat(sock, random_name)

    except:
        # connection timeout happens!
        pass


def listen_to_UDP(sock):
    def check_accept_protocol(message):
        message_dict = json.loads(message)

        if message_dict['id'] == RANDOM_ID:
            return False, None
        elif message_dict['Accept']:
            return True, message_dict['portno']
        else:
            return False, None

    def make_and_start_print_waiting_thread():
        print_waiting_thread = threading.Thread(target=print_waiting,
                                                name="print_waiting", args=("Waiting", ))
        print_waiting_thread.setDaemon(True)
        print_waiting_thread.start()

    make_and_start_print_waiting_thread()

    global in_TCP_chat
    global in_connection

    while True:
        in_connection = False
        if in_TCP_chat:
            time.sleep(0.3)
            continue

        message, clientAddress = sock.recvfrom(2048)
        message = message.decode()

        if message.split('-')[0] == "hello":
            if message.split('-')[1] == str(RANDOM_ID):
                continue
            
            in_connection = True
            create_and_listen_on_TCP(clientAddress)
            time.sleep(random.randint(3,10)/5)


        elif check_accept_protocol(message)[0]:
            in_connection = True
            connect_to_TCP(clientAddress[0], check_accept_protocol(message)[1])
            time.sleep(random.randint(3,10)/5)





def send_UDP_broadcast(sock):
    global RANDOM_ID

    serverName = '255.255.255.255'
    SEND_HELLO_INTERVAL = random.randint(10, 20)/5
    serverPort = 12000

    while True:

        while in_TCP_chat or in_connection:
            time.sleep(0.3)

        message = "hello-{id}".format(id=RANDOM_ID).encode()
        try:
            sock.sendto(message, (serverName, serverPort))
        except socket.timeout:
            print("UDP hello message send timeout.\n\n")

        time.sleep(SEND_HELLO_INTERVAL)

    sock.close()


def create_and_run_threads():
    global main_socket

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    main_socket = sock
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

    global RANDOM_ID

    serverPort = 12000
    sock.bind(('', serverPort))

    rcv_UDP_thread = threading.Thread(
        target=send_UDP_broadcast, name="send_UDP_broadcast", args=(sock, ))
    rcv_UDP_thread.setDaemon(True)
    rcv_UDP_thread.start()

    interval_send_thread = threading.Thread(
        target=listen_to_UDP, name="listen_to_UDP", args=(sock, ))
    interval_send_thread.setDaemon(True)
    interval_send_thread.start()

    interval_send_thread.join()
    rcv_UDP_thread.join()


def sigint_handler(signum, frame):
    global all_sockets
    global main_socket

    print("\n\nIt seems that we are missing you.\nEnjoy your SUNNY day!", '\U0001F604')
    for sock in all_sockets:
        try:
            sock.close()
        except:
            print("Error wile closing some socket connections!")
    main_socket.close()

    sys.exit()


signal.signal(signal.SIGINT, sigint_handler)
create_and_run_threads()
