package za.co.signapps.assessment.question3.domain;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<ToDoItem> toDoItemList;
    private int patientId;
    private String userId;

    public ToDoList() {
        this.toDoItemList = new ArrayList<>();
    }

    public ToDoList(List<ToDoItem> toDoItemList) {
        this.toDoItemList = toDoItemList;
    }

    public List<ToDoItem> getTodoItemList() {
        return toDoItemList;
    }

    public void setTodoItemList(List<ToDoItem> toDoItemList) {
        this.toDoItemList = toDoItemList;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void add(ToDoItem toDoItem){
        this.toDoItemList.add(toDoItem);
    }
}
