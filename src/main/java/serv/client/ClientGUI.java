package serv.client;

import serv.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 * Класс ClientGUI расширяет JFrame и реализует интерфейс ClientView, предоставляющий интерфейс пользователя для чат-клиента.
 * Имеет размеры окна, цвета и логику создания интерфейса, а также обработчики событий.
 * Поля ввода предоставляют возможность ввода IP-адреса, порта, логина, имени и сообщения.
 * Кнопки обеспечивают функциональность входа и отправки сообщений.
 * Отправленные сообщения отображаются в текстовой области.
 * При закрытии окна осуществляется отключение от сервера.
 * Логин в окне сервера больше не отображается.
 * Прежде чем установить соединение пользователь должен ввести имя по кнопке "Set name"
 */
public class ClientGUI extends JFrame implements ClientView {
    public static final int CLIENT_WINDOW_WIDTH = 400;
    public static final int CLIENT_WINDOW_HEIGHT = 300;
    public static final Color blueColor = new Color(0, 0, 255);
    private String currentUser = null;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JButton btnSend;
    JPanel headerPanel;

    private Client client;

    public ClientGUI(Server server) {
        this.client = new Client(this, server);

        setSize(CLIENT_WINDOW_WIDTH, CLIENT_WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());

        getContentPane().setBackground(blueColor);
        getRootPane().setBorder(BorderFactory.createLineBorder(blueColor, 3));

        createPanel();

        setVisible(true);
    }

    private void connectToServer() {
        if (currentUser == null || currentUser.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name using 'Set name' button first!");
            return;
        }
        if (client.connectToServer(tfLogin.getText())) {
            hideHeaderPanel(false);
        }
    }


    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    public void disconnectFromServer() {
        if (client != null) {
            client.disconnect();
        }
        hideHeaderPanel(true);
    }

    private void hideHeaderPanel(boolean visible) {
        headerPanel.setVisible(visible);
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }


    private void promptForUser() {
        String newName = JOptionPane.showInputDialog("Please enter your name:");
        if (newName != null && !newName.trim().isEmpty()) {
            currentUser = newName;
            showMessage("User name has been set as: " + currentUser);
        }
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));

        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Root");

        JButton btnLogin = buildButton("Login", e -> connectToServer());
        JButton btnSetName = buildButton("Set name", e -> promptForUser());



                headerPanel.add(tfIPAddress);
                headerPanel.add(tfPort);
                headerPanel.add(Box.createGlue());
                headerPanel.add(tfLogin);
                headerPanel.add(btnLogin);
                headerPanel.add(btnSetName);

                return headerPanel;
            }


    public void sendMessage() {
        String msg = tfMessage.getText();
        client.sendMessage(currentUser + ": " + msg);
        tfMessage.setText("");
    }

    private JScrollPane createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    sendMessage();
                }
            }
        });

        btnSend = buildButton("Send", e -> sendMessage());

        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);

        return panel;
    }

    private JButton buildButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(blueColor);
        button.setForeground(Color.WHITE);
        button.addActionListener(actionListener);
        return button;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectFromServer();
        }
    }
}

