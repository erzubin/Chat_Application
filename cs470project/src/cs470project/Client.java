package cs470project;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String [] args) throws IOException, InterruptedException {
		String hostName = "127.0.0.1";
		int portNumber = 3797;
		BufferedReader in = null;
		BufferedReader stdIn = null;
		PrintWriter out = null;
		  InetAddress ip=null;
	        String hostname;
	        String fromServer;
			String fromUser;
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			
		try {
		    Socket kkSocket = new Socket(hostName, portNumber);
		     out = new PrintWriter(kkSocket.getOutputStream(), true);
				
			
		    in = new BufferedReader(
		        new InputStreamReader(kkSocket.getInputStream()));
		    stdIn = new BufferedReader(
			        new InputStreamReader(System.in));
		} catch (Exception e) {
			System.out.println("Exception" +e);
		}
	
		while ((fromUser = stdIn.readLine()) != null) {
		    System.out.println("client: " + fromUser);
		  
		    if (fromUser.equals("help"))
		    {
		    	
		    	out.println("Display information about the available user interface options or command manual.");
		    }
		    
		    if (fromUser.equals("myip"))
		    {
		    	
		    
		    	System.out.println("Your current IP address : " + ip);
		    }
		    
		    if (fromUser.equals("myport"))
		    {
		    	
		    
		    	 System.out.println("Your current Hostname : " + hostname);
		    }
		    
		    
		    if (fromUser.equals("Bye."))
		    {
		        break;
		    }
		        out.println(fromUser);
		    //Thread.sleep(1000);
		    fromServer  = in.readLine();
		    if (fromServer != null) {
		    	{
		    	System.out.println("From Server: " + fromServer);
		    	}
		   }
		    	
		}
		
	}

}
