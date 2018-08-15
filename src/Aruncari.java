import java.util.ArrayList;

public class Aruncari {
	
	private Frame[] frames;
	private int crtFrame; // 
	private int crtScoreFrame;// index pt frame-urile cu scor inca neactualizat 
	                           //( pt strike si spare se asteapta asteapta valori de la urmatoarele frame-uri)
	private int scorFinal;
	private boolean esteGata;
	public boolean esteValid(int popiceDaramate) {
		if(popiceDaramate <0 || popiceDaramate>10) {
			return false;
		}
		
		Frame frameCurent = frames[crtFrame]; 
		if(crtFrame < 9) {
			if(frameCurent.getPrimaAruncare() + popiceDaramate > 10 ) {
				return false;
			}
		}else {
			if(frameCurent.getPrimaAruncare() == 10 
					&&(frameCurent.getADouaAruncare() + ((FrameFinal)frameCurent).getATreiaAruncare())>10) {
				return false;
			}
			if(frameCurent.getPrimaAruncare() != 10
				    && (frameCurent.getPrimaAruncare() + frameCurent.getADouaAruncare() > 10)) {
				return false;
			}
		}
		
		return true;
	} // TO DO validare pt ultimul frame
	
	
	// Constructor implicit
	public Aruncari() {  
		this.scorFinal=0;
		this.esteGata = false;
		this.frames = new Frame[10]; // tratam ultimul frame separat!!
		for(int i=0;i<10;i++) {
			frames[i]= new Frame();
		}
		frames[9]= new FrameFinal();
	    this.crtFrame = 0;
	    this.crtScoreFrame = 0;
	}
	private void actualizeazaScor() {
		
		Frame frameCurent= frames[crtScoreFrame];
		int scorFrameAnterior = 0;
		if(crtScoreFrame > 0) {
			scorFrameAnterior = frames[crtScoreFrame-1].getScor();
		}
		
		boolean esteStrike = frameCurent.getEsteStrike();
		if(esteStrike) {
			int firstNextValue = -1,secondNextValue = -1; // urmatoarele 2 valori pt calcularea scorului in caz de Strike
			firstNextValue = frames[crtScoreFrame+1].getPrimaAruncare();
			if(frames[crtScoreFrame+1].getADouaAruncare() >= 0) {
				secondNextValue = frames[crtScoreFrame+1].getADouaAruncare();
			}else if(crtScoreFrame < 8){
				secondNextValue = frames[crtScoreFrame+2].getPrimaAruncare();
			}
			if(firstNextValue >=0 && secondNextValue>=0) {
				int scorFrameCurent = 10+ scorFrameAnterior + firstNextValue + secondNextValue;
				frameCurent.setScor(scorFrameCurent);
				this.crtScoreFrame++;
				return;
			}
			return;
		}
		
		boolean esteSpare = frameCurent.getEsteSpare();
		if(esteSpare) {
			
			int nextValue = -1; // urmatoarea valoare (si singura) pentru calcularea scorului in caz de Spare
			//System.out.println(crtScoreFrame);
			nextValue = frames[crtScoreFrame+1].getPrimaAruncare();
			if(nextValue >= 0) {
				int scorFrameCurent = 10 + scorFrameAnterior + nextValue;
				frameCurent.setScor(scorFrameCurent);
				
				this.crtScoreFrame++;
				return;
			}
			return;
		}
		
		int primaAruncare=frameCurent.getPrimaAruncare();
		int aDouaAruncare=frameCurent.getADouaAruncare();
		
		if(primaAruncare >= 0 && aDouaAruncare >=0) {
			int scorFrameCurent = scorFrameAnterior + frameCurent.getPrimaAruncare() + frameCurent.getADouaAruncare();
			frameCurent.setScor(scorFrameCurent);
			this.crtScoreFrame++;
		}
		
	}
	private void actualizeazaScorFinalFrame(int popiceDaramate) {
		FrameFinal finalFrame= (FrameFinal)frames[9];
		
		int scorAnterior=frames[8].getScor();
		if(finalFrame.getPrimaAruncare()<0) {
			finalFrame.setPrimaAruncare(popiceDaramate);
			return;
		}
		if(finalFrame.getADouaAruncare()<0 && (finalFrame.getPrimaAruncare()+popiceDaramate < 10)) {
			finalFrame.setADouaAruncare(popiceDaramate);
			finalFrame.setATreiaAruncare(-888);
			this.esteGata=true;
			int scorFrameFinal= finalFrame.getPrimaAruncare()+finalFrame.getADouaAruncare();
			finalFrame.setScor(scorAnterior+scorFrameFinal);
			this.scorFinal=finalFrame.getScor();
			return;
		}
		if(finalFrame.getADouaAruncare()<0 && (finalFrame.getPrimaAruncare()+popiceDaramate >= 10)) {
			finalFrame.setADouaAruncare(popiceDaramate);
			return;
		}
		if(!esteGata && finalFrame.getATreiaAruncare()<0) {
			finalFrame.setATreiaAruncare(popiceDaramate);
            this.esteGata=true;
            int scorFrameFinal = finalFrame.getPrimaAruncare() + finalFrame.getADouaAruncare() + finalFrame.getATreiaAruncare();
            finalFrame.setScor(scorAnterior + scorFrameFinal);
            this.scorFinal= finalFrame.getScor();
            return;
		}
		
	}
	
	public void aruncaMingea(int popiceDaramate) throws AruncareInvalida {
		
		if(!esteValid(popiceDaramate)) {
			throw new AruncareInvalida();
		}
		
		if (crtScoreFrame == 9) {
			
			actualizeazaScorFinalFrame( popiceDaramate);
			
			return;
		}
		
		/*if(crtFrame == 9) {
			this.aruncaUltimeleMingi(popiceDaramate);
			return;
		}*/
		System.out.println(crtFrame);
		Frame frameCurent = frames[crtFrame];
		
		if(popiceDaramate == 10 && frameCurent.getPrimaAruncare()<0) {
			frameCurent.setStrike();
			actualizeazaScor();
			if(crtFrame < 9) {
				crtFrame++;
			}// @TODO caz separat global (LUCA AVEA DREPTATE)
			return;
		}
		
		if(frameCurent.getPrimaAruncare() < 0) {
			frameCurent.setPrimaAruncare(popiceDaramate);
			actualizeazaScor();	
			return;
		}

		if(frameCurent.getADouaAruncare() < 0) {
			frameCurent.setADouaAruncare(popiceDaramate);
			actualizeazaScor();
			if(crtFrame < 9) {
				crtFrame++;
			}
			return;
			
		}
		
		actualizeazaScor();
		
	
	}
	public boolean getEsteGata() {
		return this.esteGata;
	}
	public Frame[] getFrames() {
		return frames;
	}
	
	
}
