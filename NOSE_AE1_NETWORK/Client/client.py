import socket
import sys
from fileshare import *

# Create the socket with which we will connect to the server
cli_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# The server's address is a tuple, comprising the server's IP address or hostname, and port number
srv_addr = (sys.argv[1], int(sys.argv[2])) # sys.argv[x] is the x'th argument on the command line

# Convert to string, to be used shortly
srv_addr_str = str(srv_addr)

 
except:
    if len(sys.argv) != 5 or len(sys.argv) != 6: 
        print("Inappropriate number of arguments were passed")
    
try:
  
except:
    if type(sys.argv[4]) is str == false:
        print("You must pass put/get/list in place of your 5th argument")
      
try:
    portNumber = int(sys.argv[3])
except:
    print("You must provide a port number in place of the 3rd argument")
        
        

try:
	print("Connecting to " + srv_addr_str + "... ")
	cli_sock.connect(srv_addr)
	print("Connected. Now chatting...")
except Exception as e:
	# Print the exception message
	print("Client did not connect successfully")
	# Exit with a non-zero value, to indicate an error condition
	exit(1)

try:
	command = sys.argv[3:]
	
	print("CLIENT READS COMMAND AS")
	print(command)
	
	if command[0] == "put":
		cli_sock.sendall(str.encode(str(command[0])+" "+command[1]))
		send_file(cli_sock,str(command[1]))
	elif command[0] == "get":
		cli_sock.sendall(str.encode(str(command[0])+" "+command[1]))
		response = recv_file(cli_sock, filename)
	elif command[0] == "list":
		cli_sock.sendall(str.encode(str(command[0])))
		response = recv_list(cli_sock)
		print(response)
finally:
	cli_sock.close()

# Exit with a zero value, to indicate success
exit(0)
