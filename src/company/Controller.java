package company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This is the controller class for FXML
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Controller
{

    /**
     * textfield for name
     */
    @FXML
    private TextField nameField;

    /**
     * date picker for date hired
     */
    @FXML
    private DatePicker datePicker;

    /**
     * radio button for choosing CS
     */
    @FXML
    private RadioButton csDept;

    /**
     * toggle group for choosing department type so only one option chosen at a time
     */
    @FXML
    private ToggleGroup dept;

    /**
     * radio button for choosing ECE
     */
    @FXML
    private RadioButton eceDept;

    /**
     * radio button for choosing IT
     */
    @FXML
    private RadioButton itDept;

    /**
     * radio button for choosing fulltime employee type
     */
    @FXML
    private RadioButton fulltime;

    /**
     * toggle group for choosing employee type so only one option chosen at a time
     */
    @FXML
    private ToggleGroup emptype;

    /**
     * radio button for choosing management employee type
     */
    @FXML
    private RadioButton management;

    /**
     * radio button for choosing parttime employee type
     */
    @FXML
    private RadioButton parttime;

    /**
     * text field for entering annual salary for fulltime
     */
    @FXML
    private TextField annualSalary;

    /**
     * text field for entering hours worked for parttime
     */
    @FXML
    private TextField hoursWorked;

    /**
     * text field for entering rate for parttime
     */
    @FXML
    private TextField rate;

    /**
     * radio button for selecting manager
     */
    @FXML
    private RadioButton manager;

    /**
     * toggle group for selecting management type so only one option chosen at a time
     */
    @FXML
    private ToggleGroup managetype;

    /**
     * radio button for selecting department head
     */
    @FXML
    private RadioButton deptHead;

    /**
     * radio button for selecting director
     */
    @FXML
    private RadioButton director;

    /**
     * text area for showing output
     */
    @FXML
    private TextArea messageArea;


    /**
     * Instance of company that will hold employee information
     */
    Company company = new Company();


    /**
     * This functoionis for disabling hours worked and rate when fulltime is selected
     *
     * @param event action of type MouseEvent triggered by clicking on Fulltime employee type
     */
    @FXML
    void disableOptionsFulltime(MouseEvent event)
    {
        annualSalary.setDisable(false);
        rate.setDisable(true);
        hoursWorked.setDisable(true);
        manager.setDisable(true);
        deptHead.setDisable(true);
        director.setDisable(true);
    }

    /**
     * This function is for disabling hours worked and rate when management is selected
     *
     * @param event of type MouseEvent action triggered by selecting management employee type
     */
    @FXML
    void disableOptionsManagement(MouseEvent event)
    {
        annualSalary.setDisable(false);
        rate.setDisable(true);
        hoursWorked.setDisable(true);
        manager.setDisable(false);
        deptHead.setDisable(false);
        director.setDisable(false);
    }

    /**
     * This function is for disabling annual salary when management is selected
     *
     * @param event of type MouseEvent action triggered by selecting parttime employee type
     */
    @FXML
    void disableOptionsParttime(MouseEvent event)
    {
        annualSalary.setDisable(true);
        rate.setDisable(false);
        hoursWorked.setDisable(false);
        manager.setDisable(true);
        deptHead.setDisable(true);
        director.setDisable(true);
    }

    /**
     * Adds an employee to the employee list
     *
     * @param event of type ActionEvent action triggered by clicking the add employee button
     */
    @FXML
    void add(ActionEvent event)
    {
        try
        {
            String name = nameField.getText();
            if (name.length() == 0)
            {
                messageArea.appendText("Please enter a name.\n");
                return;
            }
            RadioButton selectedRadioButton = (RadioButton) dept.getSelectedToggle();
            String department = selectedRadioButton.getText();
            String date = String.valueOf(datePicker.getValue());
            Date dateHired = new Date(date);
            if (!dateHired.isValid())
            {
                messageArea.appendText("Please select a valid date \n");
                return;
            }
            Profile profile = new Profile(name, department, dateHired);
            if (parttime.isSelected())
            {

                double hourlyRate = Double.parseDouble(rate.getText());
                if (hourlyRate < 0)
                {
                    messageArea.appendText("Pay rate cannot be negative.\n");
                    return;
                }
                Employee partTime = new Parttime(profile, hourlyRate);
                if (company.add(partTime))
                {
                    messageArea.appendText("Employee added\n");
                }
                else
                {
                    messageArea.appendText("Employee is already in the list\n");
                }
            }
            else if (fulltime.isSelected())
            {

                double annualSal = Double.parseDouble(annualSalary.getText());
                if (annualSal < 0)
                {
                    messageArea.appendText("Annual Salary cannot be negative.\n");
                    return;
                }
                Employee fullTime = new Fulltime(profile, annualSal);
                if (company.add(fullTime))
                {
                    messageArea.appendText("Employee added\n");
                }
                else
                {
                    messageArea.appendText("Employee is already in the list\n");
                }
            }
            else if (management.isSelected())
            {

                double annualSal = Double.parseDouble(annualSalary.getText());
                if (annualSal < 0)
                {
                    messageArea.appendText("Annual Salary cannot be negative.\n");
                    return;
                }
                int code = 0;
                RadioButton selectedManagementButton = (RadioButton) managetype.getSelectedToggle();
                String managementType = selectedManagementButton.getText();
                if (managementType.equals("Manager"))
                {
                    code = 1;
                }
                else if (managementType.equals("Department Head"))
                {
                    code = 2;
                }
                else if (managementType.equals("Director"))
                {
                    code = 3;
                }

                Employee manageMent = new Management(profile, annualSal, code);
                if (company.add(manageMent))
                {
                    messageArea.appendText("Employee added\n");
                }
                else
                {
                    messageArea.appendText("Employee is already in the list\n");
                }
            }
            else
            {
                messageArea.appendText("Please enter employee type\n");
                return;
            }
        }
        catch (NumberFormatException e)
        {
            messageArea.appendText("Please enter integers.\n");
            return;
        }
        catch (NullPointerException e)
        {
            messageArea.appendText("Please provide all values necessary.\n");
            return;
        }
        catch (RuntimeException e)
        {
            messageArea.appendText("Please select a date.\n");
            return;
        }
        clear(new ActionEvent());
    }

    /**
     * Removes an employee from the database
     *
     * @param event of type ActionEvent action triggered by clicking the remove employee button
     */
    @FXML
    void remove(ActionEvent event)
    {
        try
        {
            String name = nameField.getText();
            if (name.length() == 0)
            {
                messageArea.appendText("Please enter a name.\n");
                return;
            }
            RadioButton selectedRadioButton = (RadioButton) dept.getSelectedToggle();
            String department = selectedRadioButton.getText();
            String date = String.valueOf(datePicker.getValue());
            Date dateHired = new Date(date); ///CHECK THAT DATE SELECTED CANNOT BE INVALID in GUI
            if (!dateHired.isValid())
            {
                messageArea.appendText("Please select a valid date \n");
                return;
            }
            Profile profile = new Profile(name, department, dateHired);
            Employee emp = new Employee(profile);
            if (company.getNumEmployees() == 0)
            {
                messageArea.appendText("Employee Database is empty.\n");
            }
            else if (company.remove(emp))
            {
                messageArea.appendText("Employee removed.\n");
            }
            else
            {
                messageArea.appendText("Employee doesn't exist.\n");
            }
        }
        catch (NullPointerException e)
        {
            messageArea.appendText("Please provide all values necessary.\n");
            return;
        }
        catch (RuntimeException e)
        {
            messageArea.appendText("Please select a date.\n");
            return;
        }
        clear(new ActionEvent());
    }


    /**
     * Sets hour for a parttime employee
     *
     * @param event of type ActionEvent action triggered by clicking the set hours button
     */
    @FXML
    void setHours(ActionEvent event)
    {
        try
        {
            if (fulltime.isSelected() || management.isSelected())
            {
                messageArea.appendText("Not Applicable.\n");
                return;
            }
            if (company.getNumEmployees() == 0)
            {
                messageArea.appendText("Employee Database is empty!\n");
                return;
            }

            int hoursworked = Integer.parseInt(hoursWorked.getText());
            String name = nameField.getText();
            if (name.length() == 0)
            {
                messageArea.appendText("Please enter a name.\n");
                return;
            }
            RadioButton selectedRadioButton = (RadioButton) dept.getSelectedToggle();
            String department = selectedRadioButton.getText();
            String date = String.valueOf(datePicker.getValue());
            Date dateHired = new Date(date);
            if (!dateHired.isValid())
            {
                messageArea.appendText("Please select a valid date\n");
                return;
            }
            Profile profile = new Profile(name, department, dateHired);
            Employee emp = new Parttime(profile, hoursworked);
            if (hoursworked < 0)
            {
                messageArea.appendText("Working hours cannot be negative.\n");
            }
            else if (hoursworked > 100)
            {
                messageArea.appendText("Invalid Hours: over 100.\n");
            }
            else if (company.setHours(emp))
            {
                messageArea.appendText("Working hours set.\n");
            }
            else
            {
                messageArea.appendText("Employee doesn't exist.\n");
            }
        }//end of try
        catch (NumberFormatException e)
        {
            messageArea.appendText("Please enter integers.\n");
            return;
        }
        catch (NullPointerException e)
        {
            messageArea.appendText("Please provide all values necessary.\n");
            return;
        }
        catch (RuntimeException e)
        {
            messageArea.appendText("Please select a date.\n");
            return;
        }
        clear(new ActionEvent());
    }

    /**
     * Imports employees and their information into the database from a file
     *
     * @param event of type ActionEvent action triggered by clicking the import button
     * @throws FileNotFoundException error for when file location for importing is empty
     */
    @FXML
    void importFile(ActionEvent event) throws FileNotFoundException
    {
        Scanner sc = null;
        try
        {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Source File for the Import");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
            //write code to read from the file.
            sc = new Scanner(sourceFile);
            if (!sc.hasNext())
            {
                messageArea.appendText("Empty File.\n");
                return;
            }
            while (sc.hasNextLine())
            {
                String str = sc.nextLine();
                String[] strings = str.split(",");
                String name = strings[1];
                nameField.setText(name);
                String department = strings[2];
                if (department.equals("CS"))
                {
                    csDept.setSelected(true);
                }
                else if (department.equals("ECE"))
                {
                    eceDept.setSelected(true);
                }
                else if (department.equals("IT"))
                {
                    itDept.setSelected(true);
                }
                String date = strings[3];
                String[] dateArray = date.split("/");
                int month = Integer.parseInt(dateArray[0]);
                int day = Integer.parseInt(dateArray[1]);
                int year = Integer.parseInt(dateArray[2]);
                String monthStr = month < 10 ? "0" + month : String.valueOf(month);
                String dayStr = day < 10 ? "0" + day : String.valueOf(day);
                String date1 = year + "-" + monthStr + "-" + dayStr;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(date1, formatter);
                datePicker.setValue(localDate);
                if (strings[0].equals("P"))
                {
                    parttime.setSelected(true);
                    rate.setText(strings[4]);
                    add(new ActionEvent());
                }
                else if (strings[0].equals("M"))
                {
                    management.setSelected(true);
                    annualSalary.setText(strings[4]);
                    int code = Integer.parseInt(strings[5]);
                    if (code == 1)
                        manager.setSelected(true);
                    else if (code == 2)
                        deptHead.setSelected(true);
                    else if (code == 3)
                        director.setSelected(true);
                    else
                    {
                        messageArea.appendText("Invalid code\n");
                        return;
                    }
                    add(new ActionEvent());
                }
                else if (strings[0].equals("F"))
                {
                    fulltime.setSelected(true);
                    annualSalary.setText(strings[4]);
                    add(new ActionEvent());
                }
                else
                {
                    messageArea.appendText("Invalid employee type\n");
                    return;
                }

            }

        }
        catch (RuntimeException e)
        {
            messageArea.appendText("Please select a file\n"); // realized it gives an exception if you just press cancel after clicking on import
            return;
        }
        finally
        {
            if (sc != null)
            {
                sc.close();
            }
        }

    }

    /**
     * Exports employees and their information into a file from the database
     *
     * @param event of type ActionEvent action triggered by clicking the export button
     * @throws IOException for error in file location/writing
     */
    @FXML
    void exportFile(ActionEvent event) throws IOException
    {
        try
        {
            if (company.getNumEmployees() == 0)
            {
                messageArea.appendText("Employee database is empty - nothing to export\n");
                return;
            }
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
            //write code to write to the file.
            company.exportDatabase(targetFile);
            messageArea.appendText("Finished Exporting Database.\n");
        }
        catch (NullPointerException e)
        {
            messageArea.appendText("Please export a file\n");
            return;
        }
        catch (IOException e)
        {
            messageArea.appendText("Please export a file\n");
            return;
        }

    }


    /**
     * Prints all employees in current order
     *
     * @param event event of type ActionEvent action triggered by clicking the print all employees menu item
     */
    @FXML
    void print(ActionEvent event)
    {
        if (company.getNumEmployees() == 0)
        {
            messageArea.appendText("Employee Database is empty.\n");
            return;
        }
        messageArea.appendText("--Printing Earning Statements for all Employees--" + "\n");
        Employee[] emplist = company.getEmplist();
        for (int i = 0; i < company.getNumEmployees(); i++)
        {
            messageArea.appendText(emplist[i].toString() + "\n");
        }
    }

    /**
     * Prints all employees in the increasing order of date hired
     *
     * @param event event of type ActionEvent action triggered by clicking the print by date hired menu item
     */
    @FXML
    void printByDate(ActionEvent event)
    {
        if (company.getNumEmployees() == 0)
        {
            messageArea.appendText("Employee Database is empty.\n");
            return;
        }
        company.printByDate();
        messageArea.appendText("--Printing earning statements by dateHired--" + "\n");
        Employee[] emplist = company.getEmplist();
        for (int i = 0; i < company.getNumEmployees(); i++)
        {
            messageArea.appendText(emplist[i].toString() + "\n");
        }

    }

    /**
     * Prints all employees categorized by department
     *
     * @param event event of type ActionEvent action triggered by clicking the print by department menu item
     */
    @FXML
    void printByDepartment(ActionEvent event)
    {
        if (company.getNumEmployees() == 0)
        {
            messageArea.appendText("Employee Database is empty.\n");
            return;
        }
        company.printByDepartment();
        messageArea.appendText("--Printing earning statements by department--" + "\n");
        Employee[] emplist = company.getEmplist();
        for (int i = 0; i < company.getNumEmployees(); i++)
        {
            messageArea.appendText(emplist[i].toString() + "\n");
        }
    }

    /**
     * Clears all the fields
     *
     * @param event event of type ActionEvent action triggered by clicking the clear button
     */
    @FXML
    void clear(ActionEvent event) // we didn't put the ActionEvent event parameter - check on that!
    {
        if (nameField != null)
        {
            nameField.clear();
        }
        if (dept.getSelectedToggle() != null)
        {
            dept.getSelectedToggle().setSelected(false);
        }
        if (managetype.getSelectedToggle() != null)
        {
            managetype.getSelectedToggle().setSelected(false);
        }
        if (emptype.getSelectedToggle() != null)
        {
            emptype.getSelectedToggle().setSelected(false);
        }
        if (annualSalary != null)
        {
            annualSalary.clear();
        }
        if (hoursWorked != null)
        {
            hoursWorked.clear();
        }
        if (datePicker != null)
        {
            datePicker.setValue(null);
        }
        if (rate != null)
        {
            rate.clear();
        }

        annualSalary.setDisable(false);
        rate.setDisable(false);
        hoursWorked.setDisable(false);
        manager.setDisable(false);
        deptHead.setDisable(false);
        director.setDisable(false);

    }

    /**
     * Calculates payments for all employees
     *
     * @param event event of type ActionEvent action triggered by clicking the calculate payment button
     */
    @FXML
    void calcPayment(ActionEvent event)
    {
        if (company.getNumEmployees() == 0)
        {
            messageArea.appendText("Employee Database is empty!\n");
            return;
        }
        company.processPayments();
        messageArea.appendText("Calculation of employee payments is done.\n");
    }

}