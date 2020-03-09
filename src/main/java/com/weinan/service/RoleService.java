package com.weinan.service;

import java.math.BigInteger;
import java.util.List;

import com.weinan.entity.Role;

public interface RoleService {
	
	public List<Role> getUserRole(BigInteger userId);

}
