import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");

    public ControlPanel(MainFrame frame) {
    this.frame=frame;
    init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        add(exitBtn);
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        resetBtn.addActionListener((this::resetGame));

    }

    private void resetGame(ActionEvent c) {
        frame.dispose();
    }

    private void saveGame(ActionEvent b) {
    frame.dispose();
    }

    private void loadGame(ActionEvent a) {
    frame.dispose();
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

}
