import java.util.*;
public class Customer{
    String cusName;
    int cusPass;
    List<String> cusDetails;
    
    public Customer(String cusName,int cusPass){
        this.cusName = cusName;
        this.cusPass = cusPass;
        this.cusDetails = new ArrayList<>();
    }
}