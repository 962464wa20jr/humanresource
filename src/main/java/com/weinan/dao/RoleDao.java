package com.weinan.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weinan.entity.Role;

@Repository
public interface RoleDao {
	
	public List<Role> getUserRole(BigInteger userId);

}
