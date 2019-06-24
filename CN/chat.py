import time
import socket
import threading

from colors import bcolors
from sys import stdout
from socket import SHUT_RDWR

connection_open = True
user_name = None
left_the_chat = False
write = stdout.write
sock = None


def remove_last_printed_line():
    write("\033[F")
    stdout.flush()


def print_dash():
    print(bcolors.OKGREEN + "-----------------------------" + bcolors.ENDC)


def talk():
    def get_input():
        global left_the_chat

        while True:
            write(bcolors.BOLD + "-> " + bcolors.ENDC)
            stdout.flush()
            message = input()

            if message == "l":
                left_the_chat = True
                print("\nYou left the chat.", "\U0001F606")
                print_dash()
                print("\n")
                sock.shutdown(SHUT_RDWR)
                sock.close()
                break

            message = "\n{username} says: {message}".format(
                username=user_name, message=message)

            sock.sendall(message.encode())

    get_input_thread = threading.Thread(
        target=get_input, name="get_input", args=())
    get_input_thread.start()

    while connection_open:
        time.sleep(0.2)


def listen():
    global left_the_chat
    while True:
        try:
            message = sock.recv(2048).decode()
            if not message:
                write("\b")
                stdout.flush()
                break
        except:
            # Other side cleses chat suddenly
            break

        remove_last_printed_line()

        print(message)

        write(bcolors.BOLD + "-> " + bcolors.ENDC)
        stdout.flush()

    if not left_the_chat:
        remove_last_printed_line()
        print("\n                    ")
        print("\nYour partner left the chat.", "\U0001F606")
        print_dash()
        print("\n")

    sock.close()
    connection_open = False


def print_welcome(username):
    remove_last_printed_line()
    print_dash()
    print("Chat started!\n", "\bYour random name is:" + bcolors.BOLD + " {username} \n".format(
        username=username) + bcolors.ENDC)


def set_globals_to_default():
    global connection_open
    global user_name
    global left_the_chat
    global sock

    connection_open = True
    user_name = None
    left_the_chat = False
    sock = None


def start_chat(TCP_socket, socket_user_name):
    global user_name
    global sock

    sock = TCP_socket
    user_name = socket_user_name

    listen_thread = threading.Thread(
        target=listen, name="listen", args=())
    talk_thread = threading.Thread(
        target=talk, name="talk", args=())

    print_welcome(user_name)

    listen_thread.start()
    talk_thread.start()

    listen_thread.setDaemon = True
    talk_thread.setDaemon = True

    listen_thread.join()

    set_globals_to_default()
