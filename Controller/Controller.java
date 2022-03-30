//PES1UG19CS393
package Controller;

import View.View;
import Model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void StartApplication() {

        StartupView();
        StartupModel();

    }

    public void StartupView() {
        view.EnableView();
        view.addActionListenerForNewButton(new AddNewActionListener());
        view.addActionListenerForClearAllButton(new ClearAllTasksListener());
        view.addActionListenerForLoadDataButton(new LoadDataListener());

    }

    public void StartupModel() {
        model.EstablishDBConnection();
    }

    class AddNewActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {

            try {

                String taskName = View.GetEntryTaskText();
                view.AddNewTask(taskName);
                model.InsertDB(taskName, "Pending");

            } catch (Exception er) {
                System.err.println(er);
            }

        }

    }

    class ClearAllTasksListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {

                view.ClearAllTasks();

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    class LoadDataListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {

                List<String> LoadedData = model.FetchData();
                view.LoadDataFromDB(LoadedData);

            } catch (Exception e) {
                System.out.println("Controller.LoadDataListener.actionPerformed() fail");
            }

        }
    }

}
