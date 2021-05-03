package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacysupply.model.JwtResponse;

@FeignClient(name = "authorization-service", url = "localhost:8084")
public interface AuthFeignClient {

	@RequestMapping(value = "/api/auth/validate", method = RequestMethod.GET)
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
