import javax.swing.*;
import java.awt.*;

public class View {

    public View() {
        initView();
    }

    public void initView() {
        JFrame frame = new JFrame("Radio Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu tables = new JMenu("Tablåer");
        menuBar.add(tables);

        JMenuItem p1, p2, p3;
        p1 = new JMenuItem("P1");
        tables.add(p1);
        p2 = new JMenuItem("P2");
        tables.add(p2);
        p3 = new JMenuItem("P3");
        tables.add(p3);

        frame.setJMenuBar(menuBar);
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setVisible(true);
    }
}
