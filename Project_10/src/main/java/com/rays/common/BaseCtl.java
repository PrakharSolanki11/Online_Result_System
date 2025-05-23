package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {
	
	@Autowired		
	protected S baseService;

	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {
			res.setSuccess(false);
		

		Map<String, String> errors = new HashMap<String, String>();

		List<FieldError> list = bindingResult.getFieldErrors();

		list.forEach(e -> {
			errors.put(e.getField(), e.getDefaultMessage());
		});

		res.addInputErrors(errors);
		
		}

		return res;
	}
}
