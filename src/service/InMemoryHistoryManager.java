package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static class Node {
        private final Task item;
        private Node next;
        private Node prev;

        Node(Node prev, Task element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private final HashMap<Integer, Node> history = new HashMap<>();
    private Node first;
    private Node last;


    @Override
    public void addTaskToHistory(Task task) {
        Node node = history.get(task.getId());
        if (node != null) {
            removeNode(node);
        }
        linkLast(task);
        history.put(task.getId(), node);
    }

    @Override
    public List<Task> getTasksFromHistory() {
        ArrayList<Task> list = new ArrayList<>();
        Node current = first;
        while (current != null) {
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    @Override
    public void remove(int id) {
        Node node = history.get(id);
        removeNode(node);
    }

    private void linkLast(Task task) {
        final Node l = last;
        final Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    private void removeNode(Node node) {
        if (node == null) {
            return;
        } else if (node.prev == null) {
            first = first.next;
        } else if (node.next == null) {
            last = node.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        history.remove(node.item.getId());
    }
}
