package controller;

import model.Grid;
import model.State;

/**
 * @author Benedikt Zoennchen
 */
public class Envolver {

    private final Grid grid;
    private ITransition transition;

    public Envolver(final Grid grid, final ITransition transition) {
        this.grid = grid;
        this.transition = transition;
    }

    public void evolve() {
        Grid clone = grid.clone();
        for(int x = 0; x < clone.getWidth(); x++) {
            for(int y = 0; y < clone.getHeight(); y++) {
                grid.setValueAt(x, y, transition.map(clone, x, y));
            }
        }
    }
}
