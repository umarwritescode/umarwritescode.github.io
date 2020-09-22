import socket
import sys
from fileshare import *

# Create the socket on which the server will receive new connections
srv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
	srv_sock.bind(("0.0.0.0", int(sys.argv[1]))) # sys.argv[1] is the 1st argument on the command line
	srv_sock.listen(5)
except Exception as e:
	# Print the exception message
	print(e)
	# Exit with a non-zero value, to indicate an error condition
	exit(1)

# Loop forever (or at least for as long as no fatal errors occur)
while True:
	try:
		print("Waiting for new client... ")
		
		cli_sock, cli_addr = srv_sock.accept()
		cli_addr_str = str(cli_addr)

		print("Client " + cli_addr_str + " connected.")
		
		
		request_raw = socket_to_screen(cli_sock, cli_addr_str)
		print("SERVER HAS RECIEVED COMMAND")
		print(request_raw)
		
		request = str(request_raw).split(' ')
		if request[0] == "put":
			print("PUT COMMAND IDENTIFIED")
			filename = request[1]
			recv_file(cli_sock, filename)
		elif request[0] == "get":
			filename = request[1]
			send_file(cli_sock, filename)
		elif request[0] == "list":
			send_list(cli_sock)
		else:
			pass
			

	finally:
		cli_sock.close()

# Close the server socket as well to release its resources back to the OS
srv_sock.close()

# Exit with a zero value, to indicate success
exit(0)
