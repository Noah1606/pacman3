package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

public class DeparturePortal {
	HashSet<Wormhole> wormholes = new HashSet<>();
	private final Square square;
	
	public Square getSquare() {
		return square;
	}
	public Set<Wormhole> getWormholes(){
		return Set.copyOf(wormholes);
	}
	
	public DeparturePortal(Square square) {
		this.square = square;
		
	}
	
}
