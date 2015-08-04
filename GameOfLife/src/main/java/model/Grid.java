package model;

import controller.ITransition;

/**
 * @author Benedikt Zoennchen
 */
public class Grid {
    private State grid[][];

    public Grid(final int width, final int height) {
        if(width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width or height <= 0.");
        }
        this.grid = new State[width][height];

        for(int x = 0; x < getWidth(); x++) {
            for(int y = 0; y < getHeight(); y++) {
                setValueAt(x, y, State.Empty);
            }
        }
    }

    public int getHeight() {
        return grid[0].length;
    }

    public int getWidth() {
        return grid.length;
    }

    public State getValueAt(final int x, final int y) {
        return grid[x][y];
    }

    public void setValueAt(final int x, final int y, final State type) {
        grid[x][y] = type;
    }

    public boolean contains(final int x, final int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    public Grid clone() {
        Grid clone = new Grid(getWidth(), getHeight());
        for(int x = 0; x < getWidth(); x++) {
            for(int y = 0; y < getHeight(); y++) {
                clone.setValueAt(x,y, getValueAt(x,y));
            }
        }
        return clone;
    }
}
