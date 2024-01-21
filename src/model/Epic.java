package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subTasksId = new ArrayList<>();

    public Epic(String name, Status status, String description) {
        super(name, status, description);
    }

    public List<Integer> getSubTasksId() {
        return subTasksId;
    }

    public void setSubTaskId(int id) {
        subTasksId.add(id);
    }

}