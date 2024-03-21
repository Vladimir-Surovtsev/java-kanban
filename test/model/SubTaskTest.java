package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {
    private final SubTask subTask = new SubTask("name", "desc", Status.NEW, 4);
    private final SubTask subTask2 = new SubTask("name2", "desc2", Status.IN_PROGRESS, 4);

    @Test
    public void checkSubtasksAreEqualIfIdentifiersAreEqual() {
        subTask2.setId(subTask.getId());
        assertEquals(subTask, subTask2, "Подзадачи должны быть равны, если равны их идентификаторы");
    }

}