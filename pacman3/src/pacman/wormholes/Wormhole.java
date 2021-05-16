package pacman.wormholes;

import java.util.Arrays;

/**
 * @invar |getDeparturePortal().getWormholes().contains(this)
 * @invar |getArrivalPortal().getWormholes().contains(this)
 */
public class Wormhole {
	private DeparturePortal departureportal;
	private ArrivalPortal arrivalportal;
	/**
	 * @invar |getArrivalPortalInternal().getWormholesInternal().contains(this)
	 * @peerObjects
	 */
	ArrivalPortal getArrivalPortalInternal(){return arrivalportal;}
	/**
	 * @invar |getDeparturePortalInternal().getWormholesInternal().contains(this)
	 * @peerObjects
	 */
	DeparturePortal getDeparturePortalInternal() {return departureportal;}
	/**
	 * @basic
	 * @peerObjects
	 */
	public ArrivalPortal getArrivalPortal(){return arrivalportal;}
	/**
	 * @basic
	 * @peerObjects
	 */
	public DeparturePortal getDeparturePortal() {return departureportal;}
	/**
	 * @mutates_properties | this.getDeparturePortal(), departureportal.getWormholes()
	 * @post | old(getDeparturePortal()).equals(departureportal)? 
	 * 		 | getDeparturePortal().getWormholes().equals(old(departureportal.getWormholes())) : 
	 * 		 | Arrays.stream((getDeparturePortal().getWormholes()).toArray()).allMatch(e-> old(departureportal.getWormholes()).contains((Wormhole)e) || ((Wormhole)e).equals(this))
	 * @throws IllegalArgumentException | departureportal == null
	 */
	public void setDeparturePortal(DeparturePortal departureportal) {
		if(departureportal == null)throw new IllegalArgumentException("Departureportal cannot be null.");
		this.departureportal.removeWormhole(this);
		this.departureportal = departureportal;
		departureportal.addWormhole(this);
		
	}
	/**
	 *@mutates_properties | this.getArrivalPortal(), arrivalportal.getWormholes()
	 *@post  | old(getArrivalPortal()).equals(arrivalportal)? 
	 * 		 | getArrivalPortal().getWormholes().equals(old(arrivalportal.getWormholes())) : 
	 * 		 | getArrivalPortal().getWormholes().stream().allMatch(e-> old(arrivalportal).getWormholes().contains(e) || (e).equals(this))
	 * @throws IllegalArgumentException | arrivalportal == null
	 */
	public void setArrivalPortal(ArrivalPortal arrivalportal) {
		if(arrivalportal == null)throw new IllegalArgumentException("Arrivalportal cannot be null");
		this.arrivalportal.removeWormhole(this);
		this.arrivalportal = arrivalportal;
		arrivalportal.addWormhole(this);
		
	}
	/**
	 * @throws IllegalArgumentException | arrivalportal == null || departureportal == null
	 * @post | departureportal == getDeparturePortal()
	 * @post | arrivalportal == getArrivalPortal()
	 * @mutates | this
	 */
	public Wormhole(DeparturePortal departureportal, ArrivalPortal arrivalportal) {
		if(departureportal == null || arrivalportal == null)throw new IllegalArgumentException("departureportal or arrivalportal is null");
		this.arrivalportal= arrivalportal;
		this.departureportal = departureportal;
		departureportal.addWormhole(this);
		arrivalportal.addWormhole(this);
	}
}
