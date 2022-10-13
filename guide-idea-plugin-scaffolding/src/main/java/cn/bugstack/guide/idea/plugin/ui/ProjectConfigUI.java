package cn.bugstack.guide.idea.plugin.ui;

import javax.swing.*;

public class ProjectConfigUI {

    private JPanel mainPanel;
    private JTextField groupIdField;
    private JTextField artifactIdField;
    private JTextField versionField;
    private JTextField packageField;

    public JComponent getComponent(){
        return mainPanel;
    }

    public JTextField getGroupIdField() {
        return groupIdField;
    }

    public JTextField getArtifactIdField() {
        return artifactIdField;
    }

    public JTextField getVersionField() {
        return versionField;
    }

    public JTextField getPackageField() {
        return packageField;
    }

}
