package de.alpharogroup.address.book.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;

@Repository("federalstatesDao")
public class FederalstatesDao extends JpaEntityManagerDao<Federalstates, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3937865183860303611L;

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
