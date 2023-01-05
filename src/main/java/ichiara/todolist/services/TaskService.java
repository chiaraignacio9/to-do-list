package ichiara.todolist.services;

import ichiara.todolist.config.JwtAuthFilter;
import ichiara.todolist.config.JwtService;
import ichiara.todolist.dto.TaskRequest;
import ichiara.todolist.models.Task;
import ichiara.todolist.models.User;
import ichiara.todolist.repositories.TaskRepository;
import ichiara.todolist.repositories.UserRepository;
import ichiara.todolist.utils.TaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static ichiara.todolist.config.JwtAuthFilter.userEmail;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;

    public TaskResponse newTask(TaskRequest taskRequest, String token){

        Optional<User> user =  userRepository.findByEmail(userEmail);

        Task task = new Task();

        task.setId(String.valueOf(UUID.randomUUID()));
        task.setName(taskRequest.getName());
        task.setIdUser(user.get().getId());

        taskRepository.save(task);



        return null;
    }

    public List<String> getTaskId(){
        Optional<User> user = userRepository.findByEmail(userEmail);
        return taskRepository.findTaskIdByUserId(user.get().getId());
    }

    public List<Optional<Task>> getTask(List<String> list){

        List<Optional<Task>> listTask = new ArrayList<>();

        for(String taskId : list){
            listTask.add(taskRepository.findById(taskId));
        }

        return listTask;
    }

    public TaskResponse deleteTaskById(String id){
        taskRepository.deleteById(id);
        return TaskResponse.builder()
                .msj("Task deleted successfully!")
                .build();
    }
}
