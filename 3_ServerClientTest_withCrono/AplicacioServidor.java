import java.io.*;
import java.net.*;

public class AplicacioServidor 
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		Cronometre crono=new Cronometre(); //incorporar cron�metro
		crono.marxa();
		
		ServerSocket ss=new ServerSocket (1500);
		Socket soc;
		soc=ss.accept();
		
		OutputStream os;
		os=soc.getOutputStream();
		
		System.out.println("Enviando mensaje al cliente."+"\n");
		Thread.sleep(2000);
		
		String m="BENVINGUT\n";
		byte[] mensaje=m.getBytes("UTF-8");
		
		os.write(mensaje);
	
		int tiempo=Math.toIntExact(crono.transcorregut())/1000;
		os.write(tiempo);
		
		InputStream is;
		is=soc.getInputStream();
		
		byte[] m2=new byte[16];
		
		is.read(m2);
		
		String mensaje2=new String(m2,"UTF-8");
		System.out.println(mensaje2);
		
		os.close();
		is.close();
		
		soc.close();
		ss.close();
	}

}
