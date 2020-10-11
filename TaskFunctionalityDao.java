package dao;

import tasktable.TaskTable;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public interface TaskFunctionalityDao {

	public void updateTask (TaskTable task);
    public void removeTask (int id);
    public int addTask (TaskTable task);
    public TaskTable getTaskbyId (int id);
    public List<TaskTable> listTasks ();
}