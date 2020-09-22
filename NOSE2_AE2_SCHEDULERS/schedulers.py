##U

from des import SchedulerDES
from event import Event, EventTypes
from process import Process, ProcessStates
import math
from os import sys
from event import *

class FCFS(SchedulerDES):

	def scheduler_func(self, cur_event):
		return self.processes[cur_event.process_id] # Return process

	def dispatcher_func(self, cur_process):
		event_time = cur_process.run_for(cur_process.remaining_time, self.time) + self.time # Run process for its service time and assign it to a new var
		cur_process.process_state = ProcessStates.TERMINATED # Assign process state to termination
		event_type = EventTypes(3) # Assign event completion to new var
		return Event(process_id = cur_process.process_id, event_type = event_type, event_time = event_time) # Return new instance of Event class


class SJF(SchedulerDES):
	def scheduler_func(self, cur_event):
		service_times_list = []
		processes = self.processes
		for process in processes:
			if process.process_state == ProcessStates.READY:
				service_times_list += [process.service_time] # Create list of service times of ready processes
		service_time_min = min(service_times_list) # Store minimum service time in a new var

		for process in processes:
			if process.service_time == service_time_min: # Return the process that matches with the min service time
				return self.processes[process.process_id]

	def dispatcher_func(self, cur_process):
		event_time = cur_process.run_for(cur_process.remaining_time, self.time) + self.time
		cur_process.process_state = ProcessStates.TERMINATED
		event_type = EventTypes(3)
		return Event(process_id = cur_process.process_id, event_type = event_type, event_time = event_time)


class RR(SchedulerDES):
	def scheduler_func(self, cur_event):
		return self.processes[cur_event.process_id] # Return process

	def dispatcher_func(self, cur_process):
		event_time = cur_process.run_for(self.quantum, self.time) + self.time
		time_left = cur_process.remaining_time
		if time_left <= 0:
			cur_process.process_state = ProcessStates.TERMINATED # Terminate process if it completes within the time frame of time slice
			event_type = EventTypes(3)
			return Event(process_id = cur_process.process_id, event_type = event_type, event_time = event_time)
		else:
			cur_process.process_state= ProcessStates.READY # Else put process in ready queue
			event_type = EventTypes(2)
			return Event(process_id = cur_process.process_id, event_type = event_type, event_time = event_time)


class SRTF(SchedulerDES):
	def scheduler_func(self, cur_event):
		remaining_time_list = []
		for process in self.processes:
			if process.process_state == ProcessStates.READY:
				 remaining_time_list += [process.remaining_time] # Create list of remaining time of ready processes


		remaining_time_list_min = min(remaining_time_list) # Store minimum remaining time
		for process in self.processes:
			if process.remaining_time == remaining_time_list_min:
				return process # Return process that matches with the stored minimum remaining time
				
	def dispatcher_func(self, cur_process):
		cur_process.process_state = ProcessStates.RUNNING

		runTime = cur_process.run_for(self.next_event_time() - self.time , self.time) # Run for length of time where after the run, a process in the queue is updated
		if cur_process.remaining_time == 0:
			cur_process.process_state = ProcessStates.TERMINATED # Terminate if remaining time is 0
			return Event(process_id = cur_process.process_id, event_type = EventTypes.PROC_CPU_DONE, event_time = runTime+self.time)
		else:
			cur_process.process_state  = ProcessStates.READY # Else put process in ready queue
			return Event(process_id = cur_process.process_id, event_type = EventTypes.PROC_CPU_REQ, event_time = runTime+self.time)
