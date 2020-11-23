package co.jjortiz.aplicacion;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final String UNIDAD_DE_PERSISTENCIA = "AlertRoom";
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null || !factory.isOpen()) {
			factory= Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA);
		}
		return factory;
	}
	
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
			
		}
	}
	
}
