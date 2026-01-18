import java.util.*;
public class Administrator{
    String AdminName;
    int AdminPass;
    List<String> profitList;
    
    public Administrator(String AdminName,int AdminPass){
        this.AdminName = AdminName;
        this.AdminPass = AdminPass;
        this.profitList = new ArrayList<>();
    }
}