package cs470project;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread{
	//protected Socket[] socket = new Socket[10];
	public static ServerSocket serverSocket;
	int i =0;
	protected Socket so;
	public static void main(java.lang.String[] args) throws UnknownHostException, IOException {
	int portNumber = 3797;
	
	System.out.println("Creating server socket on port " + portNumber);
	System.setProperty("java.net.useSystemProxies", "true");
	serverSocket = new ServerSocket(portNumber);
	serverSocket.getReuseAddress();
	
	
	while(true) {
		new Server(serverSocket.accept());
	}

}

private Server(Socket soc) {
	//socket[i++] = soc;
	
	so = soc;
	start();
	
}
public void run() {
	OutputStream os = null ;
	PrintWriter pw;
	BufferedReader br = null;
	try {
		os = so.getOutputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pw = new PrintWriter(os, true);
	try {
		br = new BufferedReader(new InputStreamReader(so.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String messageFromClient = null;
	while(true) {
	try {
		messageFromClient = br.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// check if the string contains "list all the ip", send.
	pw.println("Got the message from you!!! " + messageFromClient);
	}
}


}

