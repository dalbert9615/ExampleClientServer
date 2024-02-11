import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

public class ScadaTemperatura extends TimerTask implements ActionListener, WindowListener {

	private Cronometre crono;
	private Label l;
	private Button Monitoritza;
	private Button Para;
	private boolean boton;

	public ScadaTemperatura() {
		crono = new Cronometre();
		crono.marxa();
		Frame f = new Frame();
		Monitoritza = new Button("Monitoritza");
		Para = new Button("Atura");
		l = new Label("                                                     ");
		boton = false;

		FlowLayout fly = new FlowLayout();
		f.setLayout(fly);

		f.add(Monitoritza);
		f.add(Para);
		f.add(l);
		Monitoritza.addActionListener(this);

		Para.addActionListener(this);

		l.setText("00:00 00.0ºC\n");

		f.pack();
		f.setVisible(true);
		f.addWindowListener(this);

		Timer c1 = new Timer();
		c1.scheduleAtFixedRate(this, 0, 15000);
	}

	public void run() {
		int min = 0, seg = 0, cr = 0;
		String Cseg = "";

		cr = Math.toIntExact(crono.transcorregut()); // pasa long (crono) a int (variable de la funcion)

		min = (cr % 3600000) / 60000; // transforma el tiempo en minutos (resto de la hora / hora en ms)

		seg = ((cr % 3600000) % 60000) / 1000; // transforma el tiempo en segundos (restode hora%resto de min / seg en
												// ms)

		if (seg < 10)
			Cseg = "0" + seg; // condicion para ponerle un 0 delante a los segundos de un digito
		else
			Cseg = String.valueOf(seg); // condicion para cuando los segundos ya tienen dos digitos

		float temp = 0;
		try {
			temp = consultarTemperatura();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (boton) {
			l.setText(min + ":" + Cseg + " " + temp / 10 + "ºC\n");// coloca las variables en el label
		}
	}

	public long consultarTemperatura() throws IOException {
		String host="localhost";
		int port=502;
		Socket soc=new Socket(host,port);

		OutputStream os;
		os = soc.getOutputStream();

		byte[] request = { 
			(byte) 0x00, 
			(byte) 0x01, 
			(byte) 0x00, 
			(byte) 0x00, 
			(byte) 0x00, 
			(byte) 0x06, 
			(byte) 0x01,
			(byte) 0x03, 
			(byte) 0x9C, 
			(byte) 0x40, 
			(byte) 0x00, 
			(byte) 0x01 };

		os.write(request);

		InputStream is;
		is = soc.getInputStream();

		byte[] response = new byte[11];

		is.read(response);

		long temperatura = ((response[9] & 0xff) << 8) | (response[10] & 0xff);

		is.close();
		os.close();

		soc.close();

		return temperatura;
	}

	public void actionPerformed(ActionEvent e) {
		Object qBoto;
		qBoto = e.getSource();

		if (qBoto == Monitoritza) {
			boton = true;
		}

		else if (qBoto == Para) {
			boton = false;
		}
	}

	/* Close windows */
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	/* Implements */
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}