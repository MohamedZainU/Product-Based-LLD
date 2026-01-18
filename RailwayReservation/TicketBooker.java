import java.util.*;
public class TicketBooker{
    static int availableLB = 3;
    static int availableMB = 4;
    static int availableUB = 4;
    static int availableSU = 3;
    static int availableSL = 2;
    static int availableWL = 2;
    static int availableRAC = 2;
    
    static List<Integer> booked = new ArrayList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static Queue<Integer> waitingList = new LinkedList<>();
    
    
    static List<Integer> lowerPos = new ArrayList<>(Arrays.asList(1,2,3));
    static List<Integer> middlePos = new ArrayList<>(Arrays.asList(1,2,3,4));
    static List<Integer> upperPos = new ArrayList<>(Arrays.asList(1,2,3,4));
    static List<Integer> sideUpperPos = new ArrayList<>(Arrays.asList(1,2,3));
    static List<Integer> sideLowerPos = new ArrayList<>(Arrays.asList(1,2));
    static List<Integer> waitingPos = new ArrayList<>(Arrays.asList(1,2));
    static List<Integer> racPos = new ArrayList<>(Arrays.asList(1,2));
    
    static HashMap<Integer,Passenger> mapPass = new HashMap<>();
    
    
    public static void bookTicketOf(Passenger p,int berthPos,String berthNameOf){
        p.number = berthPos;
        p.alloted = berthNameOf;
        mapPass.put(p.passID,p);
        booked.add(p.passID);
        System.out.println("Booked Successfully");
    }
    
    public static void bookRAC(Passenger p,int berthPos,String berthNameOf){
        p.number = berthPos;
        p.alloted = berthNameOf;
        mapPass.put(p.passID,p);
        racList.add(p.passID);
    }
    
    public static void bookWL(Passenger p,int wlPos,String berthWL){
        p.number = wlPos;
        p.alloted = berthWL;
        mapPass.put(p.passID,p);
        waitingList.add(p.passID);
    }
    
    public static void print(){
        for(Passenger p:mapPass.values()){
            System.out.println(p.passID);
            System.out.println(p.name);
            System.out.println(p.age);
            System.out.println(p.number+" "+p.alloted);
        }
    }
    
    public static void cancel(int passID){
        Passenger p = mapPass.get(passID);
        int posCancel = p.number;
        booked.remove(Integer.valueOf(passID));
        mapPass.remove(Integer.valueOf(passID));
        
        System.out.println("Ticket Cancelled....");
        
        if(p.alloted.equals("L")){
            availableLB++;
            lowerPos.add(posCancel);
        }else if(p.alloted.equals("M")){
            availableMB++;
            middlePos.add(posCancel);
        }else if(p.alloted.equals("U")){
            availableUB++;
            upperPos.add(posCancel);
        }else if(p.alloted.equals("SU")){
            availableSU++;
            sideUpperPos.add(posCancel);
        }else if(p.alloted.equals("SL")){
            availableSL++;
            sideLowerPos.add(posCancel);
        }
        
        if(racList.size()>0){
            Passenger passRAC = mapPass.get(racList.poll());
            int posRac = passRAC.number;
            
            racList.remove(Integer.valueOf(passRAC.passID));
            racPos.add(posRac);
            availableRAC++;
            
            
            if(waitingList.size()>0){
                Passenger passWL = mapPass.get(waitingList.poll());
                int posWL = passWL.number;
                
                waitingList.remove(Integer.valueOf(passWL.passID));
                waitingPos.add(posWL);
                
                passWL.number = racPos.get(0);
                passWL.alloted = "RAC";
                racList.add(passWL.passID);
                racPos.remove(0);
                availableWL++;
                availableRAC--;
                
            }
            Main.bookTicket(passRAC);
        }
    }
}