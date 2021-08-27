package company;

/**
 * This is a Parttime class that extends the Employee class
 * It includes specific data and operations to a parttime employee in the company
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */

public class Parttime extends Employee
{
    /**
     * the hours worked by the parttime employee which is a protected int variable
     */
    private int hoursWorked;
    /**
     * the hourly rate of the parttime employee which is a protected double variable
     */
    private double hourlyRate;

    /**
     * A constructor for initializing a parttime employee with their hourly pay rate and their profile
     *
     * @param hourlyRate - the pay rate per hour of the parttime employee
     * @param profile    of the parttime employee
     */
    public Parttime(Profile profile, double hourlyRate)
    {
        super(profile);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }

    /**
     * A constructor for initiaizing a parttime employee with their number of hours worked and their profile
     *
     * @param hoursWorked for that parttime employee
     * @param profile     of the parttime employee
     */
    public Parttime(Profile profile, int hoursWorked)
    {
        super(profile);
        this.hourlyRate = 0;
        this.hoursWorked = hoursWorked;
    }

    /**
     * This method calculates the payment of a parttime employee
     */
    @Override
    public void calculatePayment()
    {
        if (this.hoursWorked > 80 && this.hoursWorked <= 100)
            super.setPayment(this.hourlyRate * 80 + (this.hoursWorked - 80) * (this.hourlyRate * 1.5));
        else
            super.setPayment(this.hoursWorked * this.hourlyRate);
    }

    /**
     * This method calls the equals method of the superclass and checks if the object is equal to the parttime employee
     *
     * @param obj the object that is being compared for equality
     * @return true if the object/ employee is equal to the current parttime employee
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Parttime)
            if (super.equals(obj)) // dont compare hourly rate
                return true;

        return false;
    }

    /**
     * This function calls the toString of the super class Employee and prints the profile and payment details of the parttime employee with the rate and hours worked
     *
     * @return String the details of the parttime employee
     **/
    @Override
    public String toString()
    {
        return super.toString() + "::PART TIME::Hourly Rate $" + String.format("%,.2f", this.hourlyRate) + "::Hours worked on this period: " + this.hoursWorked;
    }

    /**
     * Gets the hours worked for the parttime employee
     *
     * @return the integer number of hours worked
     */
    public int getHoursWorked()
    {
        return this.hoursWorked;
    }

    /**
     * This is the setter method to set hours worked by the parttime employee
     *
     * @param hours the hours worked by the parttime employee
     */
    public void setHoursWorked(int hours)
    {
        this.hoursWorked = hours;
    }
}
