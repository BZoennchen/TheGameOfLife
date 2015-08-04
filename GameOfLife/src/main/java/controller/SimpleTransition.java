package controller;

import model.Grid;
import model.State;

/**
 * @author Benedikt Zoennchen
 */
public class SimpleTransition implements ITransition{

    private INeighbourhood moore;
    private INeighbourhood vonNeumann;

    public SimpleTransition() {
        this.moore = new MooreNeighbourhood();
        this.vonNeumann = new NeumannNeighbourhood();
    }

    public State map(Grid grid, int x, int y) {
        switch (grid.getValueAt(x, y)) {
            case Full: {
                long count = moore.getNeighbours(grid, x, y).stream().filter(state ->  state == State.Full).count();
                if(count >= 2 && count <= 3) {
                    return State.Full;
                }
                else {
                    return State.Empty;
                }
            }
            default: return moore.getNeighbours(grid, x, y).stream().filter(state ->  state == State.Full).count() == 3 ? State.Full : State.Empty;
        }
    }
}
