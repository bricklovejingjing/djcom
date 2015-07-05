package org.tpri.djcom.core;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.tpri.djcom.dao.JdbcDao;
import org.tpri.djcom.entity.sys.EnvironmentId;

import java.util.Arrays;
import java.util.Map;
/**
 * @description 缓存工厂类
 * @author 易文俊
 * @since 2015-06-19
 */
public class MemoryCacheFactory {
	private static Logger logger = Logger.getLogger(MemoryCacheFactory.class);
	
	public static final int CACHE_LOCAL = 0; 		//本地缓存，暂时未用上
	public static final int CACHE_MEMECACHE = 1; 	//memcached缓存

	/** memcached所在的服务器地址及端口 */
	private static String[] memcacheHosts = new String[] {"127.0.0.1:11211"};
	
	private static MemoryCacheInterface instanceLocalCache;
	private static MemoryCacheInterface instanceMemecache;
	
	/** 初始化，从环境变量中读取mc的服务器ip*/
	public static void initialize() {
		String sql = "select VALUE from SYS_ENVIRONMENT where ID = ?";
		JdbcDao dao = new JdbcDao();
		try {
			String envName = EnvironmentId.URL_MEMCACHE.toString();
			Map map = dao.queryOne(sql, new Object[] {envName});
			if(map != null) {
				String value = (String) map.get("VALUE");
				if (StringUtils.isNotBlank(value)) {
					memcacheHosts = value.split(",");
				}
			}
		} catch (Exception e) {
			logger.error("error: ", e);
		} 
		logger.debug("Memcached Hosts: " + Arrays.toString(memcacheHosts));
	}
	
	public static MemoryCacheInterface getInstance(int cacheType) {
		if(cacheType == CACHE_LOCAL) {
			if(instanceLocalCache == null) {
				instanceLocalCache = new MemoryCache();
			}
			return instanceLocalCache;
		} else {
			if(instanceMemecache == null) {
				instanceMemecache = new Memcached(memcacheHosts);
			}
			return instanceMemecache;
		}
	}

	public static String[] getMemcacheHosts() {
		return memcacheHosts;
	}

	public static void setMemcacheHosts(String memcacheHostString) {
		MemoryCacheFactory.memcacheHosts = memcacheHostString.split(",");
	}
}
