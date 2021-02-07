package za.co.signapps.assessment.question3.domain;

import za.co.signapps.assessment.question3.enums.SectionType;

public class ToDoItem {
    private String activity;
    private boolean compulsory;
    private boolean completed;
    private SectionType sectionType;

    public ToDoItem() {
        this.completed = false;
    }

    public ToDoItem(String activity, boolean compulsory, boolean completed, SectionType sectionType) {
        this.activity = activity;
        this.compulsory = compulsory;
        this.completed = completed;
        this.sectionType = sectionType;
    }

    public String getActivity() {
        return activity;
    }

 /*   public void setActivity(String activity) {
        this.activity = activity;
    }*/

    public boolean isCompulsory() {
        return compulsory;
    }

  /*  public void setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
    }*/

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

   /* public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }*/
}
