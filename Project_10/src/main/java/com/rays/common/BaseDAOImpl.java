package com.rays.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public long add(T dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(T dto) {
		entityManager.merge(dto);
	}

	public void delete(T dto) {
		entityManager.remove(dto);
	}

	public T findByPk(Long pk) {
		T dto = entityManager.find(getDTOClass(), pk);
		return dto;
	}

	@Override
	public T findByUniqueKey(String attribute, Object val) {
		System.out.println("findByUniqueKey in BaseDaoImp (no UserContext)");
		Class<T> dtoClass = getDTOClass();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(dtoClass);
		Root<T> qRoot = cq.from(dtoClass);

		// Only filter by the given attribute and value
		Predicate condition = builder.equal(qRoot.get(attribute), val);
		cq.where(condition);

		TypedQuery<T> query = entityManager.createQuery(cq);
		List<T> list = query.getResultList();

		T dto = null;
		if (!list.isEmpty()) {
			dto = list.get(0);
		}

		return dto;
	}

	public abstract Class<T> getDTOClass();

}
