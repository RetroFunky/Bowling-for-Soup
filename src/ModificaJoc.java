import java.util.Vector;
import java.util.Scanner;
public class ModificaJoc {

	public static void main(String[] args) {
		
		Scanner scanner= new Scanner(System.in);
		Joc joc= new Joc();
		RandomInput ri= new RandomInput();
		Vector<Integer> vector= ri.getVector();
		System.out.print("Input random aruncari:{");
		for(int i=0;i<vector.size() || joc.getEsteGata();i++) { //
			System.out.print(vector.get(i)+",");
		
		}
		System.out.println("}");
		System.out.println();
		
		
		for(int i=0;i<vector.size();i++)  {
			try {
				joc.aruncaMingea(vector.get(i));
			}
			catch(AruncareInvalida aruncare){
				System.out.println("Aruncare invalida!!"); // nu e final!!!!
			}
		}
	

	joc.printJoc();
	
	int frameIndex,aruncare,popiceDaramate;
	frameIndex=scanner.nextInt();
	aruncare=scanner.nextInt();
	popiceDaramate=scanner.nextInt();
	try {
		joc.modificaFrameJoc(frameIndex, aruncare, popiceDaramate);
	}
	catch(ModificareInvalida modificare){
		System.out.println("Modificare invalida!!!");
	}
	
	joc.printJoc();
	
	
	}
	
	
}
