package co.jjortiz.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import co.jjortiz.aplicacion.JPAUtil;

public class ArticulosDAO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();
	
	
	
	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}

}
