
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
            stringdata = dataIncoming.decode('utf-8')
            if (stringdata != ""):
                print stringdata
                if (stringdata == "pick(grab)"):
                    print 'You are in!!! ' + stringdata
                    pickGrabFunction()
                else:
                    print 'Somting else'
            else:
                print 'Message incoming failed' + stringdata
            c.send('Server approved connection\n')
            print repr(addr[1]) + ": " + c.recv(1026)
            c.close()
    except (RuntimeError, TypeError, NameError, SyntaxError) as e:
        print 'Oops!  Something wrong' , e


def pickGrabFunction():
    print 'Here should be the code to move the Drone'


if __name__ == "__main__":
    readingData()
