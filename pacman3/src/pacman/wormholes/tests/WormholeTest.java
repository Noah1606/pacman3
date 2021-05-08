package pacman.wormholes.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Dot;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.PowerPellet;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholeTest {

	@Test
	void test() {
		boolean[] passableList = {false,false,false,
				false,false,true,
				true,false,false,
				true,true,false};
		MazeMap mazemap = new MazeMap(3,4,passableList);
		int row = 2;
		int col = 2;
		Square square1 = Square.of(mazemap,row,col);
		Square square2 = Square.of(mazemap,3,0);
		Square square3 = Square.of(mazemap, 3,1);
		
		DeparturePortal departureportal1 = new DeparturePortal(square1);
		ArrivalPortal arrivalportal1 = new ArrivalPortal(square2);
		ArrivalPortal arrivalportal2= new ArrivalPortal(square3);
		
		Wormhole wormhole = new Wormhole(departureportal1,arrivalportal1);
		Wormhole wormhole2 = new Wormhole(departureportal1, arrivalportal2);
		assertEquals(square1, departureportal1.getSquare());
		assertEquals(square2, arrivalportal1.getSquare());
		assertEquals(Set.of(wormhole, wormhole2),departureportal1.getWormholes());
		assertEquals(Set.of(wormhole),arrivalportal1.getWormholes());
		assertEquals(Set.of(wormhole2), arrivalportal2.getWormholes());
		
	}

}
