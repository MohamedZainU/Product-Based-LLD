import java.util.*;
public class Driver{
    int total = 0;
    int earnings;
    int id;
    String driverName;
    int passWord;
    List<String> driverRides;
    
    public Driver(int id,String driverName,int passWord){
        this.id = id;
        this.driverName = driverName;
        this.passWord = passWord;
        this.driverRides = new ArrayList<>();
        this.total = 0;
        this.earnings = 0;
    }
    
    public void updateTotalEarnings(int earnings) {
        this.total += earnings;
    }
    
}