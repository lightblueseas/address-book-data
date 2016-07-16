package de.alpharogroup.address.book.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import lombok.Getter;
import lombok.Setter;

@Repository("zipcodesDao")
public class ZipcodesDao extends JpaEntityManagerDao<Zipcodes, Integer>{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The entity manager. */
	@PersistenceContext
	@Getter
	@Setter
	private EntityManager entityManager;
}
