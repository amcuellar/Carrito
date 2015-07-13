package co.asw.integracion.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import co.asw.entidades.Articulo;
import co.asw.integracion.ArticulosDAOLocal;

@Stateless
@LocalBean
public class ArticulosDAO implements ArticulosDAOLocal {

	@PersistenceContext
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	public void guardarArticulo(Articulo a) {
		StoredProcedureQuery queryProc = em
				.createStoredProcedureQuery("PKG_ARTICULOS.CREAR_ARTICULO");

		queryProc.registerStoredProcedureParameter(1, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(2, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(3, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(4, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(5, Integer.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(6, Integer.class,
				ParameterMode.IN);

		queryProc.setParameter(1, a.getCategoria());
		queryProc.setParameter(2, a.getNombre());
		queryProc.setParameter(3, a.getMarca());
		queryProc.setParameter(4, a.getTipo());
		queryProc.setParameter(5, a.getCilindraje());
		queryProc.setParameter(6, a.getPrecioBase());

		queryProc.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Articulo> listarArticulos() {
		List<Articulo> gatosConsultados = new ArrayList<Articulo>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ARTICULOS");
			Query query = em.createNativeQuery(sb.toString());

			List<Object[]> listaResultadoConsulta = null;
			listaResultadoConsulta = query.getResultList();

			for (Object[] iterador : listaResultadoConsulta) {
				// Obtener el ID en un entero
				String articuloPK = iterador[0].toString();
				BigDecimal cilindrajeBD = (BigDecimal) iterador[5];
				BigDecimal precioBD = (BigDecimal) iterador[6];

				Articulo nuevoArticulo = new Articulo(articuloPK, iterador[1]
						.toString(), iterador[2].toString(), iterador[3]
						.toString(), iterador[4].toString(), cilindrajeBD
						.intValue(), precioBD.intValue());

				gatosConsultados.add(nuevoArticulo);
			}

		} catch (PersistenceException e) {
			System.out.println("Excepcion al cargar");
		}
		return gatosConsultados;
	}

	/**
	 * {@inheritDoc}
	 */
	public void borrarArticulo(Articulo a) {
		StoredProcedureQuery queryProc = em
				.createStoredProcedureQuery("PKG_ARTICULOS.BORRAR_ARTICULO");

		queryProc.registerStoredProcedureParameter(1, String.class,
				ParameterMode.IN);

		queryProc.setParameter(1, a.getReferencia());
		queryProc.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	public void modificarArticulo(Articulo a) {
		StoredProcedureQuery queryProc = em
				.createStoredProcedureQuery("PKG_ARTICULOS.MODIFICAR_ARTICULO");

		queryProc.registerStoredProcedureParameter(1, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(2, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(3, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(4, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(5, String.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(6, Integer.class,
				ParameterMode.IN);
		queryProc.registerStoredProcedureParameter(7, Integer.class,
				ParameterMode.IN);

		queryProc.setParameter(1, a.getReferencia());
		queryProc.setParameter(2, a.getCategoria());
		queryProc.setParameter(3, a.getNombre());
		queryProc.setParameter(4, a.getMarca());
		queryProc.setParameter(5, a.getTipo());
		queryProc.setParameter(6, a.getCilindraje());
		queryProc.setParameter(7, a.getPrecioBase());

		queryProc.execute();
	}
}
