import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SecureServer {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "serverkeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "PASSWORD");

        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(9999);
        System.out.println("Secure Server Started...");

        SSLSocket socket = (SSLSocket) serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String username = in.readLine();
        String password = in.readLine();

        if(username.equals("admin") && password.equals("Secure@123")) {
            out.write("Authentication Successful\n");
        } else {
            out.write("Authentication Failed\n");
        }
        out.flush();
        socket.close();
        serverSocket.close();
    }
}