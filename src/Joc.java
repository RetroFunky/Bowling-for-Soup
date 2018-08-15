import java.util.Vector;
import java.lang.Integer;

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
	// @TODO return int for detailed error recognition
		private boolean esteModificareValida (int frameIndex, int aruncare, int popiceDaramate) throws ModificareInvalida{
			// ca sa folosim obiect frame trebuie sa ii dam camp index in clasa frame
			if( popiceDaramate < 0 || popiceDaramate > 10 ) {
				return false;
			}
			Frame[] frames = this.aruncari.getFrames();
			Frame modifiedFrame = frames[frameIndex] ;
			if(frameIndex<9){
				if ( aruncare != 1 && aruncare != 2) {
					return false;
				}
				int cealaltaAruncare;
				if(aruncare == 1){
					cealaltaAruncare = modifiedFrame.getADouaAruncare();
				}
				else{
					cealaltaAruncare = modifiedFrame.getPrimaAruncare();
				}
				
				return popiceDaramate + cealaltaAruncare <= 10;
			}
			else {

				if (aruncare == 3) {
					if (modifiedFrame.getADouaAruncare() == 10 ||
							modifiedFrame.getPrimaAruncare() + modifiedFrame.getADouaAruncare() == 10){
							// daca a doua aruncare este strike, sau primele doua formeaza un spare pot arunca orice
						return true;
					}
					if (modifiedFrame.getADouaAruncare() + modifiedFrame.getPrimaAruncare() < 10 ) { // daca primele doua nu formeaza un
						return false; // cod irelevant, esteGata se ocupa de asta, de fapt relevant ca sa arunce eroare
					}
					// in orice alt caz este o aruncare valida daca nu depasesc numarul de popice
					if ( modifiedFrame.getPrimaAruncare() == 10 )
						return popiceDaramate + modifiedFrame.getADouaAruncare() <= 10;

					return false; // pentru consistenta, probabil irelevant
				}
				if (aruncare == 2) {
					if (modifiedFrame.getPrimaAruncare() == 10 && popiceDaramate != 10 ){
						return popiceDaramate + ((FrameFinal)modifiedFrame).getATreiaAruncare() <= 10 ;
					}
					if (modifiedFrame.getPrimaAruncare() == 10 && popiceDaramate == 10 ){
						return true ;
					}
					if (modifiedFrame.getPrimaAruncare() != 10 ){ // presupun ca esteGata functioneaza corect
						return modifiedFrame.getPrimaAruncare() + popiceDaramate <= 10 ;
					}
				}
				if (aruncare == 1) {
					if ( popiceDaramate == 10 )
						return true;
					return popiceDaramate + modifiedFrame.getADouaAruncare() <= 10;
				}
			}
			return false ;// aruncare != 1 2 sau 3
		}
		public void modificaFrameJoc ( int frameIndex, int aruncare, int popiceDaramate ) throws ModificareInvalida{
			// se apeleaza cu frameIndexul-ul codat nu cu frameIndexu-ul real
			Vector <Integer> vector = new Vector(11) ;
	        Frame[] frames = this.aruncari.getFrames();

			for ( int i = 0; i < 9 ; i++){
				if(i != frameIndex ){
					if(frames[i].getPrimaAruncare() >= 0 ){
						vector.add(frames[i].getPrimaAruncare());
					}
					if(frames[i].getADouaAruncare() >=0 ){
						vector.add(frames[i].getADouaAruncare());
					}
				}
				else{
					if (!esteModificareValida(frameIndex, aruncare, popiceDaramate)) {
						throw new ModificareInvalida();
					}
					else { //@TODO incarca valoare modificata in vector, treci mai departe.
						// @TODO match 10 0 with strike
						if ( aruncare == 1){
							vector.add(popiceDaramate) ;
							if ( popiceDaramate != 10) {
								vector.add(frames[i].getADouaAruncare()) ;
							}
						}
						if ( aruncare == 2){
							vector.add(frames[i].getPrimaAruncare());
							vector.add(popiceDaramate);
						}
						if ( aruncare == 3){
							vector.add(frames[i].getPrimaAruncare());
							vector.add(frames[i].getADouaAruncare());
							vector.add(popiceDaramate);
						}
					}
				}
			}

			this.aruncari = new Aruncari();
			for(int i=0;i<vector.size() ;i++)  { //@TODO sau este gata
				try {
					this.aruncaMingea(vector.get(i));
				}
				catch(AruncareInvalida aruncareGresita){
					System.out.println("Aruncare invalida!!"); // nu e final!!!!
				}
			}
		}

	public Frame[] getFrames() {
		return this.aruncari.getFrames();
	}
	
	public boolean getEsteGata() {
		return aruncari.getEsteGata();
	}
	
	

	public int getScorFinal() {
		return aruncari.getScorFinal();
	}
	public boolean esteGata() {
		return aruncari.getEsteGata();
	}
	public void aruncaMingea(int popiceDaramate) throws AruncareInvalida{
		
		aruncari.aruncaMingea(popiceDaramate);
	}
	
}
