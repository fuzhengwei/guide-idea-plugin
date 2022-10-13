package cn.bugstack.guide.idea.plugin.ui;

import javax.swing.*;

public class ReadUI {

    private JPanel mainPanel;
    private JTextPane textContent;

    public JComponent getComponent() {
        return mainPanel;
    }

    public JTextPane getTextContent() {
        return textContent;
    }

}
