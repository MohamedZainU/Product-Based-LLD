import java.util.*;
public class Main
{
    static List<Driver> driverList = new ArrayList<>();
    static List<Customer> customerList = new ArrayList<>();
    static List<Taxi> taxiList = new ArrayList<>();
    static List<Administrator> adminList = new ArrayList<>();
    static HashMap<Character,Integer> map = new HashMap<>();
    static int profitOfAdmin = 0;
    
    static HashMap<Character, Integer> mapCost = new HashMap<>();
    
    
    static Scanner sc = new Scanner(System.in);
    
    
    public static void printProfitForAdmin(Administrator adminOb){
        for(String strs:adminOb.profitList){
            System.out.println(strs);
        }
        System.out.println("Total Profit for Admin Rs: "+profitOfAdmin);
    }
    
    public static void loginAdmin(){
        System.out.println("Enter Admin name and Pass");
        String typeAdmin = sc.next();
        int passAdmin = sc.nextInt();
        for(Administrator a:adminList){
            if(a.AdminName.equals(typeAdmin) && a.AdminPass==passAdmin){
                printProfitForAdmin(a);
            }
        }
    }
    
    public static void printCus(Customer c){
        for(String str:c.cusDetails){
            System.out.println(str);
        }
    }
    
    
    public static void print(Driver driver){
        for(String s:driver.driverRides){
            System.out.println(s);
        }
        
        System.out.println("Total Earnings "+driver.total);
    }
    
    public static void action(Customer c){
        String profit = "";
        int chargeForRide = 0;
        String cus = "";
        System.out.println("1-> Book Details");
        System.out.println("2-> Print Details");
        int choiceOf = sc.nextInt();
        if(choiceOf==1){
        boolean noDuplicates = false;
        System.out.println("Enter pickup");
        char pickup = sc.next().charAt(0);
        System.out.println("Enter Drop");
        char drop = sc.next().charAt(0);
        int min = Integer.MAX_VALUE;
        int earnings = 0;
        String res = "";
        
        HashMap<Integer,Integer> mapLow = new HashMap<>();
        HashMap<Integer,Integer> mapProfitForAdmin = new HashMap<>();
        
        
        for(Taxi taxi:taxiList){

            
            if(taxi.alloted){
                continue;
            }else{
            int currTaxi = map.get(taxi.currSpot);
            int pickupWant = map.get(pickup);
            
            if(currTaxi>=pickupWant){
                mapLow.put(taxi.id,currTaxi-pickupWant);
            }else{
                mapLow.put(taxi.id,pickupWant-currTaxi);
            }
            
            }
            
        }
        
            
            for(int n:mapLow.values()){
                min = Math.min(min,n);
            }
            
            for(Taxi taxis:taxiList){
                if(taxis.alloted){
                    taxis.alloted = false;
                }
            }
            

            
            for(int m:mapLow.keySet()){
                if(!noDuplicates){
                if(min==mapLow.get(m)){
                    noDuplicates = true;
                    System.out.println("Taxi "+m+" can be allocated");
                    for(Taxi t:taxiList){
                        if(t.id==m){
                            
                            if(t.currSpot==pickup){
                              earnings = mapCost.get(pickup)+mapCost.get(drop);
                            }else{
                              earnings = mapCost.get(pickup)+mapCost.get(t.currSpot)+map.get(drop);
                            }
                            
                            
                            t.currSpot = drop;
                            t.alloted = true;
                            
                            
                            
                            chargeForRide = earnings%30;
                            
                            earnings = earnings - chargeForRide;
                            
                            
                            profit = "Taxi "+t.id+"  "+pickup+"  "+drop+"  "+"Rs: "+chargeForRide;
                            
                            cus = "Travelled On Taxi "+t.id+"  "+"From "+pickup+"  To "+drop+"  Price "+earnings;
                            
                            res = "Taxi "+t.id+"  "+"Pickup "+pickup+"  "+"Drop "+drop+"  "+"Earned "+earnings;
                            t.listOfRides.add(res);
                        }
                    }
                    
                    for(Driver driver : driverList){
                        if(driver.id==m){
                            driver.driverRides.add(res);
                            driver.earnings = earnings;
                            driver.updateTotalEarnings(earnings);
                        }
                    }
                    
                    for(Administrator adminProfit:adminList){
                        adminProfit.profitList.add(profit);
                    }
                    profitOfAdmin += chargeForRide;
                }
                }else{
                    break;
                }
            }
            
        c.cusDetails.add(cus);
        }else{
            printCus(c);
        }
    }
    
    public static void loginCustomer(){
        System.out.println("Enter ur Name and Password");
        String typeCusName = sc.next();
        int typeCusPass = sc.nextInt();
        
        for(Customer cus:customerList){
            if(cus.cusName.equals(typeCusName) && cus.cusPass==typeCusPass){
                System.out.println("Login Successful !");
                action(cus);
            }
        }
    } 
    
    
    public static void loginDriver(){
        System.out.println("Enter ur Name and Password");
        String typeName = sc.next();
        int typePass = sc.nextInt();
        
        
        for(Driver driver:driverList){
            if(driver.driverName.equals(typeName) && driver.passWord==typePass){
                System.out.println("Login Successful !");
                print(driver);
            }
        }
    }
    
    
	public static void main(String[] args) {
		boolean isTrue = true;
		Taxi t1 = new Taxi(1,'D');
		Taxi t2 = new Taxi(2,'B');
		Taxi t3 = new Taxi(3,'G');
		Taxi t4 = new Taxi(4,'A');
		
		taxiList.add(t1);
		taxiList.add(t2);
		taxiList.add(t3);
		taxiList.add(t4);
		
		
		mapCost.put('A', 20);
        mapCost.put('B', 45);
        mapCost.put('C', 62);
        mapCost.put('D', 83);
        mapCost.put('E', 109);
        mapCost.put('F', 125);
        mapCost.put('G', 132);
        
        
        Administrator admin = new Administrator("ZULA",1354);
        adminList.add(admin);
		
		
		
		Customer c1 = new Customer("aaa",134);
		Customer c2 = new Customer("bbb",124);
		
		customerList.add(c1);
		customerList.add(c2);
		
		
		Driver d1 = new Driver(1,"ggg",121);
		Driver d2 = new Driver(2,"hhh",111);
		Driver d3 = new Driver(3,"fff",141);
		Driver d4 = new Driver(4,"nnn",151);
		
		driverList.add(d1);
		driverList.add(d2);
		driverList.add(d3);
		driverList.add(d4);
		
	   map.put('A',1);
       map.put('B', 2);
       map.put('C', 3);
       map.put('D', 4);
       map.put('E', 5);
       map.put('F', 6);
       map.put('G', 7);
		
		while(isTrue){
		    System.out.println("1-> Customer");
		    System.out.println("2-> Driver");
		    System.out.println("3-> Admininstrator");
		    int choiceInit = sc.nextInt();
		    if(choiceInit==1){
		        loginCustomer();
		    }else if(choiceInit==2){
		        loginDriver();
		    }else if(choiceInit==3){
		        loginAdmin();
		    }
		}
	}
}
