package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    public void checkTasksAreEqualIfIdentifiersAreEqual() {
        Task task = new Task("name", "desc", Status.NEW);
        task.setId(100);
        Task taskExpected = new Task("name2", "desc2", Status.DONE);
        taskExpected.setId(100);
        assertEquals(taskExpected, task, "Задачи должны быть равны, если равны их идентификаторы");
    }

}