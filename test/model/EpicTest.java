package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void setUp() {
        Epic epic = new Epic("name", "desc", TaskStatus.NEW);
        Epic epicExpected = new Epic("name2", "desc2", TaskStatus.DONE);

        assertEquals(epicExpected, epic, "эпики должны быть равны");

        epic.addSubTaskId(epic.getId());
        assertEquals(epic.getSubTasksId().size(), 0,  "Epic нельзя добавить в самого себя в виде подзадачи");
    }
}