
public class FrameFinal extends Frame{
	public void setStrike() {
		super.setStrike();
		this.aTreiaAruncare = -999;
	}
	private int aTreiaAruncare;
	public FrameFinal() {
		super();
		this.aTreiaAruncare=-2;
		// to do
	}

	public void setATreiaAruncare(int aTreiaAruncare){
		this.aTreiaAruncare = aTreiaAruncare ;
	}

	public int getATreiaAruncare(){
		return aTreiaAruncare;
	}
}
