package org.tpri.djcom.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.tpri.djcom.dao.condition.DaoPara;

/**
 * @description 数据库基本操作接口类
 * @author 易文俊
 * @since 2015-04-02
 */
public interface BaseDAO<T> {
	
	/**
    * 保存
    * @param objectBase
    * @return
    */
	public Serializable save(T objectBase);
	/**
	    * 保存或更新
    * @param objectBase
    * @return
    */
	public Serializable saveOrUpdate(T objectBase);
	
	
	/**
	 * 删除
	 * @param objectBase
	 * @return
	 */
	public void delete(T objectBase);

	/**
	 * 条件删除
	 * @param daoPara
	 */
	public void delete(DaoPara daoPara);

	/**
	 * 批量删除
	 * @param objectBaseList
	 */
	public void deleteBatch(List<T> objectBaseList);

	
	/**
	 * 原生sql 删除
	 * @param sql
	 * @param params
	 */
   public void delete(String sql, Object[] params);

	/**
	 * 保存或更新
	 * @param objectBase
	 */
	public void update(T objectBase);

	/**
	 * 条件更新
	 * @param daoPara
	 */
	public Boolean update(DaoPara daoPara);
	
	/**
	 * 批量更新
	 * @param objectBaseList
	 */
	public void updateBatch(List<T> objectBaseList);
	/**
	 * 获取一个对象
	 * @param daoPara
	 * @return
	 */
	public Object loadOne(DaoPara daoPara);
	
	/**
	 * 返回符合条件的记录总数
	 * @param daoPara
	 * @return
	 */
	public Integer getTotalCount(DaoPara daoPara);
	/**
	 * 返回符合条件的记录
	 * @param hql
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<T> loadList(String hql, Integer start, Integer limit);
	/**
	 * 返回符合条件的记录
	 * @param hql
	 * @param start
	 * @param limit
	 * @param params
	 * @return
	 */
	public List<T> loadList(String hql, Integer start, Integer limit, Object[] params);
	/**
	 * 返回符合条件的记录
	 * @param daoPara
	 * @return
	 */
	public List<T> loadList(DaoPara daoPara);
   
	/**
	 * 原生sql 新增
	 * @param sql
	 * @param params
	 */
   public void saveNative(String sql, Object[] params);
   
   /**
    * 原生sql 修改
    * @param sql
    * @param params
    */
   public void updateNative(String sql, Object[] params);
   
   /**
    * 原生sql 删除
    * @param sql
    * @param params
    */
   public void deleteNative(String sql, Object[] params);
   
	/**
	 * 原生sql获取整型值,一般用来获取记录总数
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public int getNativeTotalCount(String sql, Object[] parameters);

	/**
	 * 原生sql分页查询指定排序规则
	 * @param sql
	 * @param parameters
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @param clazz
	 * @return
	 */
   public List<Object> loadNative(String sql, Object[] parameters, Integer start, Integer limit, String orderBy, Class clazz);
	
   /**
	 * 原生sql分页查询指定排序规则,支持指定返回list中的元素是map还是array
	 * @param sql
	 * @param parameters
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @param clazz
	 * @param elementAsMap 返回的list中的元素是否需是map
	 * @return
	 */
  public List<Object> loadNative(String sql, Object[] parameters, Integer start, Integer limit, String orderBy, Class clazz, boolean elementAsMap);

   /**
    * 原生sql分页查询不指定排序规则
    * @param sql
    * @param params
    * @param start
    * @param limit
    * @param clazz
    * @return
    */
   public List<Object> loadNative(String sql, Object[] params, int start, int limit, Class clazz);
   
   /**
    * 原生sql参数带参数查询
    * @param sql
    * @param params
    * @param clazz
    * @return
    */
   public List<Object> loadNative(String sql, Object[] params, Class clazz);
   
   /**
    * 原声sql查询
    * @param sql
    * @param clazz
    * @return
    */
   public List<Object> loadNative(String sql, Class clazz);

	/**
	 * 原生sql获取整型值
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public int queryNativeForInt(String sql, Object[] parameters);
	
	/**
	 * 获取数据库类型，对应枚举在 BaseConstants.DATABASE_TYPE_XXX 定义
	 * @return
	 */
	public int getDbType();

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection();
    
    /**
	 * 原生sql 删除
	 * @param sql
	 * @param params
	 */
   public Session getCurrentSession();

}
