package controller;

import model.Grid;
import model.State;

import java.util.Collection;

/**
 * @author Benedikt Zoennchen
 */
public interface INeighbourhood {
    Collection<State> getNeighbours(final Grid grid, final int x, final int y);
}
