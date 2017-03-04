
import socket

def readingData():
    try:
        s = socket.socket()
        host = socket.gethostname()
        port = 50009
        s.bind((host,port))
        s.listen(5)
        print 'Connecting to: ' + host + ' from: ' + str(port)
        while True:
            print 'Looking for data ... '
            c, addr = s.accept()
            print('Connection accepted from ' + repr(addr[1]))
            dataIncoming = c.recv(1026)
            print (get_type(dataIncoming)) #Print data type
            stringdata = str(dataIncoming)
            if (stringdata != ""):
                print stringdata
                if (stringdata == "pick(grab)"):
                    print 'You are in!!! ' + stringdata
                    pickGrabFunction()
                else:
                    print 'Somting else' + stringdata
            else:
                print 'Message incoming failed' + stringdata
            c.send('Server approved connection\n')
            print repr(addr[1]) + ": " + c.recv(1026)
            c.close()
    except (RuntimeError, TypeError, NameError, SyntaxError, socket.gaierror) as e:
        print 'Oops!  Something wrong' , e


def pickGrabFunction():
    print 'Here should be the code to move the Drone'

def get_type(input_data):
    try:
        return type(input_data)
    except (ValueError, SyntaxError):
        # A string, so return str
        return str


if __name__ == "__main__":
    readingData()
