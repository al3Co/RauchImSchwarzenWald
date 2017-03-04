
import socket

def readingData():
    try:
        s = socket.socket()
        host = ""
        port = 50009
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host,port))
        s.listen(5)
        print 'Connecting to: ' + host + ' from: ' + str(port)
        while True:
            print 'Looking for data ... '
            c, addr = s.accept()
            print('Connection accepted from ' + repr(addr[1]))
            dataIncoming = c.recv(1026)
            print (get_type(dataIncoming))
            stringdata = str(dataIncoming)
            if (stringdata != ""):  # Verify if there is data on it
                if "pick(garb)" in stringdata:
                    print 'mario1'
                    print 'You are in!!! ' + stringdata
                    pickGrabFunction()
                    c.sendall("hola090")
                else:
                    print 'mario2'
            else:
                print 'Message incoming void'
            c.send('Server approved connection\n')
            print repr(addr[1]) + ": " + c.recv(1026)
            c.close()
    except (RuntimeError, TypeError, NameError, SyntaxError, socket.gaierror) as e:
        c.close()
        s.close()
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
