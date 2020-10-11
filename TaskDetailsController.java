package controller;

import service.TaskService;
import tasktable.TaskTable;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(TaskDetailsController.class);
    private TaskService taskService;

    @Autowired(required = true)
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * DELETE TASK BY ID
     *
     * @param id task indentification
     * @return Http status
     */
    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TaskTable> deleteTask(@PathVariable("id") int id) {
        logger.debug("Deleting Task by id: " + id);

        TaskTable findedTask = this.taskService.getTaskById(id);

        if (findedTask == null) {
            logger.debug("Can't to delete. Task with id " + id + " not found");
            return new ResponseEntity<TaskTable>(HttpStatus.NOT_FOUND);
        }

        this.taskService.removeTask(id);
        return new ResponseEntity<TaskTable>(HttpStatus.NO_CONTENT);
    }

    /**
     * GET TASK BY ID
     *
     * @param id indentification of task
     * @return Return JSON Object task by id
     */
    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskTable> getTask(@PathVariable("id") int id) {
        logger.debug("Get Task by id: " + id);
        TaskTable task = this.taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<TaskTable>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TaskTable>(task, HttpStatus.OK);
    }

    /**
     * GET LIST OF TASK
     *
     * @return JSON Task Object
     */
    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public ResponseEntity<List<TaskTable>> listTasks() {
        logger.debug("List all tasks");
        List<TaskTable> tasks = this.taskService.listTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<List<TaskTable>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TaskTable>>(tasks, HttpStatus.OK);
    }

    /**
     * ADD
     *
     * Example JSON {"description":"This is task","id":0,"date":1493212282000,"hasdone":false}
     *
     * @param task geted task object
     * @return Http status
     */
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public ResponseEntity<TaskTable> createTask(@RequestBody TaskTable task) { //,    UriComponentsBuilder ucBuilder) {
        logger.debug("Add new Task to BD");

        if (task.getId() == 0) {
            int id = this.taskService.addTask(task);

            TaskTable storedTask = this.taskService.getTaskById(id);
            if (storedTask == null) {
                return new ResponseEntity<TaskTable>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<TaskTable>(task, HttpStatus.OK);

        } else {
            this.taskService.updateTask(task);
            return new ResponseEntity<TaskTable>(HttpStatus.CONFLICT);
        }
    }

    /**
     * UPDATE TASK BY ID
     *
     * @param id   task indentification
     * @param task geted task object
     * @return Http status
     */
    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TaskTable> updateTask(@PathVariable("id") int id, @RequestBody TaskTable task) {
        logger.debug("Update Task by id: " + id);

        TaskTable findedTask = this.taskService.getTaskById(id);

        if (findedTask == null) {
            logger.debug("Task with id: " + id + " not found");
            return new ResponseEntity<TaskTable>(HttpStatus.NOT_FOUND);
        }

        findedTask.setDescription(task.getDescription());
        findedTask.setDate(task.getDate());
        findedTask.setHasDone(task.getHasDone());
        this.taskService.updateTask(findedTask);

        return new ResponseEntity<TaskTable>(findedTask, HttpStatus.OK);
    }

}