package Item;

import javax.swing.*;
import java.awt.*;

public class ItemPropertyFrame extends JFrame {
    private JLabel itemLabel;

    public ItemPropertyFrame() {
        initObject();
        initSetting();
        setVisible(true);
    }

    private void initObject() {
        itemLabel = new JLabel("This is the ItemPropertyFrame");
        add(itemLabel);
    }

    private void initSetting() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}