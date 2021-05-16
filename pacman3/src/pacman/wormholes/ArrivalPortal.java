package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;
/**
 * Each instance of this class represents an arrival portal that is part of a wormhole.
 * @invar| getWormholes().stream().allMatch(a-> a != null && a.getArrivalPortal() == this)
 */
public class ArrivalPortal {
	/**
	 * @representationObject
	 * @invar|wormholes != null 
	 * @invar | wormholes.stream().allMatch(a -> a != null)
	 */
	private HashSet<Wormhole> wormholes= new HashSet<>();
	/**
	 * @immutable
	 */
	private final Square square;
	/**
	 * @basic
	 * @post | result != null
	 */
	public Square getSquare() {return square;}
	
	/**
	 * @invar | getWormholesInternal().stream().allMatch(a-> a.getArrivalPortalInternal()==this)
	 * @post | result != null && result.stream().allMatch(a-> a!= null)
	 * @peerObjects
	 * @basic
	 */
	Set<Wormhole> getWormholesInternal(){return Set.copyOf(wormholes);}
	/**
	 * @basic
	 * @peerObjects
	 * @creates | result
	 * @post | result != null
	 */
	public Set<Wormhole> getWormholes(){return Set.copyOf(wormholes);}
	/**
	 * @mutates| this
	 * @throws IllegalArgumentException| wormhole == null
	 * @post | getWormholesInternal().contains(wormhole) 
	 * @post | old(getWormholesInternal()).stream().allMatch(e-> getWormholesInternal().contains(e))
	 */
	void addWormhole(Wormhole wormhole) {
		if (wormhole == null) throw new IllegalArgumentException("wormhole cannot be null");
		wormholes.add(wormhole);
		}
	/**
	 * @mutates| this
	 * @throws IllegalArgumentException| wormhole == null
	 * @post | !getWormholesInternal().contains(wormhole) 
	 * @post | old(getWormholesInternal()).stream().allMatch(e-> getWormholesInternal().contains(e) || e.equals(wormhole))
	 */
	void removeWormhole(Wormhole wormhole) {
		if (wormhole == null) throw new IllegalArgumentException("wormhole cannot be null");
		wormholes.remove(wormhole);}
	/**
	 * @mutates | this
	 * @post | getSquare() == square
	 */
	public ArrivalPortal(Square square) {this.square= square;}
}
