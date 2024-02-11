import java.io.*;
import java.net.*;

public class ScadaTemperatura {

	public static void main(String[] args) throws IOException, InterruptedException
	{
		Socket soc=new Socket("147.83.107.39",502);
				
		OutputStream os;
		os=soc.getOutputStream();
				
		byte []request={
			(byte)0x00, 
			(byte)0x01, 
			(byte)0x00, 
			(byte)0x00,
			(byte)0x00, 
			(byte)0x06, 
			(byte)0x01, 
			(byte)0x03, 
			(byte)0x9C, 
			(byte)0x40, 
			(byte)0x00, 
			(byte)0x01};
				
		os.write(request);
		
		//Thread.sleep(2000);
		
		InputStream is;
		is=soc.getInputStream();
		
		byte[] response=new byte[11];
		
		is.read(response);
		
		float temperatura = (((response[9] & 0xff) << 8) | (response[10] & 0xff));
		
		System.out.println(temperatura/10 +"ºC"); 
			
		is.close();
		os.close();
		
		soc.close();	
	}

}