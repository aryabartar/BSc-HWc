import time
import socket
import threading

from sys import stdout
from socket import SHUT_RDWR

connection_open = True
user_name = None
write = stdout.write


def talk(sock):
    def get_input(sock):
        while True:
            message = input("-> ")

            if message == "LEAVE":
                print("\nYou left the chat.\n")
                sock.shutdown(SHUT_RDWR)
                sock.close()
                break

            message = "\n{username} says: {message}".format(
                username=user_name, message=message)
            sock.sendall(message.encode())

    threading.Thread(
        target=get_input, name="get_input", args=(sock, )).start()

    while connection_open:
        time.sleep(0.2)

    print("Ending talk!")


def listen(sock):

    while True:
        message = sock.recv(2048).decode()
        if not message:
            break

        write("\033[F")
        stdout.flush()

        print(message)

        write("-> ")
        stdout.flush()

    print("Partner left the chat!")
    sock.close()
    connection_open = False


def print_welcome(username):
    print("\nChat started!\nYour random name is: {username} \n".format(
        username=username))


def start_chat(sock, socket_user_name):
    global user_name
    user_name = socket_user_name

    listen_thread = threading.Thread(
        target=listen, name="listen", args=(sock, ))
    talk_thread = threading.Thread(
        target=talk, name="talk", args=(sock, ))

    print_welcome(user_name)

    listen_thread.start()
    talk_thread.start()

    listen_thread.join()

    print("I am here!")
    connection_open = True
    user_name = None
