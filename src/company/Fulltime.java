package company;

/**
 * This is a Fulltime class that extends the Employee class and includes specific data and operations to a full-time employee
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Fulltime extends Employee
{
    /**
     * the final constant for pay period which is 26
     */
    static final int PAY_PERIOD = 26;
    /**
     * a double protected variable annualSalary of the full time employees
     */
    private double annualSalary;

    /**
     * This is a parametrized constructor that calls the super constructor and initializes the instance variables
     *
     * @param profile      of the fulltime employee
     * @param annualSalary of the fulltime employee
     */
    public Fulltime(Profile profile, double annualSalary)
    {
        super(profile);
        this.annualSalary = annualSalary;
    }

    /**
     * This function calculates payments of a fulltime employee
     */
    @Override
    public void calculatePayment()
    {
        super.setPayment(annualSalary / PAY_PERIOD);
    }

    /**
     * This method calls the equals method of the superclass and checks if the object is equal to the fulltime employee
     *
     * @param obj the object that is being compared for equality
     * @return true if the object/ employee is equal to the current fulltime employee, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Fulltime)
            if (super.equals(obj)) // dont compare annual salary
                return true;

        return false;
    }

    /**
     * This function calls the toString of the super class Employee and prints the profile and payment details of the fulltime employee in the desired format
     *
     * @return String the details of the fulltime employee
     */
    @Override
    public String toString()
    {
        return super.toString() + "::FULL TIME::Annual Salary $" + String.format("%,.2f", this.annualSalary);
    }

    /**
     * This is a getter method to return the annual salary of the fulltime employee
     *
     * @return double return the annual salary of the fulltime employee
     */
    public double getAnnualSalary()
    {
        return this.annualSalary;
    }

}
