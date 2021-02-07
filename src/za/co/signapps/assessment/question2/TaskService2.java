package za.co.signapps.assessment.question2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// This is a second solution
public class TaskService2 {
    private ExecutorService executor = Executors.newFixedThreadPool(1);
    private SqlDatabase database = new SqlDatabase();
    private List<String> employees = new ArrayList<>();

    public List<String> getEmployees(String gender) throws ExecutionException, InterruptedException {
        Callable<List<String>> task = () -> database.getUsers(gender);
        Future<List<String>> future = executor.submit(task);
        employees.addAll(future.get());

        return employees.stream().map(x -> {
            double salaryForEmployee = database.getSalaryForEmployee(x);
            try {
                return "Employee: " + x + " earns $" + salaryForEmployee;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toCollection(ArrayList::new));

    }

    public void shutdown() {
        executor.shutdown();
    }

    //assume this is a real live database
    public class SqlDatabase {
        public List<String> getUsers(String gender) {
            if (gender == "f")
                return Arrays.asList("sue", "mary", "adelle");
            else
                return Arrays.asList("jon", "jz", "kanye", "a", "b");

        }

        public double getSalaryForEmployee(String employee) {
            List<String> exemptEmployees = Arrays.asList("jon", "jz", "kanye");
            if (exemptEmployees.stream().anyMatch(x -> x.equals(employee)))
                return 1000;
            return 4000;
        }
    }

    public static void main(String args[]) throws ExecutionException,
            InterruptedException {
        TaskService2 service = new TaskService2();
        service.getEmployees("m").forEach(System.out::println);
        service.shutdown();
    }

}
