package org.tpri.djcom.service.sys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.sys.Navigation;
import org.tpri.djcom.manager.sys.NavigationManager;
/**
 * @description 导航栏服务类
 * @author 易文俊
 * @since 2015-04-06
 */

@Service("NavigationService")
public class NavigationService {
	@Resource(name="NavigationManager")
	NavigationManager navigationManager;
	
	public List<Navigation> loadNavigations(String type,String parentCode,HashSet<String> allPrivilegeIds){
		List<Navigation> returnNavigations=new ArrayList<Navigation>();
		List<Navigation> navigations=navigationManager.loadNavigationByTypeAndParentCode(type, parentCode);
		//过滤没有权限的导航
		for(Navigation navigation:navigations){
			if(allPrivilegeIds.contains(navigation.getPrivilegeId())){
				returnNavigations.add(navigation);
			};
		}
		return returnNavigations;
	}
}
