import java.util.ArrayList;
import java.util.Scanner;

public class CommissionedEmployee extends WageEarner{

    private int commission;

    public CommissionedEmployee(String name, String address, int employee_id, double money, String payment_method, int employee_type, boolean in_syndicate, double wage, int commission) {
        super(name, address, employee_id, money, payment_method, employee_type, in_syndicate, wage);
        this.commission = commission;
    }

    //- change employee type to commissioned
    public void Change_employee_to_commissioned(Employee employee, ArrayList<CommissionedEmployee> CommissionedList, ArrayList<Employee> EmployeeList, ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);

        System.out.println("type "+employee.getName()+"´s wage:");
        double wage = scan.nextDouble();
        scan.nextLine();
        System.out.println("type "+employee.getName()+"´s commission per sale (in %)");
        int commission = scan.nextInt();
        scan.nextLine();

        CommissionedEmployee new_employee = new CommissionedEmployee(employee.getName(), employee.getAddress(), employee.getEmployee_id(), employee.getMoney(), employee.getPayment_method(), 3, employee.isIn_syndicate(), wage, commission);
        if(employee.isIn_syndicate()){
            new_employee.setSyndicate_tax(employee.getSyndicate_tax());
            new_employee.setSyndicate_id(employee.getSyndicate_id());
            SyndicateList.remove(employee);
            SyndicateList.add(new_employee);
        }
        CommissionedList.add(new_employee);
        EmployeeList.remove(employee);
        EmployeeList.add(new_employee);
    }

    //- purchase ticket(resultado de venda)
    public void PurchaseTicket(ArrayList<CommissionedEmployee> CommissionedList){
        Scanner scan = new Scanner(System.in);

        System.out.println("who would like to submit a purchase ticket(resultado de venda)?");
        for(int i = 0; i< CommissionedList.size(); i++){
            System.out.println(i+". "+CommissionedList.get(i).getName());
        }
        int index = scan.nextInt();
        scan.nextLine();

        CommissionedEmployee newCommissioned = CommissionedList.get(index);

        System.out.println("type the day of the month that the sale was made:");
        int date = scan.nextInt();
        scan.nextLine();

        System.out.println("type the amount of R$ of the sale");
        double sale = scan.nextDouble();
        scan.nextLine();

        double money = newCommissioned.commission * sale;
        money = money / 100;
        System.out.println("commission of sale was R$ "+money);
        money = money + newCommissioned.getMoney();
        newCommissioned.setMoney(money);
    }

    public CommissionedEmployee(){

    }

}
