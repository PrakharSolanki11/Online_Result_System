package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseService.authenticate(form.getLoginId(), form.getPassword());

		if (dto == null) {
			res.setSuccess(false);
			res.addMessage("LoginId and Password Invalid");
		} else {
			res.setSuccess(true);
			res.addData(dto);
			res.addResult("fName", dto.getFirstName());
			res.addResult("lName", dto.getLastName());
			res.addResult("login", dto.getLoginId());
			res.addResult("role", dto.getRoleName());
		}

		return res;

	}

	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseService.findByLogin(form.getLoginId());
		if (dto != null) {
			res.setSuccess(false);
			res.addMessage("LoginId already registered");
			return res ;
		} 

			dto = new UserDTO();

			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setLoginId(form.getLoginId());
			dto.setPassword(form.getPassword());
			dto.setGender(form.getGender());
			dto.setDob(form.getDob());
			dto.setPhone(form.getPhone());
			dto.setRoleId(2L);
			dto.setRoleName("Student");

			Long pk = baseService.register(dto);
			res.setSuccess(true);
			res.addMessage("User Registered Successfully....");
		
		return res;
	}

}
