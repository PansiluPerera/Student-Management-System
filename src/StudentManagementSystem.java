//Student Management System created by Pansilu Perera (20230075)//

//Java utility and IO imported for errors and file handling
import java.util.*;
import java.io.*;

public class StudentManagementSystem {

    //Below variables can be used by any method in this class
    //maxStudents and studentDetails are made final so that they do not change whatsoever
    private static final int maxStudents = 100;
    private static final int studentDetails = 5;

    //initCount will be used to count how many students are registered to the system
    private static int initCount = 0;

    //studentInfo is a 2D array that has the dimensions maxStudents(100) and studentDetails(5) so 1 student has 5 fields of information
    private static String[][] studentInfo = new String[maxStudents][studentDetails];


    public static void main(String[] args) {

        //Scanner util was initialized using variable "input" in main program
        Scanner input = new Scanner(System.in);

        //formatting options
        String boldStart = "\033[1m";
        String italicStart = "\033[3m";
        String formatStop = "\033[0m";

        //main menu of the program
        while (true) {
            System.out.println(boldStart + "Welcome to the Student Activity Management System" + formatStop);
            System.out.println(boldStart + "(created by Pansilu Perera)" + formatStop);
            System.out.println(" ");
            System.out.println(boldStart + "Menu" + formatStop);
            System.out.println(" "+ italicStart);
            System.out.println("1. Check Available Seats in the System");
            System.out.println("2. Register Student with ID");
            System.out.println("3. Delete Student using ID");
            System.out.println("4. Find Student using ID");
            System.out.println("5. Store Student Details into a File");
            System.out.println("6. Load Student Details from a File");
            System.out.println("7. View Students (Sorted by Name)");
            System.out.println("8. Alternate Options (Tasks 2 and 3)");
            System.out.println("9. Exit");
            System.out.println(formatStop+ " ");

            boolean validOption = false;
            int option = 0;

            //validating choice of user using try catch exception
            while (!validOption) {
                try {
                    System.out.print("Select Option:");
                    option = input.nextInt();
                    input.nextLine();

                    validOption = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid option. Please enter a valid menu option");
                    input.nextLine();
                }
            }

            if (option == 1) {
                checkSeats();
            } else if (option == 2) {
                registerStudent(input);
            } else if (option == 3) {
                deleteStudent(input);
            } else if (option == 4) {
                findStudent(input);
            } else if (option == 5) {
                storeDetails();
            } else if (option == 6) {
                loadDetails(input);
            } else if (option == 7) {
                viewDetails();
            } else if (option == 8) {

                //new menu option created for tasks 2 and 3
                System.out.println(" " + italicStart);
                System.out.println("A. Register Student with Marks (Task 2)");
                System.out.println("B. Summary of the System (Task 3)");
                System.out.println("C. Complete Report of All Students (Task 3)");
                System.out.println(formatStop + " ");
                System.out.print("Select Option (A, B or C) : ");

                String newOption = input.nextLine();

                if (newOption.equals("A")) {
                    classesRegister(input);
                } else if (newOption.equals("B")) {
                    systemSummary();
                } else if (newOption.equals("C")) {
                    studentReport();
                } else {
                    System.out.println("Invalid Option. Please try again.");
                }
            } else if (option == 9) {
                System.out.println("Goodbye...");
                break;
            } else {
                System.out.println("Invalid option. Try Again");
            }
        }
    }

    //method to create a complete report of the students registered (task 3)
    private static void studentReport() {

        //check if there are students registered, if not exit method
        if (initCount == 0) {
            System.out.println("There are no students recorded yet.");
            System.out.println(" ");
            return;
        }

        //calls to bubbleSortAverage method to arrange array in descending order of averages
        bubbleSortAverage();
        
        System.out.println("Complete Report of All Students Sorted by Average: ");
        System.out.println(" ");
        int i = 0;

        //iterated through the registered students
        for (; i < initCount; i++) {

            //collects the info of each student in 2D array
            String studentID = studentInfo[i][0];
            String studentName = studentInfo[i][1];
            double module1marks = Double.parseDouble(studentInfo[i][2]);
            double module2marks = Double.parseDouble(studentInfo[i][3]);
            double module3marks = Double.parseDouble(studentInfo[i][4]);

            //connects to Module class to set parameters for each module
            Module module1 = new Module("Module 1", module1marks);
            Module module2 = new Module("Module 2", module2marks);
            Module module3 = new Module("Module 3", module3marks);

            //connects to Student class to set parameters for student info
            Student newStudent = new Student(studentID, studentName, module1, module2, module3);

            //gets the total, average and result using methods in student class
            double totalMarks = newStudent.gettotalMarks();
            double averageMarks = newStudent.getavgMarks();
            String result = newStudent.getResult();

            //prints the system report
            System.out.println("Student ID: " + studentID);
            System.out.println("Student Name: " + studentName);
            System.out.println("Module 1 marks: " + module1marks);
            System.out.println("Module 2 marks: " + module2marks);
            System.out.println("Module 3 marks: " + module3marks);
            System.out.println("Total: " + totalMarks);
            System.out.println("Average: " + averageMarks);
            System.out.println("Grade: " + result);
            System.out.println("*****************************************");
            System.out.println(" ");
        }
    }
    //method to create a summary of student registrations (task 3)
    private static void systemSummary() {

        //check if there are students registered, if not exit method
        if (initCount == 0) {
            System.out.println("There are no students recorded yet.");
            System.out.println(" ");
            return;
        }

        int passStudents = 0;
        int i = 0;

        //iterates through students registered
        for (; i < initCount; i++) {
            double module1marks = Double.parseDouble(studentInfo[i][2]);
            double module2marks = Double.parseDouble(studentInfo[i][3]);
            double module3marks = Double.parseDouble(studentInfo[i][4]);

            //marks are checked and decided whether pass or not
            if ((module1marks > 40) && (module2marks > 40) && (module3marks > 40)) {
                passStudents += 1;
            }
        }

        //prints the summary report
        System.out.println(" ");
        System.out.println("Summary of the Student Management System: ");
        System.out.println("Total number of Students registered to the System: " + initCount);
        System.out.println("Total number of Students who scored more than 40 in all 3 modules: " + passStudents);
        System.out.println(" ");

    }
    //method to register students using classes (task 2)
    private static void classesRegister(Scanner input) {

        //calls to remainingSeats method to check if there are available seats
        if (!remainingSeats()) {
            return;
        }

        //Inputs for registering student
        System.out.println("Enter Student ID for registering (8 characters):");
        String studentID = input.nextLine();

        //checks ID to be unique and ID.length to be 8
        while ((studentID.length() != 8) || (checkStudentID(studentID))) {
            if(studentID.length() != 8){
                System.out.println("Entered ID is not valid. Please make sure ID is exactly 8 characters and try again:");
            } else{
                System.out.println("The entered ID already exists. Please try again.");
            }

            studentID = input.nextLine();
        }

        System.out.println("Enter Student Name for registering:");
        String studentName = input.nextLine();

        //marks are registered while parsing as double through validateDouble method
        double module1marks = Double.parseDouble(validateDouble(input, "Enter Module 1 marks; "));
        double module2marks = Double.parseDouble(validateDouble(input, "Enter Module 2 marks; "));
        double module3marks = Double.parseDouble(validateDouble(input, "Enter Module 3 marks; "));

        //calls to Module class to set module marks usign parameters
        Module module1 = new Module("Module 1", module1marks);
        Module module2 = new Module("Module 2", module2marks);
        Module module3 = new Module("Module 3", module3marks);

        //calls to Student class to set student info usign parameters
        Student newStudent = new Student(studentID, studentName, module1, module2, module3);

        //data captured by the two classes are entered to the array
        studentInfo[initCount][0] = newStudent.getID();
        studentInfo[initCount][1] = newStudent.getName();
        studentInfo[initCount][2] = String.valueOf(module1marks);
        studentInfo[initCount][3] = String.valueOf(module2marks);
        studentInfo[initCount][4] = String.valueOf(module3marks);

        System.out.println("The Student has been successfully registered.");
        System.out.println(" ");

        initCount += 1;


    }

    //method to view students sorted by their names
    private static void viewDetails() {

        //check whether there are students registered, if not return to menu
        if (initCount == 0) {
            System.out.println("There are no students recorded yet.");
            System.out.println(" ");
            return;
        }

        //sort the array by names using bubbleSortName method
        bubbleSortName();

        //iterate through existing students
        int i = 0;
        for (; i < initCount; i++) {

            //print the details of each student
            System.out.println("ID: " + studentInfo[i][0]);
            System.out.println(" ");
            System.out.println("Name: " + studentInfo[i][1]);
            System.out.println(" ");
            System.out.println("Module 1 marks: " + studentInfo[i][2]);
            System.out.println(" ");
            System.out.println("Module 2 marks: " + studentInfo[i][3]);
            System.out.println(" ");
            System.out.println("Module 3 marks: " + studentInfo[i][4]);
            System.out.println("*****************************************");
            System.out.println(" ");
        }
    }

    //method to load the details of students using a txt file
    private static void loadDetails(Scanner input) {
        System.out.println("Please make sure that the details file is in the main directory and is named as StudentDetails.txt");
        System.out.println("Please press Enter to load the file.");
        input.nextLine();

        //initiate File class using "load" and filename
        File load = new File("StudentDetails.txt");

        //check whether file exists
        if (load.exists()) {
            try (FileReader getInfo = new FileReader(load)) {

                //load each character in the file
                char[] loadInfo = new char[(int) load.length()];

                //read each line using previously declared File variable
                getInfo.read(loadInfo);

                //each line is loaded into a new variable
                String content = new String(loadInfo);

                //each line is read seperately
                String[] lines = content.split(System.lineSeparator());

                //make initCount 0 to remove any existing students
                initCount = 0;

                //iterate through lines in file
                int i = 0;
                for (; i < lines.length; i++) {

                    //each line is read with respect to location where data was saved precisely with begin index
                    if (lines[i].startsWith("ID:")) {
                        studentInfo[initCount][0] = lines[i].substring(4);
                    } else if (lines[i].startsWith("Name:")) {
                        studentInfo[initCount][1] = lines[i].substring(6);
                    } else if (lines[i].startsWith("Module 1 marks:")) {
                        studentInfo[initCount][2] = lines[i].substring(16);
                    } else if (lines[i].startsWith("Module 2 marks:")) {
                        studentInfo[initCount][3] = lines[i].substring(16);
                    } else if (lines[i].startsWith("Module 3 marks:")) {
                        studentInfo[initCount][4] = lines[i].substring(16);
                    } else if (lines[i].startsWith("*************************************")) {
                        initCount++;
                    }
                }
                System.out.println("The data has been successfully loaded into the program. Please proceed.");
                System.out.println(" ");
            } catch (IOException e) {

                //IO exception catch if error while laoding
                System.out.println("There was an error when loading the details.");
                System.out.println(" ");
            }

        } else {
            //if file doesnt exist, this message will be shown
            System.out.println("The file is missing or incorrectly named. Please check and try again.");
            System.out.println(" ");
        }
    }

    //method to save the student details to a txt file
    private static void storeDetails() {

        //check whether there are students registered
        if (initCount == 0) {
            System.out.println("There are no students recorded yet.");
            System.out.println(" ");
            return;
        }

        //initialize File class using "store" to store details
        File store = new File("StudentDetails.txt");
        try (FileWriter file = new FileWriter(store)) {

            //iterate through registered students
            int i = 0;
            for (; i < initCount; i++) {

                //store the details according to the category for easy readability of txt file
                file.write("ID: " + studentInfo[i][0]);
                file.write(System.lineSeparator());

                file.write("Name: " + studentInfo[i][1]);
                file.write(System.lineSeparator());

                file.write("Module 1 marks: " + studentInfo[i][2]);
                file.write(System.lineSeparator());

                file.write("Module 2 marks: " + studentInfo[i][3]);
                file.write(System.lineSeparator());

                file.write("Module 3 marks: " + studentInfo[i][4]);
                file.write(System.lineSeparator());

                file.write("********************************************");
                file.write(System.lineSeparator());
            }
            System.out.println("Details of Students have been save to a file named 'StudentDetails.txt'.");
            System.out.println(" ");
        } catch (IOException e) {

            //catch exception thrown for any file errors
            System.out.println("There was an error when saving the details.");
            System.out.println(" ");
        }
    }

    //method to find students based on id
    private static void findStudent(Scanner input) {

        //check if students exist in system
        if (initCount == 0) {
            System.out.println("There are no students recorded yet.");
            System.out.println(" ");
            return;
        }
        //get user input for id
        System.out.println("Enter ID of the Student to be found: ");
        String findID = input.nextLine();

        int x = 0;
        for (; x < initCount; x++) {

            //iterate through the array and check if id equals
            if (studentInfo[x][0].equals(findID)) {
                System.out.println("The ID of this Student is " + studentInfo[x][0]);
                System.out.println(" ");
                System.out.println("The Name of this Student is " + studentInfo[x][1]);
                System.out.println(" ");
                System.out.println("The Module 1 marks of this Student is " + studentInfo[x][2]);
                System.out.println(" ");
                System.out.println("The Module 2 marks of this Student is " + studentInfo[x][3]);
                System.out.println(" ");
                System.out.println("The Module 3 marks of this Student is " + studentInfo[x][4]);
                System.out.println(" ");
                return;
            }

        }
        System.out.println("Student not found. Please try again.");
        System.out.println(" ");
    }

    //method to delete student based on id
    private static void deleteStudent(Scanner input) {

        //check if students exist in the system
        if (initCount == 0) {
            System.out.println("There are no students recorded yet.");
            System.out.println(" ");
            return;
        }
        //get user input for id
        System.out.println("Enter ID of the Student to be deleted: ");
        String deleteID = input.nextLine();
        int x = 0;

        //iterate through array
        for (; x < initCount; x++) {

            //check if id equals
            if (studentInfo[x][0].equals(deleteID)) {
                int y = x;
                for (; y < initCount - 1; y++) {

                    //delete student by making it equal to the previous one
                    studentInfo[y] = studentInfo[y + 1];

                }
                //reset info in that position of the array as it is duplicated with previous one
                studentInfo[initCount - 1] = new String[studentDetails];

                initCount -= 1;
                System.out.println("Student has been deleted successfully.");
                System.out.println(" ");
                return;
            }
        }
        System.out.println("Student related to this ID was not found. Please try again.");
        System.out.println(" ");


    }

    //method to register student using 5 user inputs
    private static void registerStudent(Scanner input) {

        //check if there are seats left usign remainingSeats method
        if (!remainingSeats()) {
            return;
        }

        //get user input for the 5 inputs
        System.out.println("Enter Student ID for registering (8 characters):");
        String studentID = input.nextLine();

        //check whether id is unique and id.length equals to 8
        while ((studentID.length() != 8) || (checkStudentID(studentID))) {
            if(studentID.length() != 8){
                System.out.println("Entered ID is not valid. Please make sure ID is exactly 8 characters and try again:");
            } else{
                System.out.println("The entered ID already exists. Please try again.");
            }

            studentID = input.nextLine();
        }
        studentInfo[initCount][0] = studentID;

        System.out.println("Enter Student Name for registering:");
        studentInfo[initCount][1] = input.nextLine();

        //module marks are validated to be double by validateDouble method
        studentInfo[initCount][2] = validateDouble(input, "Enter Module 1 results of Student: ");

        studentInfo[initCount][3] = validateDouble(input, "Enter Module 2 results of Student: ");

        studentInfo[initCount][4] = validateDouble(input, "Enter Module 3 results of Student: ");

        System.out.println("Student has been registered successfully.");

        //increase initCount
        initCount += 1;
        System.out.println(" ");


    }

    //method to check the available no. of seates
    private static void checkSeats() {
        int seatCount = maxStudents - initCount;
        System.out.println("There are " + seatCount + " seats left.");
        System.out.println(" ");
    }

    //method to check if there are seats available or not
    private static boolean remainingSeats() {
        if (initCount >= maxStudents) {
            System.out.println("There are no available seats.");
            return false;
        }
        return true;
    }

    //method to validate double on module marks and parse them as double (2 parameters)
    private static String validateDouble(Scanner input, String request) {
        while (true) {
            System.out.println(request);
            String mark = input.nextLine();

            try {

                //parse the marks as double
                Double.parseDouble(mark);
                return mark;
            } catch (NumberFormatException e) {

                //if error, let user try again
                System.out.println("Invalid input type. Please enter a valid mark.");
            }
        }
    }

    //method for bubble sort by names of students
    private static void bubbleSortName() {

        //iterate once through array
        int x = 0;
        for (; x < initCount - 1; x++) {

            //iterate for the second time through array
            int y = 0;
            for (; y < initCount - 1; y++) {

                //check whether name of next if greater than previous
                if (studentInfo[y][1].compareTo(studentInfo[y + 1][1]) > 0) {

                    //use a temporary array to store data while exchanging positions of data
                    String[] tempArray = studentInfo[y];
                    studentInfo[y] = studentInfo[y + 1];
                    studentInfo[y + 1] = tempArray;
                }
            }
        }
    }

    private static void bubbleSortAverage() {
        //iterate once through array
        int x = 0;
        for (; x < initCount - 1; x++) {

            //iterate for the second time through array
            int y = 0;
            for (; y < initCount - x - 1; y++) {

                //calculate the average for previous and next positions
                double firstAverage = (Double.parseDouble(studentInfo[y][2]) + Double.parseDouble(studentInfo[y][3]) + Double.parseDouble(studentInfo[y][4])) / 3.0;
                double secondAverage = (Double.parseDouble(studentInfo[y + 1][2]) + Double.parseDouble(studentInfo[y + 1][3]) + Double.parseDouble(studentInfo[y + 1][4])) / 3.0;

                //check whether average of next is greater than previous
                if(firstAverage < secondAverage){

                    //use a temporary array to store data while exchanging positions of data
                    String[] tempArray = studentInfo[y];
                    studentInfo[y] = studentInfo[y+1];
                    studentInfo[y+1] = tempArray;
                }

            }
        }
    }

    //method to check whether student id already exists (1 parameter)
    private static boolean checkStudentID (String StudentID){

        //iterate through array
        int x = 0;
        for(; x < initCount; x++){

            //if id equals to another, return true
            if( studentInfo[x][0].equals(StudentID)){
                return true;
            }

        }
        return false;
    }
}