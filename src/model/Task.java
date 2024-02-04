package model;

public class Task {
    private int id;
    private String name;
    private TaskStatus taskStatus;
    private String description;

    public Task(String name, String description, TaskStatus taskStatus) {
        this.name = name;
        this.taskStatus = taskStatus;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return taskStatus;
    }

    public void setStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + taskStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}