package co.jjortiz.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.jjortiz.aplicacion.JPAUtil;
import co.jjortiz.entidades.Ambiente;
import co.jjortiz.entidades.Concepto;
import co.jjortiz.entidades.Solicitud;

public class SolicitudesDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;


	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}
	
	public String solicitudLlaves(Solicitud solicitud) {
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

	public List<Solicitud> obtenerListaSolicitud() {
		List<Solicitud>  listaSolicitudes =  new ArrayList<Solicitud>();
		Query query= entityManager.createQuery("SELECT s FROM  Solicitud s WHERE s.concepto.idConcepto = ?1 or s.concepto.idConcepto = ?2  ");
		query.setParameter(1,2);
		query.setParameter(2,4);
		listaSolicitudes = query.getResultList();
		return listaSolicitudes;
	}

	public List<Solicitud> obtenerSolicitudesAmbiente(String idAmbiente) {
		System.out.println(idAmbiente);
		List<Solicitud>  listaSolicitudes =  new ArrayList<Solicitud>();
		Query query = entityManager.createQuery("SELECT  s FROM  Solicitud s WHERE s.idAmbiente.id = :idAmbiente");
		query.setParameter("idAmbiente", idAmbiente);
		listaSolicitudes = query.getResultList();
		return listaSolicitudes;
	}
	
	public String solicitudNoveda(Solicitud solicitud) {
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
	

	
	public List<Solicitud> obtenerListaNovedades(int coneptoId,int coneptoId2 ) {
		List<Solicitud>  listaNovedades =  new ArrayList<Solicitud>();
		Query query= entityManager.createQuery("SELECT s FROM  Solicitud s WHERE s.concepto.idConcepto = ?1 or s.concepto.idConcepto = ?2");
		query.setParameter(1,coneptoId);
		query.setParameter(2,coneptoId2);
		listaNovedades = query.getResultList();
		return listaNovedades;
	}
	
	public Solicitud consultarSolicitud(Integer id) {
		Solicitud miSolicitud=entityManager.find(Solicitud.class,id);
		if (miSolicitud!=null) {
			return miSolicitud;
		}else {
			return null;
		}
	}

	public String actualizarSolicitud(Solicitud solicitud) {
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(solicitud);
			entityManager.getTransaction().commit();
			resp="ok";
		} catch (Exception e) {
			resp="No se puede actualizar la solicitud";
			
		}		
		return resp;
	}

	public String eliminarSolicitud(Solicitud miSolicitud) {
		String res = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(miSolicitud);
			entityManager.getTransaction().commit();
			res = "ok";
		} catch (Exception e) {
			res = "Problemas con la base de datos";
		}
		return res;
	}

	public List<Solicitud> obtenerListadoSolicitudes() {
		List<Solicitud>  listaNovedades =  new ArrayList<Solicitud>();
		Query query= entityManager.createQuery("SELECT s FROM  Solicitud s WHERE s.concepto.idConcepto = ?1");
		query.setParameter(1,1);
		listaNovedades = query.getResultList();
		return listaNovedades;
	}

}
