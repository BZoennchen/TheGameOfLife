package model;

import java.awt.*;

/**
 * @author Benedikt Zoennchen
 */
public enum State {
    Empty,
    Full;

    public Color getColor() {
        return this == Empty ? Color.WHITE : Color.BLUE;
    }
}
