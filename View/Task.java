package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Task {
    private JLabel taskDisplay;
    private JButton completedButton = new JButton("Pending");
    private JButton editButton = new JButton("Edit");
    private JPanel taskPanel = new JPanel();
    boolean status = false;

    public Task(String taskName) {
        taskDisplay = new JLabel("Task: " + taskName);
        taskDisplay.setOpaque(true);
        taskDisplay.setBackground(Color.red);
        taskDisplay.setHorizontalAlignment(JTextField.CENTER);
        taskPanel.add(taskDisplay);
        taskPanel.add(completedButton);
        taskPanel.add(editButton);
        taskPanel.setLayout(new GridLayout(1, 3, 20, 5));
        taskPanel.setBackground(Color.yellow);
        taskPanel.setMaximumSize(new Dimension(100, 200));
        editButton.addActionListener(new EditActionListener());
        completedButton.addActionListener(new CompletedActionListener());
    }

    public JPanel getTaskComponent() {
        return taskPanel;
    }

    public void addActionListenerForEditButton(ActionListener al) {
        completedButton.addActionListener(al);
    }

    public void EditTask(String newtask) {
        taskDisplay.setText("Task: " + newtask);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    class EditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {

            try {

                EditTask(View.GetEntryTaskText());

            } catch (Exception er) {
                System.err.println(er);
            }

        }

    }

    class CompletedActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {

            try {
                if (status) {
                    completedButton.setText("Pending");
                    status = false;
                    taskDisplay.setBackground(Color.red);
                } else {
                    completedButton.setText("Completed");
                    status = true;
                    taskDisplay.setBackground(Color.green);
                }

            } catch (Exception e) {

                System.out.println("Task.CompletedActionListener.actionPerformed() failed");
            }
        }
    }

}