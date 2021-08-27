package company;

/**
 * This is a Employee class that defines the common data and operations for all employee type; each employee has a profile that uniquely identifies the employee
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Employee
{
    /**
     * profile of type Profile of the Employee
     */
    private Profile profile;
    /**
     * the payment of the employee
     */
    private double payment;

    /**
     * This is the parametrized constructor that initializes the instance variables Profile and payment
     *
     * @param profile of type Profile which has the profile details of the employee
     */
    public Employee(Profile profile)
    {
        this.profile = profile;
        this.payment = 0;
    }

    /**
     * This function initializes the default payment of the employee
     */
    public void calculatePayment()
    {
        this.payment = 0;
    }

    /**
     * This method checks of the profile of an employee is equal to the current profile by overriding the equals method in Profile class
     *
     * @param obj the object that is being compared for equality
     * @return true if the object/ employee is equal to the current employee, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Employee)
        {
            Employee current = (Employee) obj;
            if (current.getProfile().equals(this.profile))
                return true;
        }
        return false;
    }

    /**
     * This function calls the toString method of Profile class and prints the profile details of the employee in the desired format
     *
     * @return String the details of the employee
     */
    @Override
    public String toString()
    {

        return this.profile.toString() + "::Payment $" + String.format("%,.2f", this.payment);
    }

    /**
     * This is a getter method for getting the profile of an employee
     *
     * @return profile
     */
    public Profile getProfile()
    {

        return profile;
    }

    /**
     * This is a getter method for returning the payment of the employee
     *
     * @return double return the payment of the employee
     */
    public double getPayment()
    {
        return this.payment;
    }

    /**
     * This is a setter method for setting the payment of an employee
     *
     * @param payment the payment that we want to set
     */
    public void setPayment(double payment)
    {
        this.payment = payment;
    }

}
