package company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is an array-based container class that implements the employee database.
 * The Company will consist of many different types of Employees with functions such as remove,add, calculatePayment, etc.
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */

public class Company
{

    /**
     * final int variable to store -1
     */
    static final int NOTFOUND = -1;
    /**
     * an array of employees
     */
    private Employee[] emplist;
    /**
     * the number of employees
     */
    private int numEmployees;

    /**
     * Constructor that creates an initial array of 4 to store Employees of the company
     */
    public Company()
    {
        numEmployees = 0;
        int initialCapacity = 4; // the initial Capacity is 4 books
        emplist = new Employee[initialCapacity]; // the initial Capacity of the 'library' is 4 books
    }

    /**
     * Finds the position of the given book in the array of all the books in the library
     *
     * @param employee - the employee that needs to be found
     * @return the int value of the position of the employee in the Company array
     */
    private int find(Employee employee)
    {
        for (int i = 0; i < numEmployees; i++)
        {
            if (employee.equals(emplist[i]))
            {
                return i;
            }
        }
        return NOTFOUND;
    }


    /**
     * This is the helper method to grow the capacity of the company by 4 when the array is full
     */
    private void grow()
    {
        int growByCapacity = 4;
        int initialEmpArrayLength = emplist.length;
        Employee[] newEmpList = new Employee[initialEmpArrayLength + growByCapacity];
        int x = 0;
        while (x < initialEmpArrayLength && emplist[x] != null)
        {
            newEmpList[x] = emplist[x];
            x++;
        }
        emplist = newEmpList;
    }

    /**
     * This adds a new employee to the 'company'
     *
     * @param employee - the new employee that is too be added the emplist array
     * @return true if there is no such employee already in existence and it was added, false otherwise
     */
    public boolean add(Employee employee) //check the profile before adding
    {
        for (int i = 0; i < numEmployees; i++)
        {
            if (emplist[i].equals(employee))
            {
                return false;
            }
        }

        if (emplist[emplist.length - 1] == null)
        {
            emplist[numEmployees] = employee;
        }
        else
        {
            grow();
            add(employee);
            numEmployees = numEmployees - 1;
        }
        numEmployees = numEmployees + 1;
        return true;
    }

    /**
     * Removes a specific employee from the 'company' in emplist
     *
     * @param employee - the employee that is to be found and removed from the emplist array
     * @return true if the employee was found and successfully removed, and false otherwise
     */
    public boolean remove(Employee employee) //maintain the original sequence
    {
        Employee[] newEmpList = new Employee[emplist.length];
        int index = find(employee);
        if (index == NOTFOUND)
        {
            return false;
        }

        int positionInNewEmpList = 0;
        for (int i = 0; i < emplist.length; i++)
        {
            if (i == index)
            {
                i = i + 1;
            }
            newEmpList[positionInNewEmpList] = emplist[i];
            positionInNewEmpList++;
        }
        emplist = newEmpList;
        numEmployees = numEmployees - 1;
        return true;
    }

    /**
     * Sets the working hours for a parttime employee
     *
     * @param employee that the hours are being set for
     * @return true if the employee was found and the hours were set successfully, and false otherwise
     */
    public boolean setHours(Employee employee)
    {
        int i = find(employee);
        if (i != NOTFOUND)
        {
            if (employee.equals(emplist[i]) && emplist[i] instanceof Parttime)
            {
                int hours = ((Parttime) employee).getHoursWorked();
                ((Parttime) emplist[i]).setHoursWorked(hours);
                return true;
            }
        }
        return false;
    }

    /**
     * Process payments for all employees
     */
    public void processPayments()
    {
        for (int i = 0; i < numEmployees; i++)
        {
            emplist[i].calculatePayment();
        }
    }

    /**
     * Organizes earning statements by department to print in message area in GUI
     */
    public void printByDepartment()
    {
        for (int i = 0; i < numEmployees - 1; i++)
        {
            for (int j = 0; j < numEmployees - i - 1; j++)
            {
                if (emplist[j].getProfile().getDepartment().compareTo(emplist[j + 1].getProfile().getDepartment()) > 0)
                {
                    Employee temp = emplist[j];
                    emplist[j] = emplist[j + 1];
                    emplist[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Organizes earning statements by date hired to print in message area in GUI
     */
    public void printByDate()
    {
        for (int i = 0; i < numEmployees - 1; i++)
        {
            for (int j = 0; j < numEmployees - i - 1; j++)
            {
                if (emplist[j].getProfile().getDateHired().compareTo(emplist[j + 1].getProfile().getDateHired()) > 0)
                {
                    Employee temp = emplist[j];
                    emplist[j] = emplist[j + 1];
                    emplist[j + 1] = temp;
                }
            }
        }
    }

    /**
     * The number of employees that are in the 'company'
     *
     * @return int - the number of employees currently in emplist
     */
    public int getNumEmployees()
    {
        return numEmployees;
    }

    /**
     * Getter method for getting the employee array
     *
     * @return the employee array
     */
    public Employee[] getEmplist()
    {
        return emplist;
    }

    /**
     * Exports the information of the employees in the database to a file
     * @param targetFile is the file that the employees information is to be written to
     * @throws IOException for error in file location/writing
     */
    public void exportDatabase(File targetFile) throws IOException
    {
        FileWriter writer = new FileWriter(targetFile);
        for (int i = 0; i < getNumEmployees(); i++) //works but is this the format she wants it in???
        {
            writer.append(emplist[i].toString() + "\n");
        }
        writer.close();
    }

}
