package org.tpri.djcom.core;

import java.util.HashMap;

/**
 * @description 对象注册类
 * @author 易文俊
 * @since 2015-04-02
 */
public class ObjectRegister extends ObjectBase{
	/** */
	private static final long serialVersionUID = 7063348702353386363L;

	private static HashMap<Integer, ObjectRegister> objectRegisters = new HashMap<Integer, ObjectRegister>(); 
	
	private Class clazz;
	private int objectClassType;

	
    public ObjectRegister(int objectClassType, Class clazz){
    	objectType = ObjectType.OBJECTREGISTER;
    	this.objectClassType = objectClassType;
    	this.clazz = clazz;
    }
    
    public static ObjectRegister getObjectRegister(int objectClassType){
        return objectRegisters.get(objectClassType);
    }
    
    public static void addRegister(ObjectRegister objectRegister){
		objectRegisters.put(objectRegister.getObjectClassType(), objectRegister);
    }
    
    public static void registerClass(int objectClassType, Class clazz){
    	addRegister(new ObjectRegister(objectClassType, clazz));
    }
   
    /**
     * 根据类型获取对应的java类
     * @param type
     * @return
     */
    public static Class getClassByClassType(int type) {
    	ObjectRegister register = objectRegisters.get(type);
    	if (register != null) {
			return register.getClazz();
		}
    	return null;
    }

    public int getObjectClassType() {
        return objectClassType;
    }

	public Class getClazz() {
		return clazz;
	}
}
