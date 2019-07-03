import java.util.ArrayList;
import java.util.Scanner;

public class WageEarner extends Employee {

    private double wage;

    public WageEarner(String name, String address, int employee_id, double money, String payment_method, int employee_type, boolean in_syndicate, double wage) {
        super(name, address, employee_id, money, payment_method, employee_type, in_syndicate);
        this.wage = wage;
    }

    //- change employee type to wage earner
    public void Change_employee_to_wage_earner(Employee employee, ArrayList<WageEarner> WageEarnerList, ArrayList<Employee> EmployeeList, ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);

        System.out.println("type "+employee.getName()+"´s wage:");
        double wage = scan.nextDouble();
        scan.nextLine();

        WageEarner new_employee = new WageEarner(employee.getName(), employee.getAddress(), employee.getEmployee_id(), employee.getMoney(), employee.getPayment_method(),2, employee.isIn_syndicate(), wage);
        if(employee.isIn_syndicate()){
            new_employee.setSyndicate_tax(employee.getSyndicate_tax());
            new_employee.setSyndicate_id(employee.getSyndicate_id());
            SyndicateList.remove(employee);
            SyndicateList.add(new_employee);
        }
        WageEarnerList.add(new_employee);
        EmployeeList.remove(employee);
        EmployeeList.add(new_employee);
    }

    public WageEarner() {
    }
}
