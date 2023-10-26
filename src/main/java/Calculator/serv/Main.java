package Calculator.serv;

import Calculator.serv.client.ClientGUI;
import Calculator.serv.server.ServerWindow;


public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow.getServer());
    }
}


