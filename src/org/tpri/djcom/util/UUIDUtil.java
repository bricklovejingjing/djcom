package org.tpri.djcom.util;

/**
 * @description 生成UUID的工具类
 * @author 易文俊
 * @since 2015-04-09
 */
public class UUIDUtil {

	/**
	 * 获取一个随机的GUID
	 * @return
	 */
    public static String id(){
        return java.util.UUID.randomUUID().toString().toUpperCase();
    }
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(UUIDUtil.id().length());
		}
	}
}
