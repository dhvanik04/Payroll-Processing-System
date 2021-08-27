package company;

/**
 * This is the Profile class that defines the profile of an employee.
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Profile
{

    /**
     * the name of the employee
     */
    private String name; //employee's name in the form lastname,firstname
    /**
     * the department of the employee
     */
    private String department; //department code: CS, ECE, IT
    /**
     * the dateHired of the employee
     */
    private Date dateHired;

    /**
     * This is the parametrized constructor that initializes the instances variables of an employee
     *
     * @param name       of the employee in the form LastName, firstName
     * @param department the department the employeee works in
     * @param dateHired  the date he/she was hired on
     */
    public Profile(String name, String department, Date dateHired)
    {
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * This method overrides the toString method and returns the profile details of the employee in a desired format
     *
     * @return String - the profile details of the profile in a desired format
     */
    @Override
    public String toString()
    {
        return this.name + "::" + this.department + "::" + this.dateHired;
    }


    /**
     * This is the equals method that checks if two profiles are the same by comparing the name, dateHired and the department of the employee
     *
     * @param obj of the type profile that is being checked for equality
     * @return true if two profiles are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) //compare name, department and dateHired
    {
        if (obj instanceof Profile)
        {
            Profile current = (Profile) obj;
            if (current.name.equals(this.name) && current.department.equals(this.department) && current.dateHired.compareTo(this.dateHired) == 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This is a getter method to return the department of the employee
     *
     * @return the department of the employee
     */
    public String getDepartment()
    {
        return this.department;
    }

    /**
     * This is a getter method to return the dateHired of the employee
     *
     * @return the dateHired of the employee
     */
    public Date getDateHired()
    {
        return this.dateHired;
    }
}

