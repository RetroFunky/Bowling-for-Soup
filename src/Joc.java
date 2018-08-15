
public class Joc {

	private Aruncari aruncari;
	public Joc() {
	aruncari = new Aruncari();
	}
	public void printJoc() {
		Frame[] frames= aruncari.getFrames();
		
		for(int i=0;i<9;i++) {
			System.out.print(frames[i].getPrimaAruncare()+ " "+frames[i].getADouaAruncare()+"| ");
		}
		System.out.println(frames[9].getPrimaAruncare()+ " "+frames[9].getADouaAruncare()+" "+ ((FrameFinal)frames[9]).getATreiaAruncare()+"| ");
		//System.out.println();
		
		
		for(int i=0;i<10;i++) {
			System.out.print(frames[i].getScor()+" | ");
		}
	}
	
	public Frame[] getFrames() {
		return this.aruncari.getFrames();
	}
	
	public boolean getEsteGata() {
		return aruncari.getEsteGata();
	}
	
	
	
	public boolean esteGata() {
		return aruncari.getEsteGata();
	}
	public void aruncaMingea(int popiceDaramate) throws AruncareInvalida{
		
		aruncari.aruncaMingea(popiceDaramate);
	}
	
}
