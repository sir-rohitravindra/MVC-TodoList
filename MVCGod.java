import View.View;

import Controller.Controller;

import Model.Model;

public class MVCGod {

    public static void main(String[] args) {
        View view = new View("ToDo List Application");
        Model model = new Model();

        Controller controller = new Controller(view, model);
        controller.StartApplication();
    }

}
