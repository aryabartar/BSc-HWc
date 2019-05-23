import socket
import threading


def start_chat(sock):
    while True:
        message= input("::")
        sock.send(message.encode())


