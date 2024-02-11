package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    Epic epic = new Epic("name", "desc", TaskStatus.NEW);
    Epic epicExpected = new Epic("name2", "desc2", TaskStatus.DONE);

    @Test
    void checkEpicsAreEqualIfIdentifiersAreEqual() {
        assertEquals(epicExpected, epic, "эпики должны быть равны");
    }

    @Test
    void checkEpicCannotBeSubtask() {
        epic.addSubTaskId(epic.getId());
        assertEquals(epic.getSubTasksId().size(), 0, "Epic нельзя добавить в самого себя в виде подзадачи");
    }
}