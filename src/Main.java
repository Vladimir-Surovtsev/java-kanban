import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task = taskManager.create(new Task("Новая задача", Status.NEW, "Описание цели задачи"));
        Task task2 = taskManager.create(new Task("Вторая задача", Status.NEW, "2-ое Описание цели задачи"));
        Epic epic = taskManager.createEpic(new Epic("Новый эпик", Status.NEW, "Описание эпика"));
        SubTask subTask = taskManager.createSubTask(new SubTask("Новая подзадача", Status.NEW,
                "Описание подзадачи", epic.getId()));
        SubTask subTask2 = taskManager.createSubTask(new SubTask("Вторая подзадача", Status.NEW,
                "2-ое Описание подзадачи", epic.getId()));
        System.out.println("Create task: " + task);
        System.out.println("Create task2: " + task2);
        System.out.println("Create epic: " + epic);
        System.out.println("Create subTask: " + subTask);
        System.out.println("Create subTask: " + subTask2);

        Task taskFromManager = taskManager.get(task.getId());
        System.out.println("Get task: " + taskFromManager);

        taskFromManager.setName("New name");
        taskManager.update(taskFromManager);
        System.out.println("Update task: " + taskFromManager);

        taskManager.delete(taskFromManager.getId());
        System.out.println("Delete: " + task);
    }

}
