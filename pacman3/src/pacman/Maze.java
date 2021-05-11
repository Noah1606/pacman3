package pacman;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	private DeparturePortal[] departurePortals;
	private ArrivalPortal[] arrivalPortals;
	private Wormhole[] wormholes = new Wormhole[0];
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public DeparturePortal[] getDeparturePortals() { return departurePortals.clone(); }
	
	public ArrivalPortal[] getArrivalPortals() { return arrivalPortals.clone(); }
	
	public Wormhole[] getWormholes() { return wormholes.clone(); }
	
	public void addWormhole(Wormhole wormhole) {
		if(!(Arrays.asList(arrivalPortals).contains(wormhole.getArrivalPortal()) &&  Arrays.asList(departurePortals).contains(wormhole.getDeparturePortal()))) {
			throw new IllegalStateException("One or more portals are not in the maze's list of portals.");
		}
		this.wormholes = Arrays.copyOf(this.wormholes, this.wormholes.length + 1);
		this.wormholes[this.wormholes.length - 1] = wormhole;
		}
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, DeparturePortal[] departurePortals, ArrivalPortal[] arrivalPortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.departurePortals = departurePortals.clone();
		this.arrivalPortals = arrivalPortals.clone();
	}
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			for(DeparturePortal dp : departurePortals) {
				if(dp.getSquare().equals(newSquare) && !dp.getWormholes().isEmpty()) {
					Object[] objectArray = dp.getWormholes().toArray();
					Wormhole[] array = new Wormhole[objectArray.length];
					for(int i = 0; i < objectArray.length; i++) {
						array[i] = (Wormhole)objectArray[i];
					}
					Wormhole chosenWormhole = array[random.nextInt(array.length)];
					newSquare = chosenWormhole.getArrivalPortal().getSquare();
					for (Ghost ghost : ghosts)
						if (ghost.getSquare().equals(dp.getSquare()))
							ghost.hitBy(pacMan);
					break;
				}
			}
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			checkPacManDamage();
		}
	}
	
}
