package data;

public class Dato {
	private String nombre;
	private String valor;
	
	public Dato(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Dato [nombre=" + nombre + ", valor=" + valor + "]";
	}
	
	
	
	
	
}
