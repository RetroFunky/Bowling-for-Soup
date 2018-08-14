import java.util.Random;
import java.util.Vector;
import java.lang.Integer;
public class RandomInputGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RandomInput ri= new RandomInput();
		
		Vector<Integer> vector= ri.getVector(); 
		System.out.print("Input random aruncari:{");
		for(int i=0;i<vector.size();i++) {
			System.out.print(vector.get(i)+",");
		}
		System.out.println("}");
		
		
		System.out.println();
		Joc joc= new Joc();
		
		for(int i=0;i<vector.size();i++)  {
			try {
				joc.aruncaMingea(vector.get(i));
			}
			catch(AruncareInvalida aruncare){
				System.out.println("Aruncare invalida!!"); // nu e final!!!!
			}
		}
		joc.printJoc();
		
		
		
		
		
		
		
		
		
	}

}
