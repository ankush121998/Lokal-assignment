package dao;

import tasktable.TaskTable;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TaskFunctionalityDaoImpl implements TaskFunctionalityDao {

    private static final Logger logger = LoggerFactory.getLogger(TaskFunctionalityDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
        This method will get called by a get HTTP request
    */
    public TaskTableTaskTable getTaskbyId(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        TaskTable task = (TaskTable) session.load(TaskTable.class, new Integer(id));
        logger.debug("Get task by " + id + " successfully.\nTask details: " + task);
        return task;

    }

    /*
        Method used to remove the task with given ID, once the task has removed from the tastList then it
        would not be visible to the user in taskList.
    */
    public void removeTaskById(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        TaskTable task = (TaskTable) session.load(TaskTable.class, new Integer(id));
        if (task != null)
            session.delete(task);
        logger.debug("Task with id = " + id + " has been successfully deleted.\nTask details are as follows: " + task);

    }

    /*
        Here we are passing Task class object to update the given task object.
    */
    public void updateTask(TaskTable task) {

        Session session = this.sessionFactory.getCurrentSession();
        session.update(task);
        logger.debug("Task has been updated successfully.\nTask details are as follows : " + task);

    }

    /*
        This method is usde to add a
    */
    public int addTask(TaskTable task) {

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(task);
        session.flush();
        logger.debug("Task has been saved successfully. \n Task details are as follows : " + task);
        return task.getId();

    }

    @SuppressWarnings("unchecked")
    public List<TaskTable> listTasks() {

        Session session = this.sessionFactory.getCurrentSession();
        List<TaskTable> taskDetails = session.createQuery("FROM Task").list();
        for(TaskTable taskValues: taskDetails){
            logger.debug("Task list: " + taskValues);
        }
        return taskDetails;

    }

}