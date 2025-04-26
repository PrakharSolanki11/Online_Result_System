package com.rays.common;

import com.rays.dto.UserDTO;

public interface BaseDAOInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public void delete(T dto);

	public T findByPk(Long pk);

	public T findByUniqueKey(String attribute, Object val);

}
