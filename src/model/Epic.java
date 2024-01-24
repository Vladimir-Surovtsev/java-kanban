package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTasksId = new ArrayList<>();

    public Epic(String name, Status status, String description) {
        super(name, status, description);
    }

    public List<Integer> getSubTasksId() {
        return new ArrayList<>(subTasksId);
    }

    public void addSubTaskId(int id) {
        subTasksId.add(id);
    }

    public void setSubTasksId(List<Integer> subTasksId) {
        this.subTasksId = subTasksId;
    }

}