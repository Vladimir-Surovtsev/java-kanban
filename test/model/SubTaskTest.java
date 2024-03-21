package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {
    private final SubTask subTask = new SubTask("name", "desc", Status.NEW, 3);
    private final SubTask subTask2 = new SubTask("name2", "desc2", Status.IN_PROGRESS, 3);
    @Test
    public void checkSubtaskCannotBeOwnTask() {
        subTask.setId(3);
        subTask.setEpicId(3);
        assertEquals(subTask.getEpicId(), 0, "Подзадачу нельзя назначить своим эпиком");
    }
    @Test
    public void checkSubtasksAreEqualIfIdentifiersAreEqual() {
        subTask2.setId(subTask.getId());
        assertEquals(subTask, subTask2, "Подзадачи должны быть равны, если равны их идентификаторы");
    }

}