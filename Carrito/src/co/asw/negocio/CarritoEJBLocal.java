package co.asw.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import co.asw.entidades.Articulo;

/**
 * Esta interface expone los servicios disponibles a realizar con
 * {@link Articulo}
 * 
 * @author mpuerto
 *
 */
@Local
public interface CarritoEJBLocal {

	/**
	 * Permite persistir un nuevo {@link Articulo} a la base de datos
	 * 
	 * @param a
	 *            {@link Articulo} a ser almacenado.
	 */
	public void agregarArticulo(Articulo a);

	/**
	 * Trae todos los {@link Articulo} a un {@link ArrayList} desde la base de
	 * datos
	 * 
	 * @return {@link ArrayList} con todos los {@link Articulo} que se
	 *         enncuentran registrados
	 */
	public List<Articulo> consultarArticulos();

	/**
	 * Borra un {@link Articulo} de la base de datos
	 * 
	 * @param a
	 *            {@link Articulo} que será borrado
	 */
	public void borrarArticulo(Articulo a);

	/**
	 * Modifica un {@link Articulo} de la base de datos
	 * 
	 * @param a
	 *            {@link Articulo} que será modificado
	 */
	public void modificarArticulo(Articulo a);

}
