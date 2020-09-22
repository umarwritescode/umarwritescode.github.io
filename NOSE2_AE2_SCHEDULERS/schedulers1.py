from des import SchedulerDES
from event import EventTypes
from process import Process, ProcessStates
import math


class FCFS(SchedulerDES):

    def scheduler_func(self, cur_event):
        self.current_event = cur_event
        return self.processes[self.current_event.process_id]

    def dispatcher_func(self, cur_process):
        self.current = cur_process
        self.current_event._event_time = cur_process.run_for(self.current.remaining_time, self.time) + self.time
        self.current._process_state = ProcessStates.TERMINATED
        self.current_event._event_type = EventTypes(3)
        return self.current_event


class SJF(SchedulerDES):
    def scheduler_func(self, cur_event):
        self.current_event = cur_event
        service_times_list = []
        processes = self.processes
        for process in processes:
            if process._process_state == ProcessStates.READY:
                service_times_list += [process.service_time]
        service_time_min = min(service_times_list)

        for process in processes:
            if process.service_time == service_time_min:
                return self.processes[process.process_id]
    def dispatcher_func(self, cur_process):
        self.current = cur_process
        self.current_event._event_time = cur_process.run_for(self.current.remaining_time, self.time) + self.time
        self.current._process_state = ProcessStates.TERMINATED
        self.current_event._event_type = EventTypes(3)
        return self.current_event


class RR(SchedulerDES):
    def scheduler_func(self, cur_event):
        self.current_event = cur_event
        return self.processes[self.current_event.process_id]

    def dispatcher_func(self, cur_process):
        self.current = cur_process
        self.current_event._event_time = cur_process.run_for(self.quantum, self.time) + self.time
        # print()
        # self.print_processes()
        # print()
        time_left = self.current.remaining_time
        # print(time_left)
        if time_left <= 0:
            self.current._process_state = ProcessStates.TERMINATED
            self.current_event._event_type = EventTypes(3)
            return self.current_event
        else:
            self.current._process_state = ProcessStates.READY
            return self.current_event



class SRTF(SchedulerDES):
    def scheduler_func(self, cur_event):
        service_times = []
        self.current_event = cur_event
        processes = self.processes
        for process in processes:
            if process._process_state == ProcessStates.READY:
                service_times += [process.service_time]

        min_service_time = min(service_times)

        for process in processes:
            if process.service_time == min_service_time:
                initial_process = self.process[process.process_id]
                initial_process_time = self.process[process.remaining_time]
                if self.__update_process_states() == True:
                    for process in processes: 
                        if process._process_state == ProcessStates.READY:
                            if process.remaining_time <= initial_process_time:
                                return self.process[process.process_id]
                else:
                    return initial_process



    def dispatcher_func(self, cur_process):
        processes = self.processes
        self.current = cur_process
        self.current_event._event_time = cur_process.run_for(self.quantum, self.time) + self.time
        if self.current.remaining_time <= 0:
            self.current._process_state = ProcessStates.TERMINATED
        else:
            self.current._process_state = ProcessStates.READY
        
        self.current_event._event_type = EventTypes(3)
        return self.current_event
