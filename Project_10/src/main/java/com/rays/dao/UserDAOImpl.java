package com.rays.dao;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.UserDTO;

@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDTO> implements UserDAOInt{

	@Override
	public Class<UserDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return UserDTO.class;
	}
	
	 
	
}
