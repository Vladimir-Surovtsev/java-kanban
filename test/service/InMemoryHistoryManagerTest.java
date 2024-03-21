package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    @Test
    void add() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.addTaskToHistory(new Task("Новая задача", "Описание цели задачи", Status.NEW));
        final List<Task> history = historyManager.getTasksFromHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }
}