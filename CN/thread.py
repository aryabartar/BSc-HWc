import time
import threading

from sys import stdout

write = stdout.write


class WaitThread(threading.Thread):

    # Thread class with a _stop() method.
    # The thread itself has to check
    # regularly for the stopped() condition.

    def __init__(self, message="Waiting", in_TCP_chat=False,  *args, **kwargs):
        super(WaitThread, self).__init__(*args, **kwargs)
        self._stop = threading.Event()
        self.print_counter = 0
        self.right_print_direction = True
        self.message = message
        self.in_TCP_chat = in_TCP_chat

    def stop(self):
        write("\033[F")
        stdout.flush()

        self._stop.set()

    def stopped(self):
        return self._stop.isSet()

    def run(self):
        write(self.message + " ")
        stdout.flush()
        privious_in_TCP_chat = self.in_TCP_chat

        while True:
            time.sleep(0.5)
            
            if self.stopped():
                return

            if not privious_in_TCP_chat == self.in_TCP_chat:
                write(self.message + " ")
                privious_in_TCP_chat = self.in_TCP_chat
                continue
            
            if self.in_TCP_chat == True:
                continue

            print("privious_in_TCP_chat: " , privious_in_TCP_chat , "self.in_TCP_chat" , self.in_TCP_chat)
            if self.right_print_direction:
                self.print_counter += 1
                if self.print_counter == 3:
                    self.right_print_direction = False
                write(".")
                stdout.flush()

            else:
                self.print_counter -= 1
                if self.print_counter == 0:
                    self.right_print_direction = True
                write("\b")
                write(" ")
                write("\b")
                stdout.flush()

            privious_in_TCP_chat = self.in_TCP_chat
