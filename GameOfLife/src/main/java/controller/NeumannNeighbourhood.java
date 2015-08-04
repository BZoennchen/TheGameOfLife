package controller;

import model.Grid;
import model.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Benedikt Zoennchen
 */
public class NeumannNeighbourhood implements INeighbourhood {

    public Collection<State> getNeighbours(final Grid  grid, final int x, final int y) {
        List<State> list = new ArrayList();

        if(grid.contains(x+1,y)) {
            list.add(grid.getValueAt(x+1, y));
        }

        if(grid.contains(x-1, y)) {
            list.add(grid.getValueAt(x-1, y));
        }

        if(grid.contains(x, y+1)) {
            list.add(grid.getValueAt(x, y+1));
        }

        if(grid.contains(x, y-1)) {
            list.add(grid.getValueAt(x, y-1));
        }

        return list;
    }
}
