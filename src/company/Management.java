package company;

/**
 * This is a Management class that extends the Fulltime class and includes specific data and operations to a fulltime employee with a management role
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Management extends Fulltime
{
    /**
     * the final variable for compensation for manager
     */
    static final double MANAGER = 5000.00;
    /**
     * the final variable for compensation for deoartment head
     */
    static final double DEPTHEAD = 9500.00;
    /**
     * the final variable for compensation for director
     */
    static final double DIRECTOR = 12000.00;
    /**
     * the private variable for calculating manager compensation
     */
    private double managerComp;
    /**
     * the private variable for storing the management code
     */
    private int code;

    /**
     * This is the parametrized constructor that initializes the instance variables profile, annualSalary, code of the management role
     *
     * @param profile      of the Manager/ Depthead or director
     * @param annualSalary of the person with management role
     * @param code         the code determining which management role it is: 1 for manager, 2 for department head and 3 for director
     */
    public Management(Profile profile, double annualSalary, int code)
    {
        super(profile, annualSalary);
        this.code = code;
        if (this.code == 1)
        {
            this.managerComp = (MANAGER / PAY_PERIOD);
        }
        else if (this.code == 2)
        {
            this.managerComp = (DEPTHEAD / PAY_PERIOD);
        }
        else if (this.code == 3)
        {
            this.managerComp = (DIRECTOR / PAY_PERIOD);
        }
    }

    /**
     * This method calculates the payment of the management role person
     */
    @Override
    public void calculatePayment()
    {
        double payment = (super.getAnnualSalary() / PAY_PERIOD) + this.managerComp;
        super.setPayment(payment);
    }

    /**
     * This method calls the equals method of the superclass and checks if the object is equal to the management employee
     *
     * @param obj the object that is being compared for equality
     * @return true if the object/ employee is equal to the current management employee
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Management)
            if (super.equals(obj)) // do we check for same management code???
                return true;

        return false;
    }

    /**
     * This function calls the toString of the super class Fulltime Employee and prints the profile and payment details of the management employee depending on the code
     *
     * @return String the details of the management employee
     **/
    @Override
    public String toString()
    {
        String print = super.toString();
        if (this.code == 1)
            return print + "::Manager Compensation $" + String.format("%,.2f", this.managerComp);
        else if (this.code == 2)
            return print + "::Department Head Compensation $" + String.format("%,.2f", this.managerComp);
        else if (this.code == 3)
            return print + "::Director Compensation $" + String.format("%,.2f", this.managerComp);
        else
            return "ERROR";
    }

}
