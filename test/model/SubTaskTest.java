package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {
    @Test
    public void setUp() {
        SubTask subTask = new SubTask("name", "desc", TaskStatus.NEW);
        subTask.setId(3);
        subTask.setEpicId(3);
        assertEquals(subTask.getEpicId(), 0, "Подзадачу нельзя назначить своим эпиком");
    }

}