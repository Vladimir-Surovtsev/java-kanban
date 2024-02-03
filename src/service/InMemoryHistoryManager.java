package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    List<Task> history = new ArrayList<>();
    @Override
    public void addTaskToHistory(Task task) {
        if (task == null) { return; }
        if (history.size() == 10) {
            history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public ArrayList<Task> getTasksFromHistory() {
        return new ArrayList<>(history);
    }
}
