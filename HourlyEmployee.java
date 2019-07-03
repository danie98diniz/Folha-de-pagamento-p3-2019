import java.util.ArrayList;
import java.util.Scanner;

public class HourlyEmployee extends Employee {

    private double hourly_wage;

    public HourlyEmployee(String name, String address, int employee_id, double money, String payment_method, int employee_type, boolean in_syndicate, double hourly_wage) {
        super(name, address, employee_id, money, payment_method, employee_type, in_syndicate);
        this.hourly_wage = hourly_wage;
    }

    //- change employee type to hourly
    public void Change_employee_to_hourly(Employee employee, ArrayList<HourlyEmployee> HourlyList, ArrayList<Employee> EmployeeList, ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);

        System.out.println("type "+employee.getName()+"´s hourly wage:");

        double wage = scan.nextDouble();
        scan.nextLine();

        HourlyEmployee new_employee = new HourlyEmployee(employee.getName(), employee.getAddress(), employee.getEmployee_id(), employee.getMoney(), employee.getPayment_method(),1, employee.isIn_syndicate(), wage);
        if(employee.isIn_syndicate()){
            new_employee.setSyndicate_tax(employee.getSyndicate_tax());
            new_employee.setSyndicate_id(employee.getSyndicate_id());
            SyndicateList.remove(employee);
            SyndicateList.add(new_employee);
        }
        HourlyList.add(new_employee);
        EmployeeList.remove(employee);
        EmployeeList.add(new_employee);
    }


    public HourlyEmployee() {
    }

    //- point card(cartao de ponto)
    public void EmployeeCard(ArrayList<HourlyEmployee> HourlyList){
        Scanner scan = new Scanner(System.in);
        System.out.println("Who wants to submit a Point Card?");
        for(int i = 0; i < HourlyList.size();i++){
            System.out.println(i+". "+HourlyList.get(i).getName());
        }
        int index = scan.nextInt();
        scan.nextLine();

        HourlyEmployee newHourly = HourlyList.get(index);

        System.out.println("How many hours did "+newHourly.getName()+" worked today?");
        double hours = scan.nextDouble();
        scan.nextLine();

        if(hours > 8){
            double money = 8 * newHourly.getHourly_wage();
            double extra = hours - 8;
            extra = extra * (newHourly.getHourly_wage() * 1.5);
            money = money + extra;
            System.out.println("R$ "+money+" was added to "+newHourly.getName()+" account");
            money = money + newHourly.getMoney();
            System.out.println(newHourly.getName()+" has R$ "+money+" in his/her account");
            newHourly.setMoney(money);
        }
        else{
            double money = hours * newHourly.getHourly_wage();
            System.out.println("R$ "+money+" was added to "+newHourly.getName()+" account");
            money = money + newHourly.getMoney();
            System.out.println(newHourly.getName()+" has R$ "+money+" in his/her account");
            newHourly.setMoney(money);
        }
        System.out.println("point card submitted with success!");
    }


    public double getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(double hourly_wage) {
        this.hourly_wage = hourly_wage;
    }
}
