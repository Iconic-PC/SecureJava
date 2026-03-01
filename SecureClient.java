import javax.net.ssl.*;
import java.io.*;

public class SecureClient {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "clienttruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "PASSWORD");

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sf.createSocket("localhost", 9999);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter Username: ");
        String username = console.readLine();
        System.out.print("Enter Password: ");
        String password = console.readLine();

        out.write(username + "\n");
        out.write(password + "\n");
        out.flush();

        System.out.println("Server Response: " + in.readLine());

        socket.close();
    }
}