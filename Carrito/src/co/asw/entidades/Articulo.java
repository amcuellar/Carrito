package co.asw.entidades;

/**
 * POJO que contiene la información relevante a los artículos que se manejarán
 * en el carrito de compras
 * 
 * @author mpuerto
 *
 */
public class Articulo {
	private String referencia;
	private String categoria;
	private String nombre;
	private String marca;
	private String tipo;
	private int cilindraje;
	private int precioBase;

	/**
	 * Este constructor no contiene la referencia del {@link Articulo} ya que
	 * esta es creada por el procedimiento almacenado de la base de datos, se
	 * utiliza siempre que se planee insertar un artículo nuevo
	 * 
	 * @param categoria
	 *            Categoría del {@link Articulo}.
	 * @param nombre
	 *            Nombre del {@link Articulo}.
	 * @param marca
	 *            Marca del {@link Articulo}.
	 * @param tipo
	 *            Tipo de {@link Articulo}.
	 * @param cilindraje
	 *            Cilindraje al que corresponde el {@link Articulo}.
	 * @param precioBase
	 *            Precio base del {@link Articulo}.
	 */
	public Articulo(String categoria, String nombre, String marca, String tipo,
			Integer cilindraje, Integer precioBase) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.marca = marca;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.precioBase = precioBase;
	}

	/**
	 * Este constructor se utiliza cuando el artículo se está cargando desde la
	 * base de datos y ya cuenta con una referencia asignada por el
	 * procedimiento almacenado
	 * 
	 * @param referencia
	 *            Referenia del {@link Articulo} creada por la base de datos.
	 * @param categoria
	 *            Categoría del {@link Articulo}.
	 * @param nombre
	 *            Nombre del {@link Articulo}.
	 * @param marca
	 *            Marca del {@link Articulo}.
	 * @param tipo
	 *            Tipo de {@link Articulo}.
	 * @param cilindraje
	 *            Cilindraje al que corresponde el {@link Articulo}.
	 * @param precioBase
	 *            Precio base del {@link Articulo}.
	 */
	public Articulo(String referencia, String categoria, String nombre,
			String marca, String tipo, Integer cilindraje, Integer precioBase) {
		this.referencia = referencia;
		this.categoria = categoria;
		this.nombre = nombre;
		this.marca = marca;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.precioBase = precioBase;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public int getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(int precioBase) {
		this.precioBase = precioBase;
	}

}
