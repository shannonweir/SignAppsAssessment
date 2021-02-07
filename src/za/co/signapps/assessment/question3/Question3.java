package za.co.signapps.assessment.question3;

import za.co.signapps.assessment.question3.domain.ToDoItem;
import za.co.signapps.assessment.question3.domain.ToDoList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Question3 {

    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        Question3 question3 = new Question3();
        List<ToDoList> toDoListList = question3.simulateMultipleUserRequestForToDoLists();
        for (int i = 0; i < toDoListList.size(); i++) {
            if (i == 2) {
                question3.updateToDoList(toDoListList.get(i), false);
            } else {
                question3.updateToDoList(toDoListList.get(i), true);
            }
        }
    }

    public synchronized void updateToDoList(ToDoList toDoList, boolean isCompleted) {
        List<ToDoItem> todoItemList = toDoList.getTodoItemList();
        ToDoItem toDoItem = getToDoItemByActivity(todoItemList, "patientSeen");
        toDoItem.setCompleted(isCompleted);
        boolean isSectionCompleted = todoItemList.stream()
                .filter(toDoItem1 -> toDoItem1.getSectionType().equals(toDoItem.getSectionType()))
                .filter(toDoItem1 -> toDoItem1.isCompulsory())
                .allMatch(toDoItem1 -> toDoItem1.isCompleted());
        if (isSectionCompleted) {
            System.out.println(toDoItem.getSectionType().name() + " has been completed");
        }
    }

    private ToDoItem getToDoItemByActivity(List<ToDoItem> todoItemList, String activity) {
        return todoItemList.stream()
                .filter(item -> item.getActivity().equals(activity))
                .findFirst()
                .orElse(null);
    }

    public List<ToDoList> simulateMultipleUserRequestForToDoLists() {
        List<ToDoList> toDoListList = new ArrayList<>();
        List<Callable<ToDoList>> callables = Arrays.asList(
                () -> SQLDatabase.getToDoListPatient1("hunk"),
                () -> SQLDatabase.getToDoListPatient2("punk"),
                () -> SQLDatabase.getToDoListPatient1("bunk"));
        try {
            toDoListList = executor.invokeAll(callables).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    }).collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return toDoListList;
    }
}
