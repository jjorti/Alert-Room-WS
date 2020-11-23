package co.jjortiz.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import co.jjortiz.aplicacion.JPAUtil;
import co.jjortiz.entidades.Ambiente;
import co.jjortiz.entidades.Articulo;
import co.jjortiz.entidades.InventarioAmbiente;
import co.jjortiz.vo.InformacionInventario;

public class InventarioAmbienteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();
	

	public String registrarIventarioAmbiente(String idAmbiente, List<InformacionInventario> listaArticulos) {
		String res = "";
		Articulo miArticulo = null;
		InventarioAmbiente miInventarioAmbiente = null;
		if (listaArticulos.size()>0) {
			for (InformacionInventario informacionInventario : listaArticulos) {
				miInventarioAmbiente = new InventarioAmbiente();
				Ambiente miAmbiente = new Ambiente();
				miAmbiente.setId(idAmbiente);
				miInventarioAmbiente.setIdAmbiente(miAmbiente);
				miArticulo = new Articulo();
				miArticulo.setNombre(informacionInventario.getNombre());
				miInventarioAmbiente.setIdArticulo(miArticulo);
				miInventarioAmbiente.setCantidad(informacionInventario.getCantidad());
				
				try {
					entityManager.getTransaction().begin();
					entityManager.persist(miInventarioAmbiente);
					entityManager.getTransaction().commit();
					res = "ok";
				} catch (Exception e) {
					res = "error";
					return res;
				}
			}
		}else {
			res = "error";
			return  res;
		}
		return res;
	}
	
	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}
}
