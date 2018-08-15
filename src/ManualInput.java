import java.util.Scanner;
public class ManualInput {

    public static boolean isInteger( String input )
    {
       try
       {
          Integer.parseInt( input );
          return true;
       }
       catch( Exception e)
       {
          return false;
       }
    }
    
	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		Joc joc = new Joc();
		
		while(!joc.esteGata()) { // @TODO LUCA VREA SA VERIFICE ESTE GATA
			String aruncare=scanner.next();
			if(!isInteger(aruncare)) {
				System.out.println("Nu ai introdus un numar!!");
				continue;
			}
			try{
				joc.aruncaMingea(Integer.parseInt(aruncare));
			}
			catch(AruncareInvalida aruncareInvalida){
				System.out.println("Ai introdus o aruncare invalida");
				continue;
			}
		}
		System.out.println("gata boss!!");
		joc.printJoc();
		
		

	}

}
