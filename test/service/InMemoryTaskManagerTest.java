package service;

import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Status.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {

    private final TaskManager taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());

    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        task.setId(33);

        final int taskId = taskManager.create(task).getId();

        final Task savedTask = taskManager.get(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    void addNewEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        epic.setId(35);

        final int epicId = taskManager.createEpic(epic).getId();

        final Epic savedEpic = taskManager.getEpic(epicId);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic, savedEpic, "Эпики не совпадают.");

        final List<Task> epics = taskManager.getEpics();

        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество эпиков.");
        assertEquals(epic, epics.getFirst(), "Эпики не совпадают.");
    }

    @Test
    void addNewSubTask() {
        SubTask subTask = new SubTask("Test addNewSubTask", "Test addNewSubTask description", NEW, 22);
        subTask.setId(22);

        final int subTaskId = taskManager.createSubTask(subTask).getId();

        final SubTask savedSubTask = taskManager.getSubTask(subTaskId);

        assertNotNull(savedSubTask, "Подзадача не найдена.");
        assertEquals(subTask, savedSubTask, "Подзадачи не совпадают.");

        final List<Task> subTasks = taskManager.getSubtasks();

        assertNotNull(subTasks, "Подзадачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество подзадач.");
        assertEquals(subTask, subTasks.getFirst(), "Подзадачи не совпадают.");
    }

    @Test
    void equalsTasks() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        epic.setId(31);
        SubTask subTask = new SubTask("Test addNewSubTask", "Test addNewSubTask description", NEW, 31);
        subTask.setId(31);
        assertEquals(epic, subTask, "Наследники класса Task должны быть равны друг другу, если равен их id");
    }
}