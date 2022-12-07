package reserva_passagens;

public class Aeronave {
	protected String modelo;

	public Aeronave(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}