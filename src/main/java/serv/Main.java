package serv;


import serv.client.ClientGUI;
import serv.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);

    }
}
