package company;

import java.util.Calendar;

/**
 * This is the date class that implements the Java Interface
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */

public class Date implements Comparable<Date>
{
    /**
     * a constant to help check for leap year
     */
    public static final int QUADRENNIAL = 4;
    /**
     * a constant to help check for leap year
     */
    public static final int CENTENNIAL = 100;
    /**
     * a constant to help check for leap year
     */
    public static final int QUATERCENTENNIAL = 400;
    /**
     * this is the minimum year that is acceptable as outlined in the project instructions
     */
    public static final int MINYEAR = 1900;
    /**
     * this is the maximum year that is acceptable as outlined in the project instructions
     */
    public static final int MAXYEAR = 2021;
    /**
     * there is no 13th(or larger) month in a year
     */
    public static final int MAXMONTH = 12;
    /**
     * there is no 0th(or smaller) month in a year
     */
    public static final int MINMONTH = 1;
    /**
     * setting the numbers to their corresponding months
     */
    public static final int JAN = 1, FEB = 2, MAR = 3, APR = 4, MAY = 5, JUN = 6, JUL = 7, AUG = 8, SEP = 9, OCT = 10, NOV = 11,
            DEC = 12;
    /**
     * number of days possible in non-leap year months
     */
    public static final int[] NUMDAYS = {28, 30, 31, 29};
    /**
     * instance variable to store the year
     */
    private int year;
    /**
     * instance variable to store the month
     */
    private int month;
    /**
     * instance variable to store the year
     */
    private int day;

    /**
     * This is a constructor for the Date class that creates an instance with a
     * specific date that is passed in
     *
     * @param date of a specific day of the Book
     */
    public Date(String date) // taking mm/dd/yyyy and create a Date object
    {
        String[] dateArray = date.split("-");
        month = Integer.parseInt(dateArray[1]);
        day = Integer.parseInt(dateArray[2]);
        year = Integer.parseInt(dateArray[0]);
    }

    /**
     * Default Constructor that creates an instance with today's date as the values
     * for month, day, and year
     */
    public Date() // return today's date
    {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Compares 2 dates to see if they are the same or not.
     *
     * @param date - the date that needs to be compared to
     * @return 0 if the dates are the same, 1 if date param is smaller, and -1 if
     * date param is bigger
     */
    @Override
    public int compareTo(Date date)
    {
        if (this.getMonth() == date.getMonth() && this.getDay() == date.getDay() && this.getYear() == date.getYear())
            return 0;
        else if (this.getYear() > date.getYear()
                || (this.getYear() == date.getYear() && this.getMonth() > date.getMonth())
                || (this.getYear() == date.getYear() && this.getMonth() == date.getMonth()
                && this.getDay() > date.getDay()))
            return 1;
        else
            return -1;
    } // return 1, 0, or -1

    /**
     * Checks to ensure that the date in question is a valid day of the year between
     * the years 1900 to present
     *
     * @return true if this is a real possible day and false otherwise
     */
    public boolean isValid()
    {

        if (this.year < MINYEAR || this.year > MAXYEAR || this.month > MAXMONTH || this.month < MINMONTH)
            return false;

        // check to make sure the date isn't a day after today
        Date today = new Date();
        if (this.year == MAXYEAR && ((this.month == today.month && this.day > today.day) || this.month > today.month))
            return false;

        // first if statement is the leap year check:
        if ((this.year % QUADRENNIAL == 0 && this.year % CENTENNIAL != 0)
                || (this.year % QUADRENNIAL == 0 && this.year % CENTENNIAL == 0 && this.year % QUATERCENTENNIAL == 0))
        {
            return this.month != FEB || (this.day >= 0 && this.day <= NUMDAYS[3]);
        }
        else if (this.month == FEB)
        {
            return this.day <= NUMDAYS[0] && this.day >= 0;
        }
        else if (this.month == JAN || this.month == MAR || this.month == MAY || this.month == JUL || this.month == AUG
                || this.month == OCT || this.month == DEC)
        {
            return this.day <= NUMDAYS[2] && this.day >= 0;
        }
        else if (this.month == APR || this.month == JUN || this.month == SEP || this.month == NOV)
        {
            return this.day <= NUMDAYS[1] && this.day >= 0;
        }
        return true;
    }

    /**
     * Prints the specific date of this instance in the general format mm/dd/yyyy
     */
    @Override
    public String toString()
    {
        return "" + this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Gets the year of the specific instance
     *
     * @return the integer value of the year of this instance of Date
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Gets the month of the specific instance
     *
     * @return the integer value of the month of this instance of Date
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Gets the day of the specific instance
     *
     * @return the integer value of the day of this instance of Date
     */
    public int getDay()
    {
        return day;
    }

}
