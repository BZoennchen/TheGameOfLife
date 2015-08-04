package controller;

import model.Grid;
import model.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Benedikt Zoennchen
 */
public class MooreNeighbourhood implements INeighbourhood {


    public Collection<State> getNeighbours(final Grid grid, final int x, final int y) {
        List<State> list = new ArrayList();

        for(int dx = x-1; dx <= x+1; dx++) {
            for(int dy = y-1; dy <= y+1; dy++) {
                if((dx != x || dy != y) && grid.contains(dx, dy)) {
                    list.add(grid.getValueAt(dx, dy));
                }
            }
        }

        return list;
    }
}
