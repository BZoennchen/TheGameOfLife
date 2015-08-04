import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import controller.Envolver;
import controller.MooreNeighbourhood;
import controller.Runner;
import controller.SimpleTransition;
import model.Grid;
import model.State;
import view.GridPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author Benedikt Zoennchen
 *
 * The starting point
 */
public class Application {

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JButton start = new JButton("start");
        JButton next = new JButton("next");
        JButton pause = new JButton("pause");
        menuBar.add(start);
        menuBar.add(next);
        menuBar.add(pause);

        frame.setJMenuBar(menuBar);
        frame.setSize(new Dimension(1000, 1000));
        Grid grid = new Grid(300, 300);
        Envolver envolver = new Envolver(grid, new SimpleTransition());

        /*grid.setValueAt(150, 150, State.Full);

        grid.setValueAt(152, 150, State.Full);
        grid.setValueAt(152, 151, State.Full);

        grid.setValueAt(154, 152, State.Full);
        grid.setValueAt(154, 153, State.Full);
        grid.setValueAt(154, 154, State.Full);

        grid.setValueAt(156, 153, State.Full);
        grid.setValueAt(156, 154, State.Full);
        grid.setValueAt(156, 155, State.Full);

        grid.setValueAt(157, 154, State.Full);*/

        Random random = new Random();

        for(int x = 0; x < grid.getWidth(); x++) {
            for(int y = 0; y < grid.getHeight(); y++) {
                if(random.nextDouble() <= 0.3125) {
                    grid.setValueAt(x,y,State.Full);
                }
            }
        }


        JPanel panel = new GridPanel(grid);
        panel.setPreferredSize(new Dimension(1000, 1000));
        frame.setLayout(new FormLayout("pref:grow", "pref:grow"));
        frame.getContentPane().add(panel, CC.xy(1, 1));


        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Runner runner = new Runner(envolver, frame);

        start.addActionListener(e -> runner.start());
        pause.addActionListener(e -> runner.pause());

        next.addActionListener(e -> {
            envolver.evolve();
            frame.repaint();
        });
    }

}
