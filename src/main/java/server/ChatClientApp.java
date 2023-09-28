package server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientApp extends JFrame {

    private final JTextField serverIpTextField;
    private final JTextField portTextField;
    private final JTextArea chatTextArea;
    private final JTextField messageTextField;
    private PrintWriter writer;

    public ChatClientApp() {
        setTitle("Клиент чата");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField loginTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        serverIpTextField = new JTextField();
        portTextField = new JTextField();
        chatTextArea = new JTextArea();
        messageTextField = new JTextField();
        JButton connectButton = new JButton("Подключиться");
        JButton sendButton = new JButton("Отправить");
        JList<String> userList = new JList<>(new String[]{"Пользователь_1", "Пользователь_2", "Пользователь_3"});

        JPanel serverPanel = new JPanel(new GridLayout(4, 2));
        serverPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        serverPanel.add(new JLabel("Логин: "));
        serverPanel.add(loginTextField, BorderLayout.CENTER);
        serverPanel.add(new JLabel("Пароль: "));
        serverPanel.add(passwordField);
        serverPanel.add(new JLabel("IP-адрес сервера: "));
        serverPanel.add(serverIpTextField);
        serverPanel.add(new JLabel("Порт: "));
        serverPanel.add(portTextField);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messagePanel.add(new JScrollPane(chatTextArea), BorderLayout.CENTER);
        messagePanel.add(messageTextField, BorderLayout.NORTH);
        messagePanel.add(sendButton, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(serverPanel, BorderLayout.NORTH);
        mainPanel.add(messagePanel, BorderLayout.CENTER);
        mainPanel.add(userList, BorderLayout.EAST);
        mainPanel.add(connectButton, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageTextField.getText();
                sendMessageToServer(message);
                messageTextField.setText("");
            }
        });

        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String serverIp = serverIpTextField.getText();
                int port = Integer.parseInt(portTextField.getText());
                connectToServer(serverIp, port);
            }
        });

        setVisible(true);
    }

    private void connectToServer(String serverIp, int port) {
        try {
            Socket socket = new Socket(serverIp, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Успешное подключение к серверу");

            // Создаем поток для чтения сообщений с сервера
            Thread messageReaderThread = new Thread(new MessageReader(socket));
            messageReaderThread.start();

        } catch (IOException e) {
            System.out.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }

    private void sendMessageToServer(String message) {
        if (writer != null) {
            writer.println(message);
            System.out.println("Отправлено на сервер: " + message);
        } else {
            System.out.println("Ошибка отправки сообщения: не установлено соединение с сервером");
        }
    }

    private class MessageReader implements Runnable {
        private final Socket socket;

        public MessageReader(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                while (true) {
                    String receivedMessage = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                    if (receivedMessage != null) {
                        System.out.println("Получено с сервера: " + receivedMessage);
                        chatTextArea.append(receivedMessage + "\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка чтения сообщения: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatClientApp();
            }
        });
    }
}