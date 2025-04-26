package com.rays.common;

import com.rays.dto.UserDTO;

public interface BaseServiceInt<T extends BaseDTO> {
	
	
	public long add(T dto);
	
	public void update(T dto);
	
	public void delete(T dto);
	
	public T findByPk(Long pk);

	

}
