public class Passenger{
    static int id = 1;
    String name;
    int age;
    String berthName;
    int number;
    String alloted;
    int passID;
    
    public Passenger(String name,int age,String berthName){
        this.name = name;
        this.age = age;
        this.berthName = berthName;
        alloted="";
        this.passID = id++;
        number = -1;
    }
}