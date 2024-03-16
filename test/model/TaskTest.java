package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    public void checkTasksAreEqualIfIdentifiersAreEqual() {
        Task task = new Task("name", "desc", TaskStatus.NEW);
        task.setId(100);
        Task taskExpected = new Task("name2", "desc2", TaskStatus.DONE);
        taskExpected.setId(100);
        assertEquals(taskExpected, task, "Задачи должны быть равны, если равны их идентификаторы");
    }

}