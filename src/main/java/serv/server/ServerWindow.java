package serv.server;

import serv.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * ServerWindow - графический интерфейс для сервера чата.
 * Он содержит методы для подключения и отключения клиентов, отправки сообщений всем клиентам,
 * получения истории сообщений из лог-файла, а также для сохранения сообщений в лог-файл.
 * Класс также содержит методы для создания графического интерфейса и запуска и остановки сервера.
 */

public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "./src/main/java/serv/server/log.txt";

    List<Client> clientGUIList;

    JButton btnStart, btnStop;
    JTextArea log;
    boolean work;

    public ServerWindow() {
        clientGUIList = new ArrayList<Client>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientGUIList.add(client);
        return true;
    }

    public String getHistory() {
        return readLog();
    }

    public void disconnectUser(Client clientGUI) {
        clientGUIList.remove(clientGUI);
        if (clientGUI != null) {
            clientGUI.disconnectFromServer();
        }
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
        for (Client clientGUI : clientGUIList) {
            clientGUI.answer(text);
        }
    }

    private void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH)) {
            writer.write(text + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readLog() {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });
        btnPanel.add(btnStart);

        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });
        btnPanel.add(btnStop);

        panel.add(btnPanel, BorderLayout.NORTH);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void startServer() {
        work = true;
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        log.setText("Server started\n");
    }

    private void stopServer() {
        work = false;
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        log.setText("Server stopped\n");
    }
}
