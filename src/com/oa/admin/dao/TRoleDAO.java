package com.oa.admin.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oa.admin.pojo.TRole;

/**
 * A data access object (DAO) providing persistence and search support for TRole
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.oa.admin.pojo.TRole
 * @author MyEclipse Persistence Tools
 */

public class TRoleDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TRoleDAO.class);
	// property constants
	public static final String ROLENAME = "rolename";

	protected void initDao() {
		// do nothing
	}

	public 	List<TRole> queryRolebyUserId(Integer id){
		String hql = "select r from TUser u left join u.roles r where u.id = ?";
		Session session  = getHibernateTemplate().getSessionFactory().getCurrentSession();
		List<TRole> roles = session.createQuery(hql).setParameter(0, id).list();
		return roles;
	}
	//得到权限ID
	public List<TRole> getButtonid(int roleid){
		String hql = "select distinct r  from TRole r left join fetch r.permissions  where r.id = ? ";
		Session session  = getHibernateTemplate().getSessionFactory().getCurrentSession();
		List<TRole> roles = session.createQuery(hql).setParameter(0, roleid).list();
		return roles;
	}

	public void save(TRole transientInstance) {
		log.debug("saving TRole instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TRole persistentInstance) {
		log.debug("deleting TRole instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TRole findById(java.lang.Short id) {
		log.debug("getting TRole instance with id: " + id);
		try {
			TRole instance = (TRole) getHibernateTemplate().get(
					"com.oa.admin.pojo.TRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TRole instance) {
		log.debug("finding TRole instance by example");
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
		log.debug("finding TRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TRole as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRolename(Object rolename) {
		return findByProperty(ROLENAME, rolename);
	}

	public List findAll() {
		log.debug("finding all TRole instances");
		try {
			String queryString = "from TRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TRole merge(TRole detachedInstance) {
		log.debug("merging TRole instance");
		try {
			TRole result = (TRole) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TRole instance) {
		log.debug("attaching dirty TRole instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TRole instance) {
		log.debug("attaching clean TRole instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	//总数
		public Integer  count() {
			log.debug("finding all TRole instances");
			try {
				String queryString = "select count(*) from TRole";
				return ((Long)getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString).uniqueResult()).intValue();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		/**
		 * 分页方法
		 * @return
		 */
		public List findAllByPage(TRole role,String pageNumber,String pageSize) {
			log.debug("finding all TRole instances");
			try {
				String queryString = "from TRole order by id";
				Integer page = null!=pageNumber?Integer.parseInt(pageNumber):0;
				Integer size = null!=pageSize?Integer.parseInt(pageSize):10;
				Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
				return session.createQuery(queryString).setFirstResult((page-1)*size).setMaxResults(size).list();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
	public static TRoleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TRoleDAO) ctx.getBean("TRoleDAO");
	}
}