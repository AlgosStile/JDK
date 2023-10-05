package animationS.circles;



import animationS.draw_pic.view.BallsOverflowException;
import animationS.draw_pic.view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainWindow();
                } catch (BallsOverflowException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}