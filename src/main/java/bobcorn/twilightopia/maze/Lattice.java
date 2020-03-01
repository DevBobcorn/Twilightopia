package bobcorn.twilightopia.maze;

import java.awt.Point;

public class Lattice {
	private boolean Passable;
	private Point Father;
	public Lattice() {
		setPassable(false);
		setFather(null);
	}

	public boolean isPassable() {
		return Passable;
	}

	public void setPassable(boolean isPassable) {
		this.Passable = isPassable;
	}

	/**
	 * @return the father
	 */
	public Point getFather() {
		return Father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(Point father) {
		Father = father;
	}
}
