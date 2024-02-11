package OP10;

import java.io.*;
import java.net.*;

public class AplicacioClient {

	public static void main(String[] args) throws IOException, InterruptedException
	{
		Socket soc=new Socket("localhost",1500);
		
		InputStream is;
		is=soc.getInputStream();
		
		byte[] m=new byte[14];
		
		is.read(m);
		
		String mensaje=new String(m,"UTF-8");
		System.out.println(mensaje);
		
		Thread.sleep(1000);
		int tiempo=is.read();
		System.out.println("Han pasado "+ tiempo+ " segundos desde la ejecución del servidor."+"\n");
		Thread.sleep(2000);
		
		OutputStream os;
		os=soc.getOutputStream();
		
		Thread.sleep(2000);
		System.out.println("Enviando mensaje al servidor."+"\n");
		Thread.sleep(2000);
		
		String m2="SALUTACIONS\n";
		byte[] mensaje2=m2.getBytes("UTF-8");
		
		os.write(mensaje2);
		
		is.close();
		os.close();
		
		soc.close();	
	}

}
