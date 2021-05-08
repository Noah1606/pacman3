package pacman.wormholes;

public class Wormhole {
	private DeparturePortal departureportal;
	private ArrivalPortal arrivalportal;
	
	public ArrivalPortal getArrivalPortal(){
		return arrivalportal;
	}
	public DeparturePortal getDeparturePortal() {
		return departureportal;
	}
	public void setDeparturePortal(DeparturePortal departureportal) {
		if(this.departureportal != null)
			this.departureportal.wormholes.remove(this);
		this.departureportal = departureportal;
		departureportal.wormholes.add(this);
		
	}
	public void setArrivalPortal(ArrivalPortal arrivalportal) {
		if(this.arrivalportal != null)
			this.arrivalportal.wormholes.remove(this);
		this.arrivalportal = arrivalportal;
		arrivalportal.wormholes.add(this);
	}
	public Wormhole(DeparturePortal departureportal, ArrivalPortal arrivalportal) {
		this.arrivalportal= arrivalportal;
		this.departureportal = departureportal;
		departureportal.wormholes.add(this);
		arrivalportal.wormholes.add(this);
	}
}
