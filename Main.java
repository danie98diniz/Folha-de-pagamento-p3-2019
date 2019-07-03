import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Stack;

public class Main {
//-----------------------------------------------     menu     ---------------------------------------------------------
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<Employee> EmployeeList = new ArrayList<>();
        ArrayList<HourlyEmployee> HourlyList = new ArrayList<>();
        ArrayList<WageEarner> WageEarnerList = new ArrayList<>();
        ArrayList<CommissionedEmployee> CommissionedList = new ArrayList<>();
        ArrayList<Employee> SyndicateList = new ArrayList<>();
        Stack<ArrayList<Employee>> UndoStack = new Stack<>();
        Stack<ArrayList<Employee>> RedoStack = new Stack<>();
        ArrayList<Employee> Null = new ArrayList<>();
        UndoStack.push(Null);

        while(true){

            System.out.println("type 0 to exit");
            System.out.println("type 1 to register a new employee");
            System.out.println("type 2 to remove a employee");
            System.out.println("type 3 to change employee info");
            System.out.println("type 4 to submit a point card(cartao de ponto)");
            System.out.println("type 5 to submit a purchase ticket(resultado de venda)");
            System.out.println("type 6 to submit a service tax(taxa de servico)");

            int option = scan.nextInt();
            scan.nextLine();

            if(option == 0){
                break;
            }
            else if(option == 1){
                Employee newEmployee = new Employee();
                newEmployee.Create_new_employee(EmployeeList, HourlyList, WageEarnerList, CommissionedList, SyndicateList);
            }//register an employee
            else if(option == 2){
                Employee newEmployee = new Employee();
                newEmployee.Remove_a_employee(EmployeeList, HourlyList, WageEarnerList, CommissionedList, SyndicateList);
            }//removes an employee
            else if(option == 3){
                Employee newEmployee = new Employee();
                newEmployee.Change_employee_info(EmployeeList, HourlyList, WageEarnerList, CommissionedList, SyndicateList);
            }//changes employee info
            else if(option == 4){
                HourlyEmployee newHourly = new HourlyEmployee();
                newHourly.EmployeeCard(HourlyList);
            }//submit a point card
            else if (option == 5){
                CommissionedEmployee newCommissioned = new CommissionedEmployee();
                    newCommissioned.PurchaseTicket(CommissionedList);
            }//submit a purchase ticket
            else if(option == 6){
                Employee newEmployee = new Employee();
                newEmployee.ServiceTax(SyndicateList);
            }//submit a service tax

        }
    }
}
