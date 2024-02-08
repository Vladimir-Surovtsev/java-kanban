package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;

public interface TaskManager {
    Task create(Task task);

    Epic createEpic(Epic epic);

    SubTask createSubTask(SubTask subTask);

    Task get(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    void update(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    List<Task> getTasks();

    List<Task> getEpics();

    List<Task> getSubtasks();

    List<Task> getEpicSubtasks(int id);

    void deleteAll();

    void deleteAllEpic();

    void deleteAllSubTask();

    void delete(int id);

    void deleteEpic(int id);

    void deleteSubTask(int id);

    List<Task> getHistory();

}
