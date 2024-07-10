public class Student {

    //private variables for this class
    private String ID;
    private String name;

    private Module module1;
    private Module module2;
    private Module module3;

    //using parameters to set data to private variables
    public Student(String ID, String name, Module module1, Module module2, Module module3){
            this.ID = ID;
            this.name = name;
            this.module1 = module1;
            this.module2 = module2;
            this.module3 = module3;
    }

    //getter for Id
    public String getID() {
        return ID;
    }

    //getter for name
    public String getName() {
        return name;
    }

    //getter for module1
    public Module getModule1() {
        return module1;
    }

    //getter for module2
    public Module getModule2() {
        return module2;
    }

    //getter for module3
    public Module getModule3() {
        return module3;
    }

    //method to calculate total marks using marks
    public double gettotalMarks() {
        double totalMark =  module1.getModuleMarks() + module2.getModuleMarks() + module3.getModuleMarks();
        return totalMark;
    }

    //method to calculate average using marks
    public double getavgMarks(){
        double averagemark = (module1.getModuleMarks() + module2.getModuleMarks() + module3.getModuleMarks()) / 3.0;
        return averagemark;
    }

    //method to calculate the grade using the average
    public String getResult() {
        double resultAverage = getavgMarks();

        if(resultAverage>= 80){
            return "Distinction";
        } else if (resultAverage>= 70) {
            return "Merit";

        }else if (resultAverage>= 40) {
            return "Pass";

        }else{
            return "Fail";
        }

    }
}
