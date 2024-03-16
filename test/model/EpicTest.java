package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private final Epic epic = new Epic("name", "desc", TaskStatus.NEW);
    private final Epic epicExpected = new Epic("name2", "desc2", TaskStatus.DONE);

    @Test
    void checkEpicsAreEqualIfIdentifiersAreEqual() {
        assertEquals(epicExpected, epic, "эпики должны быть равны, если равны их идентификаторы");
    }

    @Test
    void checkEpicCannotBeSubtask() {
        epic.addSubTaskId(epic.getId());
        assertEquals(epic.getSubTasksId().size(), 0, "Epic нельзя добавить в самого себя в виде подзадачи");
    }
}