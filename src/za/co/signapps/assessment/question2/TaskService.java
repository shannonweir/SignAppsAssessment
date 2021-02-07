package za.co.signapps.assessment.question2;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TaskService {

    // There is only one thread so dont see the actual point of threading.
    // This could potentially cause performance problems when there is a
    // large dataset to be processed. Changed it to 2 ;)
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    private SqlDatabase database = new SqlDatabase();
    private List<String> employees = new ArrayList<>();

    public List<String> getEmployees(String gender) throws ExecutionException, InterruptedException {
        Callable<List<String>> task = () -> database.getUsers(gender);
        Future<List<String>> future = executor.submit(task);
        employees.addAll(future.get());
        //For this method the wrong output was generated due to not matching up the user to the correct salary.
        //With threads we cant be certain of the order in which data will be processed. We can also use the synchronize
        //method to ensure that only one record is accessing a method at a time.
        Callable<Map<String, Double>> task2 = () -> database.getEmployeeToSalary(employees);
        Future<Map<String, Double>> employeeToSalaryMap = executor.submit(task2);
        Map<String, Double> stringDoubleMap = employeeToSalaryMap.get();
        return stringDoubleMap.entrySet().stream()
                .map(e -> "Employee: " + e.getKey() + " earns $" + e.getValue())
                .collect(Collectors.toCollection(ArrayList::new));

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
                return Arrays.asList("jon", "jz", "kanye");

        }

        public Map<String, Double> getEmployeeToSalary(List<String> employees) {
            List<String> exemptEmployees = new ArrayList<String>(Arrays.asList("jon", "jz", "kanye"));
            return employees.stream().collect(Collectors.toMap(s -> s, s -> exemptEmployees.contains(s) ? 1000.00 : 4000.00));
        }
    }

    public static void main(String args[]) throws ExecutionException,
            InterruptedException {
        TaskService service = new TaskService();
        service.getEmployees("m").forEach(System.out::println);
        service.shutdown();
    }
}