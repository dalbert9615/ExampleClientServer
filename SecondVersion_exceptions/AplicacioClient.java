package OP8;

import java.io.*;
import java.net.*;

public class AplicacioClient {

	public static void main(String[] args) throws IOException, InterruptedException
	{
		Socket soc=null;
		
		try 
		{
			soc=new Socket("localhost",1500);
			
		} catch (UnknownHostException e) {
			
			System.out.println("La IP a la que intenta conectarse no es válida.");
		} 
		
		Thread.sleep(1000); //espera para no sobrecargar los recursos usados por el programa
		
		InputStream is=null;
		
		try 
		{
			is=soc.getInputStream();
			
		} catch (IOException e) {
			
			System.out.println("Ha habido un problema con la conexión.");
		}
		
		byte[] m=new byte[10];
		
		is.read(m);
		
		String mensaje;
		mensaje=new String(m,"UTF-8");
		
		if("BENVINGUT\n".equals(mensaje))System.out.println(mensaje);
		else System.out.println("El mensaje entrante no es el correcto o no está bien decodificado.\n");
		
		
		OutputStream os;
		os=soc.getOutputStream();
		
		Thread.sleep(2000);
		
		System.out.println("Enviando mensaje al servidor."+"\n");
		Thread.sleep(2000);
		
		String m2="SALUTACIONS\n";
		byte[] mensaje2=m2.getBytes("UTF-8");
		
		os.write(mensaje2);
		
		Thread.sleep(1000); //espera para no sobrecargar los recursos usados por el programa
		
		is.close();
		os.close();
		
		Thread.sleep(3000);
		soc.close();	
	}

}
