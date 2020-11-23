package co.jjortiz.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import co.jjortiz.aplicacion.JPAUtil;
import co.jjortiz.entidades.Ambiente;
import co.jjortiz.entidades.Novedad;
import co.jjortiz.entidades.Solicitud;

public class NovedadesDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();

	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}

	public String registrarNovedad(Novedad novedad) {		
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(novedad);
			entityManager.getTransaction().commit();
			resp="ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public List<Novedad> listarNovedades() {
		List<Novedad> listaNovedades = new ArrayList<Novedad>();
		Query query = entityManager.createQuery("SELECT n FROM Novedad n");
		listaNovedades = query.getResultList();
		return listaNovedades;
	}

	public List<Novedad> consultarListaNovedadesAmbiente(Ambiente numAmb) {
		List<Novedad> listaUsuarios=new ArrayList<Novedad>();
		Query query=entityManager.createQuery("SELECT n FROM Novedad n WHERE n.idAmbiente = :numAmbiente");
		query.setParameter("numAmbiente",numAmb);
		listaUsuarios=query.getResultList();
		return listaUsuarios;
	}

	public List<Novedad> listarNovedadesporfecha(java.util.Date dateInicio, java.util.Date dateFinal) {
		List<Novedad> listaNovedades = new ArrayList<Novedad>();
		try {
			String jpql = "SELECT n FROM Novedad n WHERE n.fechaHora BETWEEN :dateInicio AND :dateFinal";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("dateInicio",dateInicio);
			query.setParameter("dateFinal",dateFinal);
			listaNovedades = query.getResultList();
		} catch (Exception e) {
		}
		for (Novedad novedad : listaNovedades) {
			System.out.println(novedad.getIdNovedad());
		}
		return listaNovedades;
	}

	public List<Novedad> listarNovedadesporfechaAmbiente(java.util.Date fechaHorai, java.util.Date fechaHoraf, Ambiente idAmbiente) {
		List<Novedad> listaNovedades = new ArrayList<Novedad>();
		try {
			String jpql = "SELECT n FROM Novedad n WHERE n.idAmbiente.id = :numAmbiente AND n.fechaHora BETWEEN :dateInicio and :dateFinal ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("numAmbiente",idAmbiente);
			query.setParameter("dateInicio",fechaHorai);
			query.setParameter("dateFinal",fechaHoraf);
			listaNovedades = query.getResultList();
		} catch (Exception e) {
			listaNovedades=null;
		}
		return listaNovedades;
	}

	public String notificarNovedad(Solicitud solicitud) {
		String res = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(solicitud);
			entityManager.getTransaction().commit();
			res = "ok";
		} catch (Exception e) {
			res = "error";
		}
		return res;
	}

}
