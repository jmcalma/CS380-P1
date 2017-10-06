import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class ChatClient implements Runnable {

    private Socket sock;

    public ChatClient(Socket s) {
        sock = s;
    }

    public void run() {
        try {
            String clientS = "";
            while (true) {
                InputStream is = sock.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                clientS = br.readLine();
                System.out.println("Server> " + clientS);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        
        try (Socket socket = new Socket("18.221.102.182", 38001)) {
            OutputStream outS = socket.getOutputStream();
            PrintStream printS = new PrintStream(outS, true, "UTF-8");
            Scanner scan = new Scanner(System.in);
            ChatClient cc = new ChatClient(socket);
            Thread listener = new Thread(cc);
            String clientS = "";
            System.out.println("#To close the client, type 'exit' after entering a username#\nPlease enter your username: ");
            clientS = scan.nextLine();
            printS.println(clientS);
            listener.start();

            while (true) {
                clientS = scan.nextLine();
            	if(clientS.equals("exit")){
            		System.exit(0);
            	} else {
            		printS.println(clientS);
            	}
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}