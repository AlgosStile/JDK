package serv.client;


import serv.server.Server;

public class Client {
    private String name;
    private ClientView clientView;
    private Server server;
    private boolean connected;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        connected = server.connectUser(this);
        if (connected) {
            printText("Successfully connected!\n");
            return true;
        } else {
            printText("Connection failed!");
            return false;
        }
    }

    //мы посылаем
    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ": " + message);
            }
        } else {
            printText("No server connection!");
        }
    }

    //нам посылают
    public void serverAnswer(String answer) {
        printText(answer);
    }

    public void disconnect() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("You have been disconnected from the server!");
        }
    }

    public String getName() {
        return name;
    }

    private void printText(String text) {
        clientView.showMessage(text);
    }

    public void disconnectFromServer() {

    }
}
