package org.tpri.djcom.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/** * @description 缓存接口类 * @author 易文俊 * @since 2015-06-19 */
public interface MemoryCacheInterface {
	
	 public void addObject(ObjectBase object);
	 public void addObjects(ArrayList<ObjectBase> list, int objectType);
	 public void addObjects(List list, int objectType);
	 public void addObjects(HashMap<String, ObjectBase> map, int objectType);
	 public void addObject(String key, Object object);
	 public List<ObjectBase> sortList(int objectType);
	 public ObjectBase getObject(int objectType, String objectID);
	 public Object getObject(String key);
	 public ArrayList<ObjectBase> getObjectList(int objectType);
	 public SortedHashMap getObjectMap(int objectType);
	 public void removeObject(ObjectBase object);
	 public void clear();
	 public void clearObject(int objectType);
	 /**
     * 单条记录更新操作
     */
	 public void update(ObjectBase objectBase);
	 /**
     * 批量更新操作
     */
	 public void update(HashMap<String, ObjectBase> map);
	 /**
     * 批量更新操作
     */
     public void update(List<ObjectBase> list);
     
}
