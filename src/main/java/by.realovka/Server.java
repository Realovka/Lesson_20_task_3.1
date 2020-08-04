package by.realovka;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2109);
            System.out.println("by.realovka.Server start");
            Socket client = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String request;//запрос клиента серверу
            while ((request=br.readLine())!=null) {
                System.out.println(String.format("by.realovka.Server received request [%s]", request));
                String response = handleRequestCommand(request);
                bw.write(response); //ответ сервера клиенту
                bw.newLine();
                bw.flush();
                System.out.println(String.format("by.realovka.Server sent response [%s]", response));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String handleRequestCommand(String request) {
        String[] arrayRequest = request.split("__");
        String method = arrayRequest[0];
        switch (method) {
            case "GET":
                return DBConnector.get().toString();
            case "POST":
                DBConnector.post();
                return "Вставка осуществлена";
            case "PUT":
                DBConnector.put();
                return "Замена фамилии осуществлена";
            case "DELETE":
                DBConnector.delete();
                return "Удаление строки осуществлено";
            default:
                return "ERROR 400. Bad request";
        }
    }
}
