/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
public class Main
{
    static Scanner sc = new Scanner(System.in);
    static int countOf = 0;
    
    public static void bookTicket(Passenger p){
        
        TicketBooker ticket = new TicketBooker();
        
        if(p.age<5){
            System.out.println("No Tickets Available for Children below 5");
            return;
        }
        
        if(p.age>60){
            p.berthName = "L";
            ticket.bookTicketOf(p,TicketBooker.lowerPos.get(0),"L");
            ticket.availableLB--;
            ticket.lowerPos.remove(0);
            return;
        }else if(p.age<18 && countOf<=1){
            countOf++;
            if(countOf>1){
            p.berthName = "L";
            ticket.bookTicketOf(p,TicketBooker.lowerPos.get(0),"L");
            ticket.availableLB--;
            ticket.lowerPos.remove(0);
            if(countOf>1){
                countOf = 128;
            }
            }
            return;
        }
        
        if(TicketBooker.availableWL==0){
            System.out.println("No Tickets Available");
        }
        
        
        if((p.berthName.equals("L") && TicketBooker.availableLB <= 0 )||
           (p.berthName.equals("M") && TicketBooker.availableMB <= 0 ) ||
           (p.berthName.equals("U") &&  TicketBooker.availableUB <= 0 ) ||
           (p.berthName.equals("SU") && TicketBooker.availableSU <= 0 )||
           (p.berthName.equals("SL") && TicketBooker.availableSL <= 0)){
               
            System.out.println("Available L "+ticket.availableLB);
            System.out.println("Available M "+ticket.availableMB);
            System.out.println("Available U "+ticket.availableUB);
            System.out.println("Available SU "+ticket.availableSU);
            System.out.println("Available SL "+ticket.availableSL);
            
            System.out.println("Type your pereference");
            String preferSecond = sc.next();
            p.berthName = preferSecond;
        }
        
         if((p.berthName.equals("L") && TicketBooker.availableLB > 0 )||
           (p.berthName.equals("M") && TicketBooker.availableMB > 0 ) ||
           (p.berthName.equals("U") &&  TicketBooker.availableUB > 0 ) ||
           (p.berthName.equals("SU") && TicketBooker.availableSU > 0 )||
           (p.berthName.equals("SL") && TicketBooker.availableSL > 0)){
        
        if(p.berthName.equals("L") && TicketBooker.availableLB > 0){
            ticket.bookTicketOf(p,TicketBooker.lowerPos.get(0),"L");
            ticket.availableLB--;
            ticket.lowerPos.remove(0);
        }else if(p.berthName.equals("M") && TicketBooker.availableMB > 0){
            ticket.bookTicketOf(p,TicketBooker.middlePos.get(0),"M");
            ticket.availableMB--;
            ticket.middlePos.remove(0);
        }else if(p.berthName.equals("U") && TicketBooker.availableUB > 0){
            ticket.bookTicketOf(p,TicketBooker.upperPos.get(0),"U");
            ticket.availableUB--;
            ticket.upperPos.remove(0);
        }else if(p.berthName.equals("SU") && TicketBooker.availableSU > 0){
            ticket.bookTicketOf(p,TicketBooker.sideUpperPos.get(0),"SU");
            ticket.availableSU--;
            ticket.sideUpperPos.remove(0);
        }else if(p.berthName.equals("SL") && TicketBooker.availableSL > 0){
            ticket.bookTicketOf(p,TicketBooker.sideLowerPos.get(0),"SL");
            ticket.availableSL--;
            ticket.sideLowerPos.remove(0);
        }
        
        }else{
            if(TicketBooker.availableRAC > 0){
                ticket.bookRAC(p,TicketBooker.racPos.get(0),"RAC");
                ticket.racPos.remove(0);
                ticket.availableRAC--;
            }else if(TicketBooker.availableWL > 0){
                ticket.bookWL(p,TicketBooker.waitingPos.get(0),"WL");
                ticket.waitingPos.remove(0);
                ticket.availableWL--;
            }
        }
    }
    
    
	public static void main(String[] args) {
		boolean loop = true;
		while(loop){
		    System.out.println("1-> Book Ticket");
		    System.out.println("2-> Cancel Ticket");
		    System.out.println("3-> Print Ticket");
		    int numChoice = sc.nextInt();
		    if(numChoice==1){
		    System.out.println("Enter you name age berthpreference(L,M,U)");
		    String nameOfPass = sc.next();
		    int ageOfPass = sc.nextInt();
		    String berthpreference = sc.next();
		    Passenger p = new Passenger(nameOfPass,ageOfPass,berthpreference);
		    bookTicket(p);
		    }else if(numChoice==2){
		        TicketBooker ticket = new TicketBooker();
		        System.out.println("Enter Passenger ID");
		        int passToCancel = sc.nextInt();
		        ticket.cancel(passToCancel);
		    }else if(numChoice==3){
		        TicketBooker ticket = new TicketBooker();
		        ticket.print();
		    }
		}
	}
}
