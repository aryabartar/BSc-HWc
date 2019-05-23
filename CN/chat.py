import socket
import threading
from sys import stdout

user_name = None
write = stdout.write


def talk(sock):
    while True:
        message = input("-> ")
        message = "\n{username} says: {message}".format(
            username=user_name, message=message)
        sock.sendall(message.encode())


def listen(sock):
    while True:
        message = sock.recv(2048).decode()
        write("\033[F")
        print(message)
        print("-> ", end="")


def start_chat(sock, socket_user_name):
    user_name = socket_user_name

    listen_thread = threading.Thread(
        target=listen, name="listen", args=(sock, ))
    talk_thread = threading.Thread(
        target=talk, name="talk", args=(sock, ))
    listen_thread.daemon = False
    talk_thread.daemon = False
    listen_thread.start()
    talk_thread.start()
