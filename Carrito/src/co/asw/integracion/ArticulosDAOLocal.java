package co.asw.integracion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import co.asw.entidades.Articulo;

@Local
public interface ArticulosDAOLocal {

	/**
	 * Llama un procedimiento almacenado que permite la persistencia de un
	 * {@link Articulo} a la base de datos
	 * 
	 * @param a
	 *            {@link Articulo} que será almacenado en la base de datos
	 */
	public void guardarArticulo(Articulo a);

	/**
	 * Llama un procedimiento almacenado que permite la modificación de un
	 * {@link Articulo} registrado en la base de datos
	 * 
	 * @param a
	 *            {@link Articulo} que será almacenado en la base de datos
	 */
	public void modificarArticulo(Articulo a);

	/**
	 * Consulta por medio de un procedimiento almacenado todos los
	 * {@link Articulo} que se encuentran registrados en la base de datos
	 * 
	 * @return {@link ArrayList} con los {@link Articulo} registrados en la base
	 *         de datos
	 */
	public List<Articulo> listarArticulos();

	/**
	 * Borra un {@link Articulo} de la base de datos
	 * 
	 * @param a
	 *            {@link Articulo} que será borrado de la base de datos
	 */
	public void borrarArticulo(Articulo a);

}
