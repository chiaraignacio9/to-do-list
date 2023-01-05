package ichiara.todolist.controller;

import ichiara.todolist.dto.TaskRequest;
import ichiara.todolist.models.Task;
import ichiara.todolist.models.User;
import ichiara.todolist.services.TaskService;
import ichiara.todolist.utils.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponse newTask(@RequestBody TaskRequest taskRequest, @RequestHeader("Authorization") String token){
        return taskService.newTask(taskRequest, token);
    }

    @DeleteMapping("/{id}")
    public TaskResponse deleteTaskById(@PathVariable String id){
        return taskService.deleteTaskById(id);
    }

    @GetMapping
    public List<Optional<Task>> viewTask(){
        List<String> listId = taskService.getTaskId();
        return taskService.getTask(listId);
    }
}
