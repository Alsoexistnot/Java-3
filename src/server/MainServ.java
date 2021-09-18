package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.*;

public class MainServ {
    private static final Logger LOGGER = Logger.getLogger("");
    private Vector<ClientHandler> clients;

    public MainServ() {
        createAndFormatLogger();
        LOGGER.getHandlers()[0].setLevel(Level.OFF); // Печать в консоль
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8188);
            System.out.println("Сервер запущен!");
            LOGGER.log(Level.INFO, "Сервер запущен.");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                LOGGER.log(Level.INFO, "Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, "Ошибка!", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.log(Level.WARNING, "Ошибка!", e);
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.log(Level.WARNING, "Ошибка!", e);
            }
            AuthService.disconnect();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
        LOGGER.log(Level.INFO, "Клиент отправил сообщение");
    }

    public void whisperMsg(String writer, String msg, String nickname) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickname)) {
                try {
                    o.out.writeUTF("[W] " + writer + ": " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.log(Level.WARNING, "Ошибка!", e);
                }
            }

        }
        LOGGER.log(Level.INFO, "Клиент отправил личное сообщение");
    }

    public Vector<ClientHandler> getClients() {
        return clients;
    }

    public void createAndFormatLogger(){
        LOGGER.setLevel(Level.INFO);
        try {
            Handler fileHandler = new FileHandler("logs.log", true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.getHandlers()[1].setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    SimpleDateFormat logTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Calendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(record.getMillis());
                    return record.getLevel()
                            + " "
                            + logTime.format(cal.getTime())
                            + " log: "
                            + record.getMessage() + "\n";
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, "Ошибка!", e);
        }
    }
}