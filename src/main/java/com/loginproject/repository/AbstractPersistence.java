package com.loginproject.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.loginproject.model.AbstractEntity;

public abstract class AbstractPersistence<T extends AbstractEntity, PK extends Number> {

	@Inject
	private EntityManager manager;

	private Class<T> entityClass;

	public AbstractPersistence(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T save(T e) {
		if (e.getId() != null) {
			this.manager.getTransaction().begin();
			T t = getEntityManager().merge(e);
			this.manager.flush();
			this.manager.getTransaction().commit();
			return t;
		} else {
			this.manager.getTransaction().begin();
			getEntityManager().persist(e);
			this.manager.flush();
			this.manager.getTransaction().commit();
			return e;
		}
	}

	public void remove(T entity) {
		this.manager.getTransaction().begin();
		getEntityManager().remove(getEntityManager().merge(entity));
		this.manager.flush();
		this.manager.getTransaction().commit();
	}

	public T find(PK id) {
		return getEntityManager().find(entityClass, id);
	}

	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	protected abstract EntityManager getEntityManager();

}
