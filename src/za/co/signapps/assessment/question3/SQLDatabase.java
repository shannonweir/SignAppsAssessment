package za.co.signapps.assessment.question3;

import za.co.signapps.assessment.question3.domain.ToDoItem;
import za.co.signapps.assessment.question3.domain.ToDoList;
import za.co.signapps.assessment.question3.enums.SectionType;

public class SQLDatabase
{
    private static ToDoList toDoListPatient1;
    private static ToDoList toDoListPatient2;

    public static ToDoList getToDoListPatient1(String userId) {
        if (toDoListPatient1 == null) {
            toDoListPatient1 = getToDoList(SectionType.ALL, 1);
            toDoListPatient1.setPatientId(1);
        }
        toDoListPatient1.setUserId(userId);
        return toDoListPatient1;
    }

    public static ToDoList getToDoListPatient2(String userId) {
        if (toDoListPatient2 == null) {
            toDoListPatient2 = getToDoList(SectionType.ALL, 2);
            toDoListPatient2.setPatientId(2);
        }
        toDoListPatient2.setUserId(userId);
        return toDoListPatient2;
    }

    public static ToDoList getToDoList(SectionType sectionType, int patientId) {
        ToDoList toDoList = new ToDoList();
        switch (sectionType) {
            case SURGEON:
                toDoList.add(createToDoItem("patientSeen", true, false, SectionType.SURGEON));
                toDoList.add(createToDoItem("xrBooked", false, false, SectionType.SURGEON));
                toDoList.setPatientId(patientId);
                break;
            case WARD_SISTER:
                toDoList.add(createToDoItem("patientArrived", true, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("patientReady", true, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("preMedGiven", true, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("pregnancy", false, false, SectionType.WARD_SISTER));
                toDoList.setPatientId(patientId);
                break;
            case SCRUB_TEAM:
                toDoList.add(createToDoItem("xrContacted", true, false, SectionType.SCRUB_TEAM));
                toDoList.add(createToDoItem("theatreReady", true, false, SectionType.SCRUB_TEAM));
                toDoList.add(createToDoItem("repContacted", false, false, SectionType.SCRUB_TEAM));
                toDoList.setPatientId(patientId);
                break;
            case ALL:
                toDoList.add(createToDoItem("patientSeen", true, false, SectionType.SURGEON));
                toDoList.add(createToDoItem("xrBooked", false, false, SectionType.SURGEON));
                toDoList.add(createToDoItem("patientArrived", true, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("patientReady", true, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("preMedGiven", true, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("pregnancy", false, false, SectionType.WARD_SISTER));
                toDoList.add(createToDoItem("xrContacted", true, false, SectionType.SCRUB_TEAM));
                toDoList.add(createToDoItem("theatreReady", true, false, SectionType.SCRUB_TEAM));
                toDoList.add(createToDoItem("repContacted", false, false, SectionType.SCRUB_TEAM));
                toDoList.setPatientId(patientId);
                break;
        }
        return toDoList;
    }

    private static ToDoItem createToDoItem(String activity, boolean compulsory, boolean completed, SectionType sectionType) {
        return new ToDoItem(activity, compulsory, completed, sectionType);
    }

}
