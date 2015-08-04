import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import controller.Envolver;
import controller.Runner;
import controller.SimpleTransition;
import model.Grid;
import model.State;
import view.GridPanel;
import view.SizeDialog;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

/**
 * @author Benedikt Zoennchen
 *
 * The starting point
 */
public class Application {

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setTitle("Game Of Life");
        JMenuBar toolBar = new JMenuBar();
        JButton start = new JButton(getImageIcon("/icons/play_icon.png"));
        start.setToolTipText("Run");
        start.setBorderPainted(false);
        JButton reset = new JButton(getImageIcon("/icons/reset_icon.png"));
        reset.setToolTipText("Clear field");
        reset.setBorderPainted(false);
        JButton next = new JButton(getImageIcon("/icons/next_icon.png"));
        next.setToolTipText("Next step");
        next.setBorderPainted(false);
        JButton pause = new JButton(getImageIcon("/icons/pause_icon.png"));
        pause.setToolTipText("Pause");
        pause.setBorderPainted(false);
        JButton size = new JButton(getImageIcon("/icons/size_icon.png"));
        size.setToolTipText("Set grid size");
        size.setBorderPainted(false);
        JButton showGrid = new JButton(getImageIcon("/icons/grid_icon.png"));
        showGrid.setToolTipText("Enable/disable grid lines");
        showGrid.setBorderPainted(false);
        JButton btRandom = new JButton(getImageIcon("/icons/dice_icon.png"));
        btRandom.setToolTipText("Fill randomly");
        btRandom.setBorderPainted(false);

        JButton btAbout = new JButton(getImageIcon("/icons/about_icon.png"));
        btAbout.setToolTipText("About");
        btAbout.setBorderPainted(false);

        toolBar.add(start);
        toolBar.add(next);
        toolBar.add(pause);
        toolBar.add(reset);
        toolBar.add(btRandom);
        toolBar.add(size);
        toolBar.add(showGrid);
        toolBar.add(new JSeparator(SwingConstants.VERTICAL));
        toolBar.add(btAbout);



        frame.setJMenuBar(toolBar);
        Toolkit.getDefaultToolkit().getScreenSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        Grid grid = new Grid(80, 80);
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


        GridPanel panel = new GridPanel(grid);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(screenSize);
        //panel.setPreferredSize(screenSize);
        frame.setLayout(new FormLayout("2dlu, default:grow, 2dlu", "2dlu, default:grow, 2dlu"));
        frame.getContentPane().add(scrollPane, CC.xy(2, 2));


        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Runner runner = new Runner(envolver, frame);

        start.addActionListener(e -> runner.start());
        pause.addActionListener(e -> runner.pause());
        size.addActionListener(e -> new SizeDialog(grid, frame));
        reset.addActionListener(e -> {
            runner.pause();
            grid.init(grid.getWidth(), grid.getHeight());
            frame.repaint();
        });

        btRandom.addActionListener(e -> {
            String result = JOptionPane.showInputDialog(null,"Choose the probability for an filled cell:",
                    "Input",
                    JOptionPane.PLAIN_MESSAGE);
            try {
                if(Double.parseDouble(result) <= 1.0 && Double.parseDouble(result) >= 0) {
                    runner.pause();
                    grid.init(grid.getWidth(), grid.getHeight(), Double.parseDouble(result));
                    frame.repaint();
                }
            }
            catch (NumberFormatException ex) {
                // ignore
            }

        });

        showGrid.addActionListener(e -> {
            panel.setShowGrid(!panel.isShowGrid());
            frame.repaint();
        });

        next.addActionListener(e -> {
            envolver.evolve();
            frame.repaint();
        });

        btAbout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "For more information about the rules\nlook to the corresponding wikipedia article.\nNote that we do not look at an infinite grid!\nA cell at the border of the grid has less neighbours than cells in the center.",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static ImageIcon getImageIcon(final String pathAndFileName) {
        final URL url = Application.class.getResource(pathAndFileName);
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        Image scaledImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

}
