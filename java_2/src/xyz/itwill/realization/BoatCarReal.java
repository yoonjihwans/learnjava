package xyz.itwill.realization;

public class BoatCarReal implements BoatCar{

	@Override
	public void run() {
		System.out.println("땅위를 달리는 능력");
		
	}

	@Override
	public void seil() {
		System.out.println("물위를 항해하는 능력");
		
	}

	@Override
	public void raiz() {
		System.out.println("공중에 떠오르는 능력");
		
	}

}
