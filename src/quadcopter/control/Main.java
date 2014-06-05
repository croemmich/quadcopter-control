package quadcopter.control;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
    }

    private Controller controller;

    public Main() {
        setupController();
        loop();
    }

    public void setupController() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for(Controller controller : controllers) {
            if (controller.getType() == Controller.Type.STICK) {
                this.controller = controller;
                return;
            }
        }

        throw new RuntimeException("No Controller");
    }

    public void loop() {
        while(true) {
            controller.poll();
            for(Component component : controller.getComponents()) {
                System.out.println(component.getName() + ": " + component.getPollData());
            }

            System.out.println("-----------------------------------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
