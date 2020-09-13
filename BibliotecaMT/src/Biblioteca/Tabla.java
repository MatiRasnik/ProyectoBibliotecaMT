package Biblioteca;

public class Tabla {

	public String id, titulo, autor, tipo;

	public Tabla(String id, String titulo, String autor, String tipo) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String codigo) {
		this.id = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
