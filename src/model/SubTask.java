package model;

public class SubTask extends Task {
    private final int epicId;

    public SubTask(String name, TaskStatus taskStatus, String description, int epicId) {
        super(name, taskStatus, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

}