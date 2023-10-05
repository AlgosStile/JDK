package animationS.common;

import animationS.draw_pic.view.MainWindow;

import java.awt.*;

public interface CanvasRepaintListener {
    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime);
}
