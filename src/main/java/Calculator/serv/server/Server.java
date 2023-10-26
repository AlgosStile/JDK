package Calculator.serv.server;

import Calculator.serv.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private final List<Client> clients;
    private final ServerView serverView;
    private boolean work;

    public Server(ServerView serverView) {
        this.serverView = serverView;
        clients = new ArrayList<>();
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        serverView.showMessage(client.getName() + " подключился.");
        clients.add(client);
        return true;
    }

    public void disconnectUser(Client client) {
        clients.remove(client);
        serverView.showMessage(client.getName() + " отключился.");
        client.disconnectFromServer();
    }

    public void sendMessage(String text) {
        if (!work) {
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text) {
        for (Client client : clients) {
//            client.serverAnswer(text);//убрал отображение логина
            client.serverAnswer(client.getName() + ": " + text);
        }
    }

    private void saveInLog(String text) {
        ServerWindow.saveInLog(text);
    }

    private String readLog() {
        return ServerWindow.readLog();
    }

    private void appendLog(String text) {
        serverView.showMessage(text);
    }

    public void startServer() {
        work = true;
        serverView.startServer();
        serverView.showMessage("Server started\n");
    }

    public void stopServer() {
        for (Client client : clients) {
            client.disconnect();
        }
        clients.clear();
        work = false;
        serverView.stopServer();
        serverView.showMessage("Server stopped\n");
    }

    public int getX() {
        return serverView.getX();
    }

    public int getY() {
        return serverView.getY();
    }
}
