package finalcodecs470;


import java.math.BigInteger;
import java.util.Random;

public class ModularArithmetic {
	
	public BigInteger modadd(BigInteger a, BigInteger b, BigInteger N)
	{
		BigInteger c;
		c = a.add(b).mod(N); 
		return c ;
	}
	
	public BigInteger modmult(BigInteger a, BigInteger b, BigInteger N)
	{
		BigInteger c;
		c = a.multiply(b).mod(N); 
		return c ;
	}
	
	public BigInteger moddiv(BigInteger a, BigInteger b, BigInteger N)
	{
		BigInteger c;
		c = a.divide(b).mod(N); 
		return c ;
	}
	
	public BigInteger modexp(BigInteger a, BigInteger b, BigInteger N)
	{
		BigInteger c;
		c = a.modPow(b, N); 
		return c ;
	}

	public boolean isPrime(BigInteger N, int k)
	{
		boolean b;
		b = N.isProbablePrime(k);
		return b ;
	}
	
	public BigInteger genPrime(int n)
	{
		BigInteger N;
		Random rnd;
		rnd = new Random();
		N = BigInteger.probablePrime(n, rnd);
		return N;
	}
}

