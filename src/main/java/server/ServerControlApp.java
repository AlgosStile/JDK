package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerControlApp extends JFrame {

    private JButton startButton;
    private JButton stopButton;
    private JTextArea logTextArea;
    private boolean isServerWorking = false;

    public ServerControlApp() {
        setTitle("Управление сервером");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startButton = new JButton("Запустить сервер");
        stopButton = new JButton("Остановить сервер");
        logTextArea = new JTextArea();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    logTextArea.append("Сервер уже запущен\n");
                } else {
                    logTextArea.append("Сервер запущен\n");
                    isServerWorking = true;
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    logTextArea.append("Сервер остановлен\n");
                    isServerWorking = false;
                } else {
                    logTextArea.append("Сервер не запущен\n");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(logTextArea), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerControlApp();
            }
        });
    }
}
