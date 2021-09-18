package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class ClientHandler {
    private MainServ serv;
    private Socket socket;
    private String nick;
    DataInputStream in;
    DataOutputStream out;

    public ClientHandler(MainServ serv, Socket socket) {
        try {
            this.serv = serv;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            spot:
                            {
                                String str = in.readUTF();
                                if (str.startsWith("/auth")) {
                                    String[] tokens = str.split(" ");
                                    String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                    if (currentNick != null) {
                                        Vector<ClientHandler> clients = serv.getClients();
                                        for (ClientHandler o : clients) {
                                            if (o.getNick().equals(currentNick)) {
                                                sendMsg("Пользователь уже авторизован");
                                                break spot;
                                            }
                                        }
                                        sendMsg("/authok");
                                        nick = currentNick;
                                        serv.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Неверный логин/пароль");
                                    }
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            System.out.println("Client " + str);
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w ")) {
                                String[] foolPrivateMsg = str.split(" ");
                                String nickWhisp = foolPrivateMsg[1];
                                Vector<ClientHandler> clients = serv.getClients();
                                for (ClientHandler o : clients) {
                                    if (o.getNick().equals(nickWhisp)) {
                                        int wordsSize = foolPrivateMsg[0].length() + foolPrivateMsg[1].length() + 2;
                                        String privateMsg = str.substring(wordsSize);
                                        serv.whisperMsg(getNick(), privateMsg, nickWhisp);
                                        out.writeUTF("[W] to " + nickWhisp + ": " + privateMsg);
                                    }
                                }
                            } else {
                                serv.broadcastMsg(nick + ": " + str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serv.unsubscribe(ClientHandler.this);
                    }

                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
