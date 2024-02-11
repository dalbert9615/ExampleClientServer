package OP10;

import java.util.Date; //libreria Date 
public class Cronometre /*OB4: realizar un programa que actue como un cronómetro*/
{
	private long acumulado;// acumulador de tiempo
	private long inicio; // punto de inicio
	private boolean marcha; // verificador de marcha del crono
	
	public Cronometre() // constructor
	{
		this.acumulado=0;
		this.marcha=false;
	}
	
	public void marxa() // per posar-lo en marxa
	{
		if(!this.marcha)
		{
			Date instante=new Date ();
			this.marcha=true;
			this.inicio=instante.getTime(); //tiempo inicial
		}
 	}
	
	public void para() // per parar-lo
	{
		if(this.marcha)
		{
			Date instante=new Date();
			this.marcha=false;
			this.acumulado+=instante.getTime()-this.inicio;
		}	
	}
	
	public void posaAzero()// per posar-lo a zero (para ponerlo a cero)
	{
		Date instante =new Date();
		this.inicio=instante.getTime();
		this.acumulado=0;
	}	
	
	public long transcorregut()// per consultar el temps transcorregut, estigui parat o no (en ms)
	{
		if(this.marcha) 
		{
			Date instante=new Date();
			return ((instante.getTime()-this.inicio)+acumulado); //para consultar en marcha
		}
		
		else return this.acumulado;	//para consultar con el crono parado
	}
}