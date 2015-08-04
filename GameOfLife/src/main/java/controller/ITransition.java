package controller;

import model.Grid;
import model.State;

/**
 * @author Benedikt Zoennchen
 */
public interface ITransition {
    State map(final Grid grid, final int x, final int y);
}
