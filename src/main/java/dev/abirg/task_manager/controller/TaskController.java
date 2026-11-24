package dev.abirg.task_manager.controller;

import dev.abirg.task_manager.entity.Task;
import dev.abirg.task_manager.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "https://task-manager-frontend-bfd6.onrender.com")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service)
    {
        this.service = service;
    }

    // Get all tasks
    @GetMapping public List<Task> getAllTasks()
    {
        return service.getAllTasks();
    }

    // Get task by ID
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id)
    {
        return service.getTaskById(id);
    }

    // Create task
    @PostMapping
    public ResponseEntity<Task>
    createTask(@Valid @RequestBody Task task)
    {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createTask(task));
    }

    // Update task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @Valid @RequestBody Task task)
    {

        return service.updateTask(id, task);
    }

    // Update completion status
    @PatchMapping("/{id}/status")
    public Task
    updateStatus(@PathVariable Long id,
                 @RequestBody Map<String, Boolean> request)
    {

        Boolean completed = request.get("completed");

        if (completed == null) {
            throw new org.springframework.web.server
                    .ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Completed status is required");
        }

        return service.updateTaskStatus(id, completed);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteTask(@PathVariable Long id)
    {

        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
