package com.example.prova.service;

import com.example.prova.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private List<Task> tasks;

    public TaskService(){
        tasks = new ArrayList<>();
    }
    public List<Task> searchAllTasks(){
        return tasks;
    }

    public Task createTask(Task task) throws Exception {
        tasks.add(task);
        return task;
    }

    public Task searchTask(int idTask) throws Exception {
        Optional<Task> optinalTask = tasks.stream().filter(e -> e.getIdTask() == idTask).findFirst();
        if (optinalTask.isPresent()){
            return optinalTask.get();
        } else {
            throw new Exception("Task not found!");
        }
    }

    public void removeTask(int idTask){
        tasks.removeIf(task -> task.getIdTask() == idTask);
    }

    public Task editTask(int idTask, Task task) throws Exception{
        Optional<Task> optinalTask = tasks.stream().filter(e -> e.getIdTask() == idTask).findFirst();
        if (optinalTask.isPresent()){
            optinalTask.get().setTitle(task.getTitle());
            optinalTask.get().setDescription(task.getDescription());
            optinalTask.get().setStatus(task.getStatus());
            return task;
        } else {
            throw new Exception("Tarefa não encotrada!");
        }
    }

    public Task editStatusTask(int idTask, Task task) throws Exception{
        Optional<Task> optionalTask = tasks.stream().filter(e -> e.getIdTask() == idTask).findFirst();
        if (optionalTask.isPresent()){
            optionalTask.get().setStatus(task.getStatus());
            return task;
        } else {
            throw new Exception("Tarefa não encontrada");
        }
    }





}
