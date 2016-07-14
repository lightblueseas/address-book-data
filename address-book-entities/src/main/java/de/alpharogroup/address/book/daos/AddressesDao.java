package de.alpharogroup.address.book.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;

import lombok.Getter;
import lombok.Setter;

@Repository("addressesDao")
public class AddressesDao extends JpaEntityManagerDao<Addresses, Integer>{

	private static final long serialVersionUID = 5693868415897202295L;

	@PersistenceContext
	@Getter
	@Setter
	private EntityManager entityManager;

}
