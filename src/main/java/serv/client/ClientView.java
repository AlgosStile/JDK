package serv.client;

public interface ClientView {
    void showMessage(String text);
    void disconnectFromServer();

    void answer(String text);
}
