package finalcodecs470;


import java.io.*;
import java.math.BigInteger;

public class RSA extends ModularArithmetic{
	
	BigInteger p;
	BigInteger q;
	BigInteger N;
	BigInteger phi;
	BigInteger e;
	BigInteger d; 
	BigInteger public_key[];
	private BigInteger private_key[];


	RSA(int n){
		
		p = genPrime(n);
		q = genPrime(n);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = genPrime(n/2);
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
       
		d = e.modInverse(phi);
		private_key = new BigInteger[2];
		BigInteger pri[] = new BigInteger[2];
		pri[0] = N;
		pri[1] = d;
		setPrivate_key(pri);
		public_key = new BigInteger[2];
		public_key[0] = N;
		public_key[1] = e;
		
		//System.out.println("Public key is : "+public_key[0]+"\n"+public_key[1]);
		
	}
	
	RSA(int n , String filename) throws IOException{
		
		p = genPrime(n);
		q = genPrime(n);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = genPrime(n/2);
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
       
		d = e.modInverse(phi);
		private_key = new BigInteger[2];
		BigInteger pri[] = new BigInteger[2];
		pri[0] = N;
		pri[1] = d;
		setPrivate_key(pri);
		public_key = new BigInteger[2];
		public_key[0] = N;
		public_key[1] = e;
		
		String str = ""+public_key[0]+"\n"+public_key[1]+"\n"+private_key[0]+"\n"+private_key[1];
		
		File file = new File(filename);
		if(file.createNewFile()){
			System.out.println("File is created");
		}
		else
			System.out.println("File already exists");
		FileWriter writer = new FileWriter(file);
		writer.write(str);
		writer.close();
	}
	
    RSA(String filename) throws IOException{
          	
    	String str,msg[];
    	int i=0;
    	msg = new String[4];
    	@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(filename));
    	while((str=br.readLine())!=null){
    		msg[i++] = str;
    		
    	}

    	BigInteger k1 = new BigInteger(msg[2]);
    	BigInteger k2 = new BigInteger(msg[3]);
    	BigInteger pi[] = new BigInteger[2];
    	pi[0] = k1;
    	pi[1] = k2;
    	setPrivate_key(pi);  
    	
	}

	public BigInteger[] getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(BigInteger private_key[]) {
		this.private_key = private_key;
		
	}
	

	public byte[] encryptFile(String msg,BigInteger N, BigInteger d) throws IOException{

	//	System.out.println("hii.."+msg);
	//	System.out.println("String in Bytes: "+ bytesToString(msg.getBytes()));
		// encrypt
		byte[] encrypted = encrypt(msg.getBytes(),N, d);

		return encrypted;
	}


	public String decryptFile(byte[] msg,BigInteger N, BigInteger e) throws IOException {

		

			//byte[] encrypted = encrypt(msg.getBytes());
			// decrypt
			byte[] decrypted = decrypt(msg,N,e);
			//System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
		//	System.out.println("Decrypted String: " + new String(decrypted));

		return (new String(decrypted));
	}

	private static String bytesToString(byte[] encrypted)
	{
		String test = "";
		for (byte b : encrypted)
		{
			test += Byte.toString(b);
		}
		return test;
	}



	// Encrypt message
	public byte[] encrypt(byte[] message,BigInteger N, BigInteger d)
	{
		return (new BigInteger(message)).modPow(d, N).toByteArray();
	}

	// Decrypt message
	public byte[] decrypt(byte[] message,BigInteger N, BigInteger e)
	{
		return (new BigInteger(message)).modPow(e, N).toByteArray();
	}

}
