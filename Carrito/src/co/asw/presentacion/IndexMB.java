package co.asw.presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import co.asw.entidades.Articulo;
import co.asw.negocio.CarritoEJBLocal;

@ViewScoped
@ManagedBean
public class IndexMB implements Serializable {

	private static final long serialVersionUID = 1L;
	// Atributos del Artículo
	private String referencia;
	private String categoria;
	private String nombre;
	private String marca;
	private String tipo;
	private int cilindraje;
	private int precio;
	// Artibutos para modiificación del Artículo
	private String modCategoria;
	private String modNombre;
	private String modMarca;
	private String modTipo;
	private int modCilindraje;
	private int modPrecio;
	// Manejo de la lista de artículos
	private List<Articulo> listaCargada;
	private Articulo paraModificar;
	private int numeroRepuestos;
	private boolean ejecutar;
	private LineChartModel lineModel2;

	@EJB
	private CarritoEJBLocal servicioCarrito;

	@PostConstruct
	public void inicializar() {
		// Cargar la lista de artículos y dibujar la gráfica
		listarArticulos();
		createLineModels();
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	/**
	 * Este método inicializa la gráfica
	 */
	private void createLineModels() {
		lineModel2 = initCategoryModel();
		lineModel2.setTitle("Sumatoria de repuestos");
		lineModel2.setLegendPosition("e");
		lineModel2.setShowPointLabels(true);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Repuestos X"));
		Axis yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("Repuestos Y");
	}

	/**
	 * Este método se encarga de actualizar la información de las gráficas.
	 * 
	 * @return
	 */
	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries datosGatos = new ChartSeries();
		datosGatos.setLabel("Repuestos");

		if (ejecutar) {
			for (int i = 0; i < numeroRepuestos; i++) {
				Integer t = i;
				String temp = t.toString();
				datosGatos.set(temp, sumatoriaNaturales(i));
			}
		}
		model.addSeries(datosGatos);
		return model;
	}

	/**
	 * Persiste un {@link Articulo} a la base de datos, el procedimiento
	 * almacenado se encargará de asignar el código de referencia al artículo
	 */
	public void guardarArticulo() {
		if (categoria != null & nombre != null & marca != null & tipo != null) {
			Articulo a = new Articulo(categoria, nombre, marca, tipo,
					cilindraje, precio);
			listaCargada.add(a);
			servicioCarrito.agregarArticulo(a);
			redireccionarInicio();
		} else {
			System.out.println("Existen valores nulos.");
		}

	}

	/**
	 * Carga todos los {@link Articulo} existentes en la base de datos
	 */
	public void listarArticulos() {
		listaCargada = servicioCarrito.consultarArticulos();
		numeroRepuestos = listaCargada.size();
		if (numeroRepuestos > 0)
			ejecutar = true;
		sumatoriaArticulos();
	}

	/**
	 * Realiza la sumatoria de los {@link Articulo} que actualmente están
	 * registrados en la base de datos
	 * 
	 * @return Sumatoria de los N Artículos en la base de datos
	 */
	public int sumatoriaArticulos() {
		return (numeroRepuestos * (numeroRepuestos + 1)) / 2;
	}

	/**
	 * Retorna la sumatoria de los N números naturales
	 * 
	 * @param n
	 *            Número hasta donde irá la sumatoria
	 * @return Resultado de la sumatoria de los n números naturales
	 */
	public int sumatoriaNaturales(int n) {
		return (n * (n + 1)) / 2;
	}

	/**
	 * Elimina el registro del artículo desde la base de datos
	 * 
	 * @param articulo
	 *            {@link Articulo} que será eliminado
	 */
	public void borrarArticulo(Articulo articulo) {
		try {
			listaCargada.remove(articulo);
			servicioCarrito.borrarArticulo(articulo);
		} catch (Exception e) {
			System.out.println("no pude borrar");
			e.printStackTrace();
		} finally {
			redireccionarInicio();
		}
	}

	public FacesContext obternerContexto() {
		return FacesContext.getCurrentInstance();
	}

	public void redireccionarInicio() {
		try {
			obternerContexto().getExternalContext().redirect("index.xhtml");
			obternerContexto().responseComplete();
		} catch (IOException e) {
			System.out.println("Error al redireccionar a inicio");
		}
	}

	/**
	 * Parametriza el {@link Articulo} que será modificado
	 * 
	 * @param paraModificar
	 */
	public void modificando(Articulo paraModificar) {
		this.paraModificar = paraModificar;
	}

	/**
	 * Modifica un {@link Articulo} seleccionado por el usuario, el
	 * identificador de referencia no se puede modificar
	 */
	public void modificarArticulo() {
		int indice = listaCargada.indexOf(paraModificar);
		Articulo temp = paraModificar;

		temp.setCategoria(modCategoria);
		temp.setNombre(modNombre);
		temp.setMarca(modMarca);
		temp.setTipo(modTipo);
		temp.setCilindraje(modCilindraje);
		temp.setPrecioBase(modPrecio);

		try {
			listaCargada.remove(paraModificar);
			servicioCarrito.modificarArticulo(temp);
			listaCargada.add(indice, temp);
			redireccionarInicio();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No hay nada para modificar");
		}

	}

	public ArrayList<Articulo> getListaCargada() {
		return (ArrayList<Articulo>) listaCargada;
	}

	public void setListaCargada(ArrayList<Articulo> listaCargada) {
		this.listaCargada = listaCargada;
	}

	public Articulo getParaModificar() {
		return paraModificar;
	}

	public void setParaModificar(Articulo paraModificar) {
		this.paraModificar = paraModificar;
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModCategoria() {
		return modCategoria;
	}

	public void setModCategoria(String modCategoria) {
		this.modCategoria = modCategoria;
	}

	public String getModNombre() {
		return modNombre;
	}

	public void setModNombre(String modNombre) {
		this.modNombre = modNombre;
	}

	public String getModMarca() {
		return modMarca;
	}

	public void setModMarca(String modMarca) {
		this.modMarca = modMarca;
	}

	public String getModTipo() {
		return modTipo;
	}

	public void setModTipo(String modTipo) {
		this.modTipo = modTipo;
	}

	public int getModCilindraje() {
		return modCilindraje;
	}

	public void setModCilindraje(int modCilindraje) {
		this.modCilindraje = modCilindraje;
	}

	public int getModPrecio() {
		return modPrecio;
	}

	public void setModPrecio(int modPrecio) {
		this.modPrecio = modPrecio;
	}

}
