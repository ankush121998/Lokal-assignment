package service;

import tasktable.TaskTable;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public interface TaskService {

	public void updateTask(TaskTable task);
    public void removeTask(int id);
    public int addTask(TaskTable task);
    public TaskTable getTaskById(int id);
    public List<TaskTable> listTasks();

}