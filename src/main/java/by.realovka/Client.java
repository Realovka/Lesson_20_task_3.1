package by.realovka;

import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("localhost", 2109);
            String[] request = {"POST__insert into table class 1 a", "GET__first pupil from class 1 a", "PUT__update surname 'Карпов' to 'Григоренко'",
            "DELETE__delete string where surname 'Григоренко"};
            for (int i = 0; i < request.length; i++) {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                bw.write(request[i]);
                bw.newLine();
                bw.flush();
                System.out.println(String.format("Client sent request [%s]", request[i]));
                String response = br.readLine(); //ответ сервера клиенту
                System.out.println(String.format("Client receive response [%s]", response));
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
