package serv.server;


public interface ServerView {
    void showMessage(String text);

    void startServer();

    void stopServer();

    void isWork(boolean work);


    int getY();

    int getX();
}
