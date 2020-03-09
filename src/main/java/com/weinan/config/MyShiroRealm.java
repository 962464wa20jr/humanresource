package com.weinan.config;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.weinan.entity.Permission;
import com.weinan.entity.Role;
import com.weinan.entity.User;
import com.weinan.service.PermissionService;
import com.weinan.service.RoleService;
import com.weinan.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//如果身份认证的时候没有传入user对象，这里就只能取到userName
		//即在构造SimpleAuthorizationInfo的时候第一个参数需要user对象
		User user = (User)principals.getPrimaryPrincipal();
		
		//查询用户角色 用户--角色 多对多
		List<Role> roles = roleService.getUserRole(user.getId());
		for(Role role: roles) {
			authorizationInfo.addRole(role.getRole());
			//根据角色查询权限
			List<Permission> permissions = permissionService.getRolePermission(role.getId());
			for(Permission permission: permissions) {
				authorizationInfo.addStringPermission(permission.getPermission());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//获取用户输入的账号
		String userName = (String)token.getPrincipal();
		User user = userService.findUserByStaffCode(userName);
		if(Objects.isNull(user)) {
			return null;
		}
		System.out.println(user.getCredentialsSalt());
		return new SimpleAuthenticationInfo(
				//这里传入的是user对象，对比的是用户名，传入用户名也没错，但是在授权部分就需要再去数据库找user对象
				user,
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				getName()
				);
	}

}
