package animationS.draw_pic;

import animationS.draw_pic.view.BallsOverflowException;
import animationS.draw_pic.view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainWindow();
            } catch (BallsOverflowException e) {
                throw new RuntimeException(e);
            }
        });
    }
}