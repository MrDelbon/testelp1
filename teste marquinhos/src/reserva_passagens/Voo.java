package reserva_passagens;

public class Voo {
	private Aviao aeronave;
	private int nro;
	private String data;
	private String hora;
	
	public Voo(Aviao aeronave, int nro, String data, String hora) {
		this.aeronave = aeronave;
		this.nro = nro;
		this.data = data;
		this.hora = hora;
	}
	
	public int getNro() {
		return nro;
	}
	public String getData() {
		return data;
	}
	public String getHora() {
		return hora;
	}
	
	
}