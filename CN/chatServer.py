import socket
import threading

def start_chat(sock):
    while True:
        message = sock.recv(1024)
        print(message)