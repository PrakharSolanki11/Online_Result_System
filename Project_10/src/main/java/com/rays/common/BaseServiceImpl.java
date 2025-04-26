package com.rays.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dto.UserDTO;

@Service
@Transactional
public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	protected D baseDao;

	@Transactional(readOnly = false)
	public long add(T dto) {

		long pk = baseDao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto) {

		baseDao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T dto) {
		baseDao.delete(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T findByPk(Long pk) {
		T dto = baseDao.findByPk(pk);
		return dto;
	}

}
