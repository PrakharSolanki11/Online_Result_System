package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {



	public UserDTO findByLogin(String login) {
		return baseDao.findByUniqueKey("loginId", login);
	}

	public UserDTO authenticate(String login, String password) {

		UserDTO dto = findByLogin(login);

		if (dto != null) {
			if (password.equals(dto.getPassword())) {
				return dto;
			}
		}

		return null;
	}
	
	public long register(UserDTO dto) {
		long pk = add(dto);
		return pk;
	}

}
