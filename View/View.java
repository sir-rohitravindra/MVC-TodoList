package View;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class View extends JFrame {

    private JLabel headingLabel;

    private JButton addNewButton;
    private JButton loadDataButton;
    private JButton clearDataButton;
    private static JTextField entryTaskText;
    // private JPanel headPanel;
    private JPanel disPanel;
    private JPanel interPanel;

    public View(String heading) {

        // Heading Label
        headingLabel = new JLabel(heading + "    by Rohit R (PES1UG19CS393) ", JLabel.CENTER);
        headingLabel.setOpaque(true);
        headingLabel.setBackground(Color.lightGray);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 16));

        // Heading Panel
        // headPanel = new JPanel(new GridLayout(1, 1, 0, 0));
        // headPanel.add(headingLabel);

        // Display Panel
        disPanel = new JPanel();
        disPanel.setBackground(Color.white);
        disPanel.setLayout(new GridLayout(0, 1, 0, 5));

        // Interaction Panel
        interPanel = new JPanel();
        interPanel.setBackground(Color.GRAY);
        interPanel.setLayout(new GridLayout(2, 2, 5, 5));

        // Interaction Panel Components
        entryTaskText = new JTextField(40);
        entryTaskText.setHorizontalAlignment(JTextField.CENTER);
        entryTaskText.setFont(new Font("SansSerif", Font.BOLD, 20));

        interPanel.add(entryTaskText);
        addNewButton = new JButton("Add Task");
        interPanel.add(addNewButton);
        loadDataButton = new JButton("Load Data");
        interPanel.add(loadDataButton);
        clearDataButton = new JButton("Clear All Tasks");
        interPanel.add(clearDataButton);

        // Adding All panels to jframe
        this.add(headingLabel);
        this.add(disPanel);
        this.add(interPanel);

    }

    public void EnableView() {
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize((int) screenSize.getWidth(), 600);
        setLayout(new GridLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addActionListenerForNewButton(ActionListener al) {
        addNewButton.addActionListener(al);
    }

    public void AddNewTask(String task) {
        disPanel.add(new Task(task).getTaskComponent());
        disPanel.revalidate();
        disPanel.repaint();
    }

    static public String GetEntryTaskText() {
        return entryTaskText.getText();
    }

    public void addActionListenerForLoadDataButton(ActionListener al) {
        loadDataButton.addActionListener(al);
    }

    public void RepaintView() {

        disPanel.revalidate();
        disPanel.repaint();
    }

    public void addActionListenerForClearAllButton(ActionListener al) {
        clearDataButton.addActionListener(al);
    }

    public void ClearAllTasks() {
        Component[] componentList = disPanel.getComponents();

        // Loop through the components
        for (Component c : componentList) {

            // Find the components you want to remove
            disPanel.remove(c);

        }

        disPanel.revalidate();
        disPanel.repaint();
    }

    public void LoadDataFromDB(List<String> DataList) {

        for (String task : DataList) {
            AddNewTask(task);

        }
        RepaintView();

    }

}
