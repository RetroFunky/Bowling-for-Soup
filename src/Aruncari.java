import java.util.ArrayList;

public class Aruncari {
	
	private Frame[] frames;
	private FrameFinal frameFinal;
	private int crtFrame;
	private boolean esteGata;
	private boolean esteRelevant1; // definesc daca aruncarea curenta este relevanta pentru alte frame-uri
	private boolean esteRelevant2;


	private boolean esteValid(int popiceDaramate) {
		if(popiceDaramate <0 || popiceDaramate>10) {
			return false;
		}
		
		Frame frameCurent = frames[crtFrame];
		if(frameCurent.getPrimaAruncare() + popiceDaramate > 10) {
			return false;
		}
		return true;
	} // TO DO validare pt ultimul frame
	
	public Aruncari() {
		this.esteGata = false;
		this.frames = new Frame[9]; // tratam ultimul frame separat!!
	    this.crtFrame = 0;
	    frameFinal = new FrameFinal();
	    esteRelevant1 = false;
	    esteRelevant2 = false;
	}

	public int actualizeazaScor(int popiceDaramate) { // @TODO de folosit antecedenti in loc de formule urate
		Frame frameCurent = frames[crtFrame];

		if (esteRelevant1) { // actualizez scor pentru strike / spare din frame-ul trecut
			int scorVechi = frames[crtFrame - 1].getScor() ;
			frames[crtFrame - 1].setScor(scorVechi + popiceDaramate) ;
			if (frames[crtFrame - 1].getEsteSpare())
				esteRelevant1 = false; // greseala legata de strike
			if (frames[crtFrame - 1].getEsteStrike() && frameCurent.getADouaAruncare() >= 0 )
				esteRelevant1 = false;

		}

		if (esteRelevant2) { // actualizez scor pentru strike de acum doua frame-uri
			int scorVechi = frames[crtFrame - 1].getScor() ;
			frames[crtFrame - 2].setScor(scorVechi + popiceDaramate) ;
			esteRelevant2 = false;
		}

		frameCurent.setScor(popiceDaramate + frameCurent.getScor()) ; // si la prima si la a doua aruncare adun scorurile
	}

	public void aruncaUltimeleMingi(int popiceDaramate){
		FrameFinal frameCurent =  new FrameFinal();
		if(frameCurent.getPrimaAruncare() < 0 ) {
			frameCurent.setPrimaAruncare(popiceDaramate);
			actualizeazaScor(popiceDaramate); // @TODO de verificat validitate treji
			return;
		}
		if(frameCurent.getADouaAruncare() < 0){
			frameCurent.setADouaAruncare(popiceDaramate);
			actualizeazaScor(popiceDaramate);
			if (frameCurent.getADouaAruncare()+frameCurent.getPrimaAruncare() < 10) // strike / spare se arunca si a treia oara
				crtFrame++;
				return;
		}
		if(frameCurent.getATreiaAruncare() < 0){ //if formal
			frameCurent.setATreiaAruncare(popiceDaramate);
			actualizeazaScor(popiceDaramate);
			crtFrame++;
			return;
		}
	}
	public void aruncaMingea(int popiceDaramate) {

		if(!esteValid(popiceDaramate)) { //@TODO verificare input
			// Exceptie!!
		}
		if (crtFrame == 9){ //caz particular ultimul frame poate 3 aruncari
			aruncaUltimeleMingi(popiceDaramate);
			return;
		}
		Frame frameCurent = frames[crtFrame];
		if(popiceDaramate == 10) { // daca strike, actualizez scor, trec mai departe
			frameCurent.setStrike();
			actualizeazaScor(popiceDaramate);
			crtFrame++;
			esteRelevant1 = true ;
			if (crtFrame>=2 && frames[crtFrame-2].getEsteStrike())
				esteRelevant2 = true ;
			return;
		}
		if(frameCurent.getPrimaAruncare()<0) { // daca nu s-a facut prima aruncare, actualizez scor ( in caz ca relevant )
			frameCurent.setPrimaAruncare(popiceDaramate);
			actualizeazaScor(popiceDaramate);
			return;
		}
		if(frameCurent.getADouaAruncare()<0) {  // la a doua aruncare actualizez scor frame curent si verific relevant2??
			frameCurent.setADouaAruncare(popiceDaramate);
			actualizeazaScor(popiceDaramate);
			crtFrame++;
			return ;
		}
		

		
		
		
	}

	// gettere
	public int getCrtFrame() {
		return crtFrame;
	}

	public int getRealFrame(){
		return crtFrame + 1;
	}
	
	
	
	
}
