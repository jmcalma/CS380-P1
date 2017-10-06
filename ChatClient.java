
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class ChatClient {

    public static void main(String[] args) throws Exception {
        
        try (Socket socket = new Socket("18.221.102.182", 38001)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStream outS = socket.getOutputStream();
            PrintStream printS = new PrintStream(outS, true, "UTF-8");
            Scanner scan = new Scanner(System.in);
            String clientS = "";
            String serverS = "";

            while (true) {
                System.out.print("Client> ");
                clientS = scan.nextLine();
            	if(clientS.equals("exit")){
            		printS.println(clientS);
            		System.exit(0);
            	} else {
            		printS.println(clientS);
            		serverS = br.readLine();
            		System.out.println("Server> " + serverS);
            	}
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}