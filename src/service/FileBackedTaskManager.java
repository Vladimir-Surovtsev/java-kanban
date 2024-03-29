package service;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTaskManager(HistoryManager historyManager) {
        this(historyManager, new File(TASK_CSV));
    }

    public FileBackedTaskManager(File file) {
        this(Managers.getDefaultHistory(), file);
    }

    public FileBackedTaskManager(HistoryManager historyManager, File file) {
        super(historyManager);
        this.file = file;
    }

    public void init() {
        loadFromFile();
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        manager.init();
        return manager;
    }

    @Override
    public Task get(int id) {
        Task newTask = super.get(id);
        save();
        return newTask;
    }

    @Override
    public Epic getEpic(int id) {
        Epic newEpic = super.getEpic(id);
        save();
        return newEpic;
    }

    @Override
    public SubTask getSubTask(int id) {
        SubTask newSubTask = super.getSubTask(id);
        save();
        return newSubTask;
    }

    @Override
    public Task create(Task task) {
        Task newTask = super.create(task);
        save();
        return newTask;
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        SubTask newSubTask = super.createSubTask(subTask);
        save();
        return newSubTask;
    }

    @Override
    public Epic createEpic(Epic epic) {
        Epic newEpic = super.createEpic(epic);
        save();
        return newEpic;
    }

    @Override
    public void update(Task task) {
        super.update(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        save();
    }

    @Override
    public void delete(int id) {
        super.delete(id);
        save();
    }

    private String toString(Task task) {
        return task.getId() + "," + task.getType() + "," + task.getName() + "," + task.getStatus() + "," +
                task.getDescription();
    }

    private Task fromString(String value) {
        final String[] columns = value.split(",");
        int id = Integer.parseInt(columns[0]);
        String name = columns[2];
        String description = columns[4];
        Status status = Status.valueOf(columns[3]);
        int epicId = Integer.parseInt(columns[5]);

        TaskType type = TaskType.valueOf(columns[1]);
        Task task = switch (type) {
            case TASK -> new Task(name, description, status);
            case SUBTASK -> new SubTask(name, description, status, epicId);
            case EPIC -> new Epic(name, description, status);
        };
        task.setId(id);
        return task;
    }

    protected static String toString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for (Task task : manager.getTasksFromHistory()) {
            sb.append(task.getId()).append(",");
        }
        return sb.toString();
    }

    protected static List<Integer> historyFromString(String value) {
        final String[] ids = value.split(",");
        List<Integer> history = new ArrayList<>();
        for (String id : ids) {
            history.add(Integer.valueOf(id));
        }
        return history;
    }

    private void save() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append("id,type,name,status,description,epic");
            writer.newLine();
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                writer.append(toString(entry.getValue()));
                writer.newLine();
            }
            for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
                writer.append(toString(entry.getValue()));
                writer.newLine();
            }
            for (Map.Entry<Integer, SubTask> entry : subTasks.entrySet()) {
                writer.append(toString(entry.getValue())).append(",").append(String.valueOf(entry.getValue().getEpicId()));
                writer.newLine();
            }
            writer.newLine();
            writer.append(toString(historyStorage));
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка в файле: " + file.getName(), e);
        }

    }

    private void loadFromFile() {
        HashMap<Integer, Task> temp = new HashMap<>();
        int maxId = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(file, UTF_8))) {
            reader.readLine();
            while (true) {
                String line = reader.readLine();
                if (line.isEmpty()) {
                    break;
                }
                final Task task = fromString(line);
                final int id = task.getId();
                temp.put(id, task);
                TaskType type = task.getType();
                switch (type) {

                    case TASK:
                        tasks.put(id, task);

                    case SUBTASK:
                        subTasks.put(id, (SubTask) task);

                    case EPIC:
                        assert task instanceof Epic;
                        epics.put(id, (Epic) task);
                }
                maxId = Math.max(maxId, id);
            }

            String line = reader.readLine();
            for (int id : historyFromString(line)) {
                historyStorage.addTaskToHistory(temp.get(id));
            }

        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка в файле: " + file.getName(), e);
        }
        seq = maxId;
    }

    public static final String TASK_CSV = "task.csv";
}
