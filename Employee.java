import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Employee{

    private String name;
    private String address;
    private int employee_id;
    private double money;
    private String payment_method;
    private int employee_type;
    private boolean in_syndicate;
    private int syndicate_tax;
    private int syndicate_id;

    //- Create employee
    public void Create_new_employee(ArrayList<Employee> EmployeeList, ArrayList<HourlyEmployee> HourlyList, ArrayList<WageEarner> WageEarnerList, ArrayList<CommissionedEmployee> CommissionedList, ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);


        String name;
        String address;
        String payment_method;
        int id;
        int employee_type;
        boolean in_syndicate = false;

        Generate_id:
        while(true){
            Random rand = new Random();
            int n = rand.nextInt(1000) + 1;
            for(int i = 0; i < EmployeeList.size(); i++){
                if(n == EmployeeList.get(i).employee_id){
                    continue Generate_id;
                }
            }
            id = n;
            break;
        }

        System.out.println("please type the name of the employee:");
        name = scan.nextLine();
        System.out.println("please type the employee´s address:");
        address = scan.nextLine();
        System.out.println("How your worker would like to receive his/her payment?");
        System.out.println("type 1 receive a check delivered by mail");
        System.out.println("type 2 to receive a check delivered directly to the worker");
        System.out.println("type 3 to receive deposit into worker´s bank account");
        int option = scan.nextInt();
        scan.nextLine();

        if(option == 1){
            payment_method = "check by mail";
        }
        else if(option == 2){
            payment_method = "check to worker";
        }
        else{
            payment_method = "deposit to bank account";
        }

        System.out.println("is the employee in the syndicate? (Y/N)");
        String answer = scan.nextLine();
        int option2 = 0;
        int syndicate_id = 0;

        if(answer.equals("Y") || answer.equals("y")){
            in_syndicate = true;
            System.out.println("type the syndicate tax (in %):");
            option2 = scan.nextInt();
            scan.nextLine();

            Generate_id:
            while(true){
                Random rand = new Random();
                int n = rand.nextInt(1000) + 1;
                for(int i = 0; i < SyndicateList.size(); i++){
                    if(n == SyndicateList.get(i).employee_id){
                        continue Generate_id;
                    }
                }
                syndicate_id = n;
                break;
            }
        }

        System.out.println("type 1 if the employee is an hourly employee (horista)");
        System.out.println("type 2 if the employee is a wage earner (assalariado)");
        System.out.println("type 3 if the employee is a commissioned wage earner ( assalariado comissionado)");
        option = scan.nextInt();
        scan.nextLine();

        if(option == 1){
            employee_type = 1;
            double value_per_hour;
            System.out.println("type the value per hour that the worker will earn");
            value_per_hour = scan.nextDouble();
            scan.nextLine();

            HourlyEmployee new_employee = new HourlyEmployee(name, address, id, 0, payment_method, employee_type, in_syndicate, value_per_hour);

            if(in_syndicate){
                new_employee.setSyndicate_tax(option2);
                SyndicateList.add(new_employee);
                new_employee.setSyndicate_id(syndicate_id);
            }

            System.out.println("Employee registered with success!");
            EmployeeList.add(new_employee);
            HourlyList.add(new_employee);

        }//register a hourly employee
        else if(option == 2){
            employee_type = 2;
            double wage;

            System.out.println("type the wage of the employee:");
            wage = scan.nextDouble();
            scan.nextLine();

            WageEarner new_employee = new WageEarner(name, address, id, 0, payment_method, employee_type,in_syndicate, wage);

            if(in_syndicate){
                new_employee.setSyndicate_tax(option2);
            }

            System.out.println("Employee registered with success!");
            EmployeeList.add(new_employee);
            WageEarnerList.add(new_employee);

        }//register a wage earner
        else if(option == 3){
            employee_type = 3;
            int commission;
            double wage;
            System.out.println("type the wage of the employee:");
            wage = scan.nextDouble();
            scan.nextLine();

            System.out.println("type the commission (int %) of the commissioned wage earner");
            commission = scan.nextInt();
            scan.nextLine();

            CommissionedEmployee new_employee = new CommissionedEmployee(name, address, id, 0, payment_method, employee_type,in_syndicate, wage, commission);

            if(in_syndicate){
                new_employee.setSyndicate_tax(option2);
            }

            EmployeeList.add(new_employee);
            CommissionedList.add(new_employee);

            System.out.println("Employee registered with success!");

        }//register a commissioned wage earner
    }//register a new employee

    //- remove employee
    public void Remove_a_employee(ArrayList<Employee> EmployeeList, ArrayList<HourlyEmployee> HourlyList, ArrayList<WageEarner> WageEarnerList, ArrayList<CommissionedEmployee> CommissionedList, ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);
        int employee_index;
        int id;
        String name;

        System.out.println("choose a employee to remove or type '-1' to return:");
        for(int i = 0; i < EmployeeList.size(); i++){
            System.out.println(i+". "+EmployeeList.get(i).getName());
        }
        employee_index = scan.nextInt();
        scan.nextLine();
        if(employee_index == -1){
            return;
        }
        Employee newEmployee = EmployeeList.get(employee_index);
        id = newEmployee.getEmployee_id();
        name = newEmployee.getName();

        if(newEmployee.getEmployee_type() == 1){
            for(int i = 0; i < HourlyList.size(); i++){
                if(id == HourlyList.get(i).getEmployee_id()){
                    HourlyList.remove(i);
                    break;
                }
            }
        }//removes a hourly employee
        else if(newEmployee.getEmployee_type() == 2){
            for(int i = 0; i < WageEarnerList.size(); i++){
                if(id == WageEarnerList.get(i).getEmployee_id()){
                    WageEarnerList.remove(i);
                    break;
                }
            }
        }//removes a wage earner employee
        else{
            for(int i =0; i < CommissionedList.size(); i++){
                if(id == CommissionedList.get(i).getEmployee_id()){
                    CommissionedList.remove(i);
                    break;
                }
            }
        }//removes a commissioned wage earner employee

        if(newEmployee.isIn_syndicate()){
            SyndicateList.remove(newEmployee);
        }//removes from syndicate list
        EmployeeList.remove(newEmployee);//removes from general list
        System.out.println("Employee "+name+" was removed with success!");

    }

    //- change info
    public void Change_employee_info(ArrayList<Employee> EmployeeList, ArrayList<HourlyEmployee> HourlyList, ArrayList<WageEarner> WageEarnerList, ArrayList<CommissionedEmployee> CommissionedList, ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);


        System.out.println("change info of:");
        for(int i = 0; i < EmployeeList.size(); i++){
            System.out.println(i+". "+EmployeeList.get(i).getName());
        }
        int index = scan.nextInt();
        scan.nextLine();

        Employee newEmployee = EmployeeList.get(index);

        int option;


            System.out.println(" - choose info to change (type -1 to go back) - ");
            System.out.println(newEmployee.getName()+"´s info:");
            System.out.println("1.Name: "+newEmployee.getName());
            System.out.println("2.Address: "+newEmployee.getAddress());
            System.out.println("3.Payment method: "+newEmployee.getPayment_method());
            if(newEmployee.isIn_syndicate()){
                System.out.println("4.Syndicate id: "+newEmployee.getSyndicate_id()+" (tax deducted from employee: "+newEmployee.getSyndicate_tax()+"%)");
            }
            else{
                System.out.println("4.Syndicate: is not in the syndicate");
            }
            //employee type
            if(newEmployee.getEmployee_type() == 1){
                System.out.println("5.Employee type: Hourly Employee");
            }
            else if(newEmployee.getEmployee_type() == 2){
                System.out.println("5.Employee type: Wage Earner Employee");
            }
            else{
                System.out.println("5.Employee type: Commissioned Wage Earner Employee");
            }
            option = scan.nextInt();
            scan.nextLine();

            if(option == -1){
                return;
            }//return
            else if(option == 1){
                System.out.println("please type "+newEmployee.getName()+" new name:");
                String new_name = scan.nextLine();
                newEmployee.setName(new_name);
            }//changes name
            else if(option == 2){
                System.out.println("please type "+newEmployee.getName()+" new address:");
                String new_Address = scan.nextLine();
                newEmployee.setAddress(new_Address);
            }//changes address
            else if(option == 3){
                String payment_method;
                System.out.println("How your worker would like to receive his/her payment?");
                System.out.println("type 1 receive a check delivered by mail");
                System.out.println("type 2 to receive a check delivered directly to the worker");
                System.out.println("type 3 to receive deposit into worker´s bank account");
                int option2 = scan.nextInt();
                scan.nextLine();

                if(option2 == 1){
                    payment_method = "check by mail";
                }
                else if(option2 == 2){
                    payment_method = "check to worker";
                }
                else{
                    payment_method = "deposit to bank account";
                }
                newEmployee.setPayment_method(payment_method);
            }//changes payment method
            else if(option == 4){
                if(newEmployee.isIn_syndicate()){
                    System.out.println("type 1 to remove "+newEmployee.getName()+" from the syndicate");
                    System.out.println("type 2 to change "+newEmployee.getName()+"´s syndicate tax (current "+newEmployee.getSyndicate_tax()+" ):");
                    System.out.println("type 3 to change employee´s syndicate id (current "+newEmployee.getSyndicate_id()+" ):");
                    int choice = scan.nextInt();
                    scan.nextLine();
                    if(choice == 1){
                        newEmployee.setIn_syndicate(false);
                        newEmployee.setSyndicate_tax(0);
                        SyndicateList.remove(newEmployee);
                        System.out.println("employee removed from the syndicate");
                    }
                    else if(choice == 2){
                        System.out.println("type the amount of the syndicate tax (in %):");
                        int amount = scan.nextInt();
                        scan.nextLine();
                        newEmployee.setSyndicate_tax(amount);
                        System.out.println("amount updated");
                    }
                    else if (choice == 3){
                        //change employee syndicate id to a unique one
                        int flag = 0;
                        int id = 0;
                        syndicateid:
                        while (flag == 0){
                            System.out.println(" please type a new unique syndicate id:");
                            id = scan.nextInt();
                            scan.nextLine();
                            for(int i = 0; i < SyndicateList.size(); i++){
                                if(SyndicateList.get(i).getSyndicate_id() == id){
                                    System.out.println("the id you want is already taken!");
                                    continue syndicateid;
                                }
                            }
                            System.out.println("your syndicate id is now: "+id);
                            break;
                        }
                        newEmployee.setSyndicate_id(id);
                    }
                }
                else{
                    System.out.println("do "+newEmployee.name+" would like to join the syndicate? (y/n)");
                    String answer = scan.nextLine();
                    if(answer.equals("Y") || answer.equals("y")){
                        newEmployee.setIn_syndicate(true);
                        System.out.println("type the syndicate tax (in %):");
                        int tax = scan.nextInt();
                        scan.nextLine();
                        newEmployee.setSyndicate_tax(tax);
                        SyndicateList.add(newEmployee);
                        Generate_id:
                        while(true){
                            Random rand = new Random();
                            int n = rand.nextInt(1000) + 1;
                            for(int i = 0; i < SyndicateList.size(); i++){
                                if(n == SyndicateList.get(i).employee_id){
                                    continue Generate_id;
                                }
                            }
                            newEmployee.setSyndicate_id(n);
                            break;
                        }
                    }
                }
            }//changes syndicate options
            else if(option == 5){
                //removes employee from his specific list since its type will change and call function to change his type
                if(newEmployee.getEmployee_type() == 1){
                    HourlyList.remove(newEmployee);
                    System.out.println("type 1 to change the employee to a wage earner (assalariado)");
                    System.out.println("type 2 to change the employee to a commissioned wage earner ( assalariado comissionado)");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == 1){
                        WageEarner newWageEarner = new WageEarner();
                        newWageEarner.Change_employee_to_wage_earner(newEmployee, WageEarnerList, EmployeeList, SyndicateList);
                        System.out.println("changes made");
                        return;
                    }
                    else if(option == 2){
                        CommissionedEmployee newCommissioned = new CommissionedEmployee();
                        newCommissioned.Change_employee_to_commissioned(newEmployee, CommissionedList, EmployeeList, SyndicateList);
                        System.out.println("changes made");
                        return;
                    }
                }
                else if(newEmployee.getEmployee_type() == 2){
                    WageEarnerList.remove(newEmployee);
                    System.out.println("type 1 to change the employee to a hourly employee (horista)");
                    System.out.println("type 2 to change the employee to a commissioned wage earner ( assalariado comissionado)");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == 1){
                        HourlyEmployee newHourly = new HourlyEmployee();
                        newHourly.Change_employee_to_hourly(newEmployee, HourlyList, EmployeeList, SyndicateList);
                        System.out.println("changes made");
                        return;
                    }
                    else if(option == 2){
                        CommissionedEmployee newCommissioned = new CommissionedEmployee();
                        newCommissioned.Change_employee_to_commissioned(newEmployee, CommissionedList, EmployeeList, SyndicateList);
                        System.out.println("changes made");
                        return;
                    }

                }
                else{
                    CommissionedList.remove(newEmployee);
                    System.out.println("type 1 to the employee to a hourly employee (horista)");
                    System.out.println("type 2 to change the employee to a wage earner (assalariado)");
                    option = scan.nextInt();
                    scan.nextLine();
                    if(option == 1){
                        HourlyEmployee newHourly = new HourlyEmployee();
                        newHourly.Change_employee_to_hourly(newEmployee, HourlyList, EmployeeList, SyndicateList);
                        System.out.println("changes made");
                        return;
                    }
                    else if(option == 2){
                        WageEarner newWageEarner = new WageEarner();
                        newWageEarner.Change_employee_to_wage_earner(newEmployee, WageEarnerList, EmployeeList, SyndicateList);
                        System.out.println("changes made");
                        return;
                    }
                }
            }//changes employee type

    }

    //- Service tax (taxa de servico)
    public void ServiceTax(ArrayList<Employee> SyndicateList){
        Scanner scan = new Scanner(System.in);

        System.out.println("who do you like to tax from the syndicate?");

        for (int i = 0; i < SyndicateList.size(); i++){
            System.out.println(i+". "+SyndicateList.get(i).getName());
        }
        int index = scan.nextInt();
        scan.nextLine();

        Employee newEmployee = SyndicateList.get(index);

        System.out.println("how much do you like to tax "+newEmployee.getName()+"? (in R$)");
        double tax = scan.nextDouble();
        scan.nextLine();
        System.out.println("R$ "+tax+"will be deducted from "+newEmployee.getName()+" next salary");
        tax = newEmployee.money - tax;
        newEmployee.setMoney(tax);
    }

    public int getSyndicate_tax() {
        return syndicate_tax;
    }

    public int getSyndicate_id() {
        return syndicate_id;
    }

    public void setSyndicate_id(int syndicate_id) {
        this.syndicate_id = syndicate_id;
    }

    public void setSyndicate_tax(int syndicate_tax) {
        this.syndicate_tax = syndicate_tax;
    }

    public Employee(String name, String address, int employee_id, double money, String payment_method, int employee_type, boolean in_syndicate) {
        this.name = name;
        this.address = address;
        this.employee_id = employee_id;
        this.money = money;
        this.payment_method = payment_method;
        this.employee_type = employee_type;
        this.in_syndicate = in_syndicate;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_type(int employee_type) {
        this.employee_type = employee_type;
    }

    public Employee() {
    }

    public boolean isIn_syndicate() {
        return in_syndicate;
    }

    public void setIn_syndicate(boolean in_syndicate) {
        this.in_syndicate = in_syndicate;
    }

    public int getEmployee_type() {
        return employee_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
