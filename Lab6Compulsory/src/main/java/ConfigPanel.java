import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    private JSpinner rows;
    private JSpinner cols;
    public ConfigPanel(MainFrame frame) {
        this.frame=frame;
        init();
    }
    private void init() {
        label = new JLabel("Grid size:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        JLabel colslabel=new JLabel("Columns: ");
        colslabel.setForeground(Color.black);
        cols = new JSpinner(new SpinnerNumberModel(4, 2, 500, 1));

        JLabel rowslabel = new JLabel("Rows: ");
        rowslabel.setForeground(Color.black);
        rows = new JSpinner(new SpinnerNumberModel(2,1,100,1));

        add(label);
        add(spinner);
        add(colslabel);
        add(cols);
        add(rowslabel);
        add(rows);
    }

    public int getRows() {
        return (int) rows.getValue();
    }

    public int getCols() {

        return (int) cols.getValue();
    }
}
