import time 
import threading 
  
class WaitThread(threading.Thread): 
  
    # Thread class with a _stop() method.  
    # The thread itself has to check 
    # regularly for the stopped() condition. 
  
    def __init__(self, *args, **kwargs): 
        super(WaitThread, self).__init__(*args, **kwargs) 
        self._stop = threading.Event() 
  
    # function using _stop function 
    def stop(self): 
        self._stop.set() 
  
    def stopped(self): 
        return self._stop.isSet() 
  
    def run(self): 
        while True: 
            if self.stopped(): 
                return
            print("waiting")
            time.sleep(1) 
  