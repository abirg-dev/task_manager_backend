package dev.abirg.task_manager.service;

import dev.abirg.task_manager.exception.TaskNotFoundException;
import dev.abirg.task_manager.entity.Task;
import dev.abirg.task_manager.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository)
    {
        this.repository = repository;
    }

    // Retrieve all tasks
    public List<Task> getAllTasks()
    {
        return repository.findAll();
    }

    // Retrieve a single task
    public Task getTaskById(Long id)
    {
        return repository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(id));
    }

    // Create a new task
    public Task createTask(Task task)
    {
        task.setId(null);
        task.setCompleted(false);
        return repository.save(task);
    }

    // Update task details
    public Task updateTask(Long id, Task updatedTask)
    {

        Task task = getTaskById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());

        return repository.save(task);
    }

    // Change task completion status
    public Task updateTaskStatus(Long id, boolean completed)
    {

        Task task = getTaskById(id);
        task.setCompleted(completed);

        return repository.save(task);
    }

    // Delete an existing task
    public void deleteTask(Long id)
    {

        Task task = getTaskById(id);
        repository.delete(task);
    }
}