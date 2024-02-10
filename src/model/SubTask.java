package model;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
    }

    public void setEpicId(int id) {
        if (id == this.getId()) {
            return;
        }
        this.epicId = id;
    }

    public int getEpicId() {
        return epicId;
    }

}