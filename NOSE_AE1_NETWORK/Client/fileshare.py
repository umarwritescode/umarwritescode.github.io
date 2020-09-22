
import sys
import os
import socket

def socket_to_screen(socket, sock_addr):
	data = bytearray(1)
	data = socket.recv(4096)
	print("MESSAGE: "+data.decode()) # Use end="" to avoid adding a newline per print() call
	print('CMD RECV' + str(data.decode()))
	return str(data.decode())

def keyboard_to_socket(socket):
	print("You: ", end="", flush=True) # Use end="" to avoid adding a newline after the prompt, flush=True to force-print the prompt

	user_input = sys.stdin.readline()
	if user_input == "EXIT\n": # The user requested that the communication is terminated.
		return 0

	bytes_sent = socket.sendall(str.encode(user_input))
	return bytes_sent
	
def send_file(socket, filename):
	data_to_send = b''
	with open(filename,'rb') as f:
		new_data = f.read(4096)
		while new_data != b'':
			data_to_send += new_data
			new_data = f.read(4096)
		socket.sendall(data_to_send)
		
	return data_to_send
	
def recv_file(socket,filename):
	full_data = b''
	data = socket.recv(4096)
	while len(data) > 0:
		full_data += data
		data = socket.recv(4096)
	full_data += data
	with open(filename,'wb') as f:
		f.write(full_data)

def recv_list(socket):
	files = []
	folders = []
	data = bytearray(1)
	data = socket.recv(4096)
	raw_list = str(data.decode())
	items = raw_list.strip(']').strip('[').split(',')
	
	list_string = "Files in Directory: "
	for item in items:
		item_string = item.strip(' ').strip('\'')
		list_string += (item_string + ", ")
	list_string = list_string.strip(' ').strip(',')
	return list_string
	
def send_list(socket):
	list = str(os.listdir())
	socket.sendall(str.encode(list))
	return list
