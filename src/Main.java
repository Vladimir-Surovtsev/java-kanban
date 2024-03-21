import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.Managers;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefaultTaskManager();
        Task task = taskManager.create(new Task("Новая задача", "Описание цели задачи", Status.NEW));
        Task task2 = taskManager.create(new Task("Вторая задача", "2-ое Описание цели задачи", Status.NEW));
        Epic epic = taskManager.createEpic(new Epic("Новый эпик", "Описание эпика", Status.NEW));
        SubTask subTask = taskManager.createSubTask(new SubTask("Новая подзадача",
                "Описание подзадачи", Status.NEW, epic.getId()));
        SubTask subTask2 = taskManager.createSubTask(new SubTask("Вторая подзадача",
                "2-ое Описание подзадачи", Status.NEW, epic.getId()));
        System.out.println("Create task: " + task);
        System.out.println("Create task2: " + task2);
        System.out.println("Create epic: " + epic);
        System.out.println("Create subTask: " + subTask);
        System.out.println("Create subTask: " + subTask2);

        Task taskFromManager = taskManager.get(task.getId());
        System.out.println("Get task: " + taskFromManager);
        Task epicFromManager = taskManager.getEpic(epic.getId());
        System.out.println("Get epic: " + epicFromManager);
        Task subtaskFromManager = taskManager.getSubTask(subTask.getId());
        System.out.println("Get subtask: " + subtaskFromManager);

        Task taskFromManager2 = taskManager.get(task2.getId());
        System.out.println("Get task: " + taskFromManager2);
        Task subtaskFromManager2 = taskManager.getSubTask(subTask2.getId());
        System.out.println("Get subtask: " + subtaskFromManager2);
        Task taskFromManager3 = taskManager.get(task.getId());
        System.out.println("Get task: " + taskFromManager3);

        taskFromManager.setName("New name");
        taskManager.update(taskFromManager);
        System.out.println("Update task: " + taskFromManager);

        taskManager.delete(taskFromManager.getId());
        System.out.println("Delete: " + task);

        printAllTasks(taskManager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasks(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

}