package service;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, SubTask> subTasks;
    private final HistoryManager historyStorage;
    private int seq = 0;

    private int generateId() {
        return ++seq;
    }

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyStorage = historyManager;
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    @Override
    public Task create(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        calculateEpicStatus(epic);
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        subTasks.put(subTask.getId(), subTask);
        Epic saved = epics.get(subTask.getEpicId());
        if (saved == null) {
            System.out.println("Такого эпика нет в списке!");
        } else {
            saved.addSubTaskId(subTask.getId());
            calculateEpicStatus(saved);
        }
        return subTask;
    }

    @Override
    public Task get(int id) {
        historyStorage.addTaskToHistory(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        historyStorage.addTaskToHistory(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTask(int id) {
        historyStorage.addTaskToHistory(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void update(Task task) {
        Task saved = tasks.get(task.getId());
        saved.setName(task.getName());
        saved.setStatus(task.getStatus());
        saved.setDescription(task.getDescription());
    }

    @Override
    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        saved.setStatus(epic.getStatus());
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        saved.setSubTasksId(saved.getSubTasksId());
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        SubTask saved = subTasks.get(subTask.getId());
        saved.setStatus(subTask.getStatus());
        saved.setName(subTask.getName());
        saved.setDescription(subTask.getDescription());
        int epicId = subTask.getEpicId();
        Epic savedEpic = epics.get(epicId);
        calculateEpicStatus(savedEpic);
        savedEpic.setId(generateId());
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Task> getSubtasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public List<Task> getEpicSubtasks(int epicId) {
        List<Task> allSub = new ArrayList<>();
        for (int subId : epics.get(epicId).getSubTasksId()) {
            allSub.add(subTasks.get(subId));
        }
        return allSub;
    }

    @Override
    public void deleteAll() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpic() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void deleteAllSubTask() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTasksId().clear();
        }
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
        historyStorage.remove(id);
    }

    @Override
    public void deleteEpic(int id) {
        for (int subTaskId : epics.get(id).getSubTasksId()) {
            subTasks.remove(subTaskId);
        }
        epics.remove(id);
    }

    @Override
    public void deleteSubTask(int id) {
        SubTask removeSubTask = subTasks.remove(id);

        int epicId = removeSubTask.getEpicId();
        Epic epicSaved = epics.get(epicId);

        epicSaved.getSubTasksId().remove(removeSubTask.getEpicId());
        calculateEpicStatus(epicSaved);
    }

    @Override
    public List<Task> getHistory() {
        return historyStorage.getTasksFromHistory();
    }

    private void calculateEpicStatus(Epic epic) {
        int countNew = 0;
        int countDone = 0;
        for (int id : epic.getSubTasksId()) {
            if (subTasks.get(id).getStatus() == TaskStatus.NEW) {
                countNew++;
            }
            if (subTasks.get(id).getStatus() == TaskStatus.DONE) {
                countDone++;
            }
        }
        if (countNew == epic.getSubTasksId().size()) {
            epic.setStatus(TaskStatus.NEW);
        } else if (countDone == epic.getSubTasksId().size()) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

}