import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private static final long serialVersionUID = 1L;

    private JMenuItem p1, p2, p3;
    private String[] columnNames = {"Program", "Starttid", "Sluttid"};
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu tables;

    public View() {
        initView();
    }

    public void initView() {
        frame = new JFrame("Radio Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        menuBar = new JMenuBar();
        tables = new JMenu("Tablåer");
        menuBar.add(tables);

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

    public void makeTable(String[][] data, String[] columnNames) {
        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
    }

    public void setActionListenerP1(ActionListener actionListener) {
        p1.addActionListener(actionListener);
    }

    public void setActionListenerP2(ActionListener actionListener) {
        p2.addActionListener(actionListener);
    }

    public void setActionListenerP3(ActionListener actionListener) {
        p3.addActionListener(actionListener);
    }
}
