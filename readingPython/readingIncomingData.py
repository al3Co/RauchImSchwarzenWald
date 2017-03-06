
import socket

def readingData():
    try:
        s = socket.socket()
        host = ""
        port = 50009
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host,port))
        s.listen(5)
        while True:
            print 'Looking for data ... '
            c, addr = s.accept()
            print('Connection accepted from ' + repr(addr[1]))
            dataIncoming = c.recv(1026)
            #print (get_type(dataIncoming))
            stringdata = str(dataIncoming)
            reviewDataIncoming(c,stringdata)
            #print repr(addr[1]) + ": " + c.recv(1026)
            c.close()
    except (RuntimeError, TypeError, NameError, SyntaxError, socket.gaierror) as e:
        c.close()
        s.close()
        print 'Oops!  Something wrong at the connection' , e

def reviewDataIncoming(c, stringdata):
    try:
        if (stringdata != ""):
            if ("pick(garb)" in stringdata):
                print 'mario1'
                print 'You are in!!! ' + stringdata
                pickGrabFunction()
                c.sendall("hola090")
                c.send("Server approved connection\n")
            else:
                print 'mario2' + ': ' + stringdata
                #print 'crear accion para datos incompletos en el ambiente Java'
        else:
            print 'Message incoming void'
    except (ValueError, SyntaxError) as e:
        print 'Oops!  Something wrong on data Incoming' , e


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
