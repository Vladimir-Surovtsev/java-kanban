package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private static int seq = 0;

    private int generateId() {
        return ++seq;
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        calculateEpicStatus(epic);
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

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

    public Task get(int id) {
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    public void update(Task task) {
        Task saved = tasks.get(task.getId());
        saved.setName(task.getName());
        saved.setStatus(task.getStatus());
        saved.setDescription(task.getDescription());
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        saved.setStatus(epic.getStatus());
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        saved.setSubTasksId(saved.getSubTasksId());
    }

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

    public List<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    public List<SubTask> getAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    public List<SubTask> getAllEpicSubTask(Epic epic) {
        List<SubTask> allSub = new ArrayList<>();
        for (int subId : epic.getSubTasksId()) {
            allSub.add(subTasks.get(subId));
        }
        return allSub;
    }

    public void deleteAll() {
        tasks.clear();
    }

    public void deleteAllEpic() {
        epics.clear();
        subTasks.clear();
    }

    public void deleteAllSubTask() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTasksId().clear();
        }
    }

    public void delete(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        for (int subTaskId : epics.get(id).getSubTasksId()) {
            subTasks.remove(subTaskId);
        }
        epics.remove(id);
    }

    public void deleteSubTask(int id) {
        SubTask removeSubTask = subTasks.remove(id);

        int epicId = removeSubTask.getEpicId();
        Epic epicSaved = epics.get(epicId);

        epicSaved.getSubTasksId().remove(removeSubTask.getEpicId());
        calculateEpicStatus(epicSaved);
    }

    private void calculateEpicStatus(Epic epic) {
        int countNew = 0;
        int countDone = 0;
        for (int id : epic.getSubTasksId()) {
            if (subTasks.get(id).getStatus() == Status.NEW) {
                countNew++;
            }
            if (subTasks.get(id).getStatus() == Status.DONE) {
                countDone++;
            }
        }
        if (countNew == epic.getSubTasksId().size()) {
            epic.setStatus(Status.NEW);
        } else if (countDone == epic.getSubTasksId().size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

}