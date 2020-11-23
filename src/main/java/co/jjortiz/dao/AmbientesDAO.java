package co.jjortiz.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.jjortiz.aplicacion.JPAUtil;
import co.jjortiz.entidades.Ambiente;

public class AmbientesDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}
	
	public String crearAmbiente(Ambiente ambiente) {
		String res = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(ambiente);
			entityManager.getTransaction().commit();
			
			res = "ok";
		} catch (Exception e) {
			res = "error";
		}
		
		return res;
	}

	public String eliminarAmbiente(Ambiente miAmbiente) {

		String res = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(miAmbiente);
			entityManager.getTransaction().commit();
			
			res = "ok";
			
		} catch (Exception e) {
			res = "Problemas con la base de datos";
		}
		
		
		return res;
	}

	public List<Ambiente> obtenerListaAmbientes() {
		List<Ambiente> listaAmbientes = new ArrayList<Ambiente>();
		Query query=entityManager.createQuery("SELECT a FROM Ambiente a");
		listaAmbientes = query.getResultList();
		return listaAmbientes;
	}
	
	public Ambiente consultarAmbiente(String id) {
		Ambiente miAmbiente = entityManager.find(Ambiente.class, id);
		if (miAmbiente != null) {
			return miAmbiente;
		} else {
			return null;
		}
	}
	
	public Ambiente actualizarAmbiente(Ambiente ambiente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(ambiente);
			entityManager.getTransaction().commit();
			return ambiente;
		} catch (Exception e) {
			return null;
		}
	}






}
