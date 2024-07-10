public class Module {

    //private variables created for marks and module name
    private double moduleMarks;
    private String name;

    //setting private variable via parameters
    public Module(String name, double moduleMarks){
        this.name = name;
        this.moduleMarks= moduleMarks;

    }
    //getter for module name
    public String getName(){
        return name;
    }
    //getter for module marks
    public double getModuleMarks() {
        return moduleMarks;
    }
}
