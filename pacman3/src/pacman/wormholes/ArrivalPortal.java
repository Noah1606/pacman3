package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

public class ArrivalPortal {
	HashSet<Wormhole> wormholes= new HashSet<>();
	private Square square;
	
	public Square getSquare() {
		return square;
	}
	public Set<Wormhole> getWormholes(){
		return Set.copyOf(wormholes);
	}
	public ArrivalPortal(Square square) {
		this.square= square;
	}
}
