import time
import socket
import threading

from sys import stdout
from socket import SHUT_RDWR

connection_open = True
user_name = None
left_the_chat = False
write = stdout.write


def remove_last_printed_line():
    write("\033[F")
    stdout.flush()


def talk(sock):
    def get_input(sock):
        global left_the_chat 
        
        while True:
            write("-> ")
            stdout.flush()

            message = input()
            if message == "l":
                left_the_chat = True
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


def listen(sock):
    global left_the_chat 

    while True:
        message = sock.recv(2048).decode()
        if not message:
            write("\b")
            stdout.flush()
            break

        remove_last_printed_line()

        print(message)

        write("-> ")
        stdout.flush()

    if not left_the_chat :
        print("\nYour partner left the chat :( \n")

    sock.close()
    connection_open = False


def print_welcome(username):
    print("\nChat started!\nYour random name is: {username} \n".format(
        username=username))

def set_globals_to_default():
    global connection_open
    global user_name
    global left_the_chat

    connection_open = True
    user_name = None
    left_the_chat = False

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

    set_globals_to_default()

