import model.Epic;
import model.TaskStatus;
import model.SubTask;
import model.Task;
import service.Managers;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefaultTaskManager();
        Task task = taskManager.create(new Task("Новая задача", TaskStatus.NEW, "Описание цели задачи"));
        Task task2 = taskManager.create(new Task("Вторая задача", TaskStatus.NEW, "2-ое Описание цели задачи"));
        Epic epic = taskManager.createEpic(new Epic("Новый эпик", TaskStatus.NEW, "Описание эпика"));
        SubTask subTask = taskManager.createSubTask(new SubTask("Новая подзадача", TaskStatus.NEW,
                "Описание подзадачи", epic.getId()));
        SubTask subTask2 = taskManager.createSubTask(new SubTask("Вторая подзадача", TaskStatus.NEW,
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
