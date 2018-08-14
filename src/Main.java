import java.util.Iterator;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomInput joc = new RandomInput();
		Vector<Integer> vectorJoc = joc.getVector() ;
		Iterator itr = vectorJoc.iterator();
		while(itr.hasNext()){
			int numar  = (int)itr.next();
			System.out.print(numar + " ");
		}
		return;
	}

}
