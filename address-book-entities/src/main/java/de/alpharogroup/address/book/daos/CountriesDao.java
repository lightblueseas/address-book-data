package de.alpharogroup.address.book.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;

@Repository("countriesDao")
public class CountriesDao extends JpaEntityManagerDao<Countries, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 814480996099196488L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;		
	}
}
