package service;

import tasktable.TaskTable;
import dao.TaskFunctionalityDao;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskFunctionalityDao taskDao;

    @Transactional
    public Task getTaskById(int id) {
        return this.taskDao.getTaskbyId(id);
    }

    @Transactional
    public void removeTask(int id) {
        this.taskDao.removeTask(id);

    }

    @Transactional
    public int addTask(TaskTable task) {
        return this.taskDao.addTask(task);
    }

    @Transactional
    public void updateTask(TaskTable task) {
        this.taskDao.updateTask(task);

    }

    public void setTaskDao(TaskFunctionalityDao bookDao) {
        this.taskDao = bookDao;
    }

    @Transactional
    public List<Task> listTasks() {
        return this.taskDao.listTasks();
    }

}