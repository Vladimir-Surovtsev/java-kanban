package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTasksId = new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public List<Integer> getSubTasksId() {
        return new ArrayList<>(subTasksId);
    }

    public void addSubTaskId(int id) {
        if (id == super.getId()) {
            System.out.println("Epic нельзя добавить в самого себя в виде подзадачи");
            return;
        }
        subTasksId.add(id);
    }

    public void setSubTasksId(List<Integer> subTasksId) {
        this.subTasksId = subTasksId;
    }

    @Override
    public TaskType getType() {
        return TaskType.EPIC;
    }

}