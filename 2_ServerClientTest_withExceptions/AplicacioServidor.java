import java.io.*;
import java.net.*;

public class AplicacioServidor 
{

	public static void main(String[] args) throws IOException, InterruptedException
	{	
		ServerSocket ss=null;
		try 
		{
			ss=new ServerSocket (1500);
			
		} catch (IOException e) {
			
			System.out.println("Ha ocurrido un error al atribuirle puerto al Socket.");
		}
		
		Socket soc;
		
		Thread.sleep(1000); //espera para no sobrecargar los recursos usados por el programa
		
		soc=ss.accept();
		
		OutputStream os;
		os=soc.getOutputStream();
		
		System.out.println("Enviando mensaje al cliente."+"\n");
		Thread.sleep(2000);
		
		String m="BENVINGUT\n";
		
		byte[] mensaje=null;
		mensaje=m.getBytes("UTF-8");
		
		os.write(mensaje);
		
		Thread.sleep(1000); //espera para no sobrecargar los recursos usados por el programa
		
		
		InputStream is;
		is=soc.getInputStream();
		
		byte[] m2=new byte[12];
		
		is.read(m2);
		
		String mensaje2=new String(m2,"UTF-8");
		
		if("SALUTACIONS\n".equals(mensaje2))System.out.println(mensaje2);
		else System.out.println("El mensaje entrante no es el correcto o no est� bien decodificado.\n");
		
		os.close();
		is.close();	
		
		try
		{
			soc.close();
			ss.close();
			
		} catch (SocketException e) {
			System.out.println("Si se cierra el socket en este momento habr� p�didas en la comunicaci�n.\n");
		}
	}

}
