import socket

serverName = '255.255.255.255'
serverPort = 12000

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

message = "hello".encode()
sock.sendto(message,(serverName, serverPort))
sock.sendto(message,(serverName, 12001))
modifiedMessage, serverAddress = sock.recvfrom(2048)
print (modifiedMessage)
sock.close()