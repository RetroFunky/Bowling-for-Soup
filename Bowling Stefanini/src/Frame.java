public class Frame {

	private boolean esteStrike;
	private boolean esteSpare;
	private boolean esteScorDefinit;
	private int primaAruncare;
	private int aDouaAruncare;
	private int scor;
	
	public Frame() {
		this.esteStrike = false;
		this.esteSpare = false;
		this.primaAruncare = -1;
		this.aDouaAruncare = -1;
		this.scor =-1;
		this.esteScorDefinit=false;
	}
	
	public void setStrike() {
		this.primaAruncare=10; //
		this.aDouaAruncare=-999; // corespunde cu acel spatiu gol pt a doua aruncare in caz de Strike ,
		this.esteStrike=true;
		this.esteSpare=false;
	}
	
	public void setScor(int newScor) {
		this.scor=newScor;
	}
	
	public int getPrimaAruncare() {
		return this.primaAruncare;
	}
	public int getADouaAruncare() {
		return this.aDouaAruncare;
	}
	public void setPrimaAruncare(int primaAruncare) {
		
		this.primaAruncare= primaAruncare;
		if(primaAruncare == 10) {
			this.setStrike();
		}
	}
	public void setADouaAruncare(int aDouaAruncare) {
		this.aDouaAruncare=aDouaAruncare;
		if(primaAruncare + aDouaAruncare == 10) {
			this.esteSpare = true;
		}
	}
	public boolean getEsteStrike() {
		return esteStrike;
	}
	public boolean getEsteSpare() {
		return esteSpare;
	}
	public boolean isScoreDefined() {
		return esteScorDefinit;
	}
	public int getScor() {
		return scor;
	}

	public void setScoreDefined() {
		this.esteScorDefinit=true;
	}
	
	
	
	
	
	
	
}
