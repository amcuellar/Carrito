package co.asw.negocio.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.asw.entidades.Articulo;
import co.asw.integracion.ArticulosDAOLocal;
import co.asw.negocio.CarritoEJBLocal;

@Stateless
@LocalBean
public class CarritoEJB implements CarritoEJBLocal {

	@EJB
	private ArticulosDAOLocal dao;

	/**
	 * {@inheritDoc}
	 */
	public void agregarArticulo(Articulo a) {
		dao.guardarArticulo(a);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Articulo> consultarArticulos() {
		return dao.listarArticulos();
	}

	/**
	 * {@inheritDoc}
	 */
	public void borrarArticulo(Articulo a) {
		dao.borrarArticulo(a);
	}

	/**
	 * {@inheritDoc}
	 */
	public void modificarArticulo(Articulo a) {
		dao.modificarArticulo(a);
		;
	}

}
