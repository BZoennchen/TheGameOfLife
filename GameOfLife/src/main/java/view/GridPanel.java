package view;

import model.Grid;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Benedikt Zoennchen
 */
public class GridPanel extends JPanel {

    private final Grid grid;

    public GridPanel(final Grid grid) {
        this.grid = grid;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point position = e.getPoint();
                double cellSize = Math.min(((double) getWidth()) / grid.getWidth(), ((double) getHeight()) / grid.getHeight());

                int x = (int)Math.floor(position.getX() /cellSize);
                int y = (int)Math.floor(position.getY() / cellSize);

                if(grid.contains(x,y)) {
                    switch (grid.getValueAt(x,y)) {
                        case Empty: grid.setValueAt(x,y, State.Full);break;
                        case Full: grid.setValueAt(x,y, State.Empty);break;
                    }
                    GridPanel.this.repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double cellSize = Math.min(((double)getWidth()) / grid.getWidth(), ((double)getHeight()) / grid.getHeight());

        double xLine = cellSize * grid.getWidth();
        double yLine = cellSize * grid.getHeight();

        for(int dx = 0; dx < grid.getWidth(); dx++) {
            for(int dy = 0; dy < grid.getHeight(); dy++) {
                Color c = grid.getValueAt(dx, dy).getColor();
                g2d.setColor(c);
                g2d.fill(new Rectangle2D.Double(dx*cellSize, dy*cellSize, cellSize, cellSize));
            }
        }

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(0.3f));

        for (double x = 0; x < xLine; x += cellSize) {
            g2d.draw(new Line2D.Double(x, 0, x, yLine));
        }

        for(double y = 0; y < yLine; y += cellSize) {
            g2d.draw(new Line2D.Double(0, y, xLine, y));
        }
    }
}
