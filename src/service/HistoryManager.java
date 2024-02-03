package service;

import model.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void addTaskToHistory(Task task);
    ArrayList<Task> getTasksFromHistory();
}
