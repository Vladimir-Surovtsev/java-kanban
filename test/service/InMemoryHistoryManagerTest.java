package service;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void add() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.addTaskToHistory(new Task("Новая задача", "Описание цели задачи", TaskStatus.NEW));
        final List<Task> history = historyManager.getTasksFromHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }
}