package com.example.prova.controller;

import com.example.prova.entity.Task;
import com.example.prova.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("add")
    public ResponseEntity<?> createTask (@RequestBody Task task){
        try {
            task = taskService.createTask(task);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na criação de tarefas!",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("tasks")
    public ResponseEntity<?> searchAllTasks(){
        try {
            List<Task> listTask = taskService.searchAllTasks();
            return new ResponseEntity<>(listTask, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição!",HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<?> searchTask(@PathVariable ("idTask") int idTask){
        try {
            Task task = taskService.searchTask(idTask);
            return new ResponseEntity(task,HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{idTask}")
    public ResponseEntity<?> removeTask(@PathVariable("idTask") int idTask){
        try {
            taskService.removeTask(idTask);
            return new ResponseEntity<>("Tarefa removida com sucesso!", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição!",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{idTask}")
    public ResponseEntity<?> editTask(@RequestBody Task task,@PathVariable ("idTask") int idTask){
        try {
            task = taskService.editTask(idTask, task);
            return new ResponseEntity("Tarefa editada com sucesso!", HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição!", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/editStatus/{idTask}")
    public ResponseEntity<?> editStatusTask(@RequestBody Task task,@PathVariable ("idTask") int idTask){
        try {
            task = taskService.editStatusTask(idTask, task);
            return new ResponseEntity("Tarefa editada com sucesso!", HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<>("Erro na requisição!", HttpStatus.BAD_REQUEST);
        }
    }










}
