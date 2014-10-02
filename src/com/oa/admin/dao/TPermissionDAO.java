package com.oa.admin.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oa.admin.pojo.TPermission;
import com.oa.admin.pojo.TRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * TPermission entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oa.admin.pojo.TPermission
 * @author MyEclipse Persistence Tools
 */

public class TPermissionDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TPermissionDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String URL = "url";
	public static final String BUTTIONID = "buttionid";
	public static final String PARENTID = "parentid";

	protected void initDao() {
		// do nothing
	}

	public List<TPermission> queryPermissionByRoleId(Integer id){
		String hql = "select p from TRole r left join r.permissions p where r.id = ?";
		Session session  = getHibernateTemplate().getSessionFactory().getCurrentSession();
		List<TPermission> permissions = session.createQuery(hql).setParameter(0, id).list();
		return permissions;
	}
	
	public void save(TPermission transientInstance) {
		log.debug("saving TPermission instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TPermission persistentInstance) {
		log.debug("deleting TPermission instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TPermission findById(java.lang.Long id) {
		log.debug("getting TPermission instance with id: " + id);
		try {
			TPermission instance = (TPermission) getHibernateTemplate().get(
					"com.oa.admin.pojo.TPermission", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TPermission instance) {
		log.debug("finding TPermission instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TPermission instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TPermission as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByButtionid(Object buttionid) {
		return findByProperty(BUTTIONID, buttionid);
	}

	public List findByParentid(Object parentid) {
		return findByProperty(PARENTID, parentid);
	}

	public List findAll() {
		log.debug("finding all TPermission instances");
		try {
			String queryString = "from TPermission";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TPermission merge(TPermission detachedInstance) {
		log.debug("merging TPermission instance");
		try {
			TPermission result = (TPermission) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TPermission instance) {
		log.debug("attaching dirty TPermission instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TPermission instance) {
		log.debug("attaching clean TPermission instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TPermissionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TPermissionDAO) ctx.getBean("TPermissionDAO");
	}
}