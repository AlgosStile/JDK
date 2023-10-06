package serv.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;


public class ServerWindow extends JFrame implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "./src/main/java/serv/server/log.txt";
    public static final Color blueColor = new Color(0, 0, 255);

    JButton btnStart, btnStop;
    JTextArea log;

    private Server server;

    public ServerWindow() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        // Цвет фона и рамки окна
        getContentPane().setBackground(blueColor);
        getRootPane().setBorder(BorderFactory.createLineBorder(blueColor, 3));

        createPanel();

        setVisible(true);

    }

    public static void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH)) {
            writer.write(text + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readLog() {
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

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    @Override
    public void startServer() {
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    @Override
    public void stopServer() {
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }

    @Override
    public void isWork(boolean work) {
        if (work) {
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        } else {
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        btnStart = new JButton("Start");

        // Устанавливаем цвет фона и текста для кнопки
        btnStart.setBackground(blueColor);
        btnStart.setForeground(Color.WHITE);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startServer();
            }
        });
        btnPanel.add(btnStart);

        btnStop = new JButton("Stop");

        // Устанавливаем цвет фона и текста для кнопки
        btnStop.setBackground(blueColor);
        btnStop.setForeground(Color.WHITE);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
            }
        });
        btnPanel.add(btnStop);

        panel.add(btnPanel, BorderLayout.NORTH);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        server = new Server(this);
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    public Server getServer() {
        return server;
    }
}
