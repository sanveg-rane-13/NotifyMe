package com.app.notifyme.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.repositories.ErrorRepository;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.services.AdminService;
import com.app.notifyme.models.Error;
import com.app.notifyme.models.ErrorResponse;
import com.app.notifyme.models.Verdict;

@RestController
public class AdminController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ErrorRepository errorRepository;
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(path="/getErrors",method = RequestMethod.GET)
	public List<ErrorResponse>  getAllErrors() {
		
		List<ErrorResponse> response=new ArrayList<>();
		List<Error> server_response=errorRepository.findByVerdict(Verdict.UNRESOLVED.getVerdictStatus());
		for(Error error:server_response){
			ErrorResponse er=new ErrorResponse();
			er.setId(error.getErrorId());
			er.setErrorType(error.getErrorType());
			er.setVerdict(error.getVerdict());
			er.setProductName(error.getProduct().getProductName());
			er.setNewUrl("");
			er.setNewXpath("");
			response.add(er);
		}
		return response;
	}
	
	
	
	@RequestMapping(path="/resolveError",method=RequestMethod.POST,consumes="application/json")
	public String resolveError(@RequestBody ErrorResponse errorResponse){
		System.out.println("errorResponse: "+errorResponse);
        adminService.fixFaulty(errorResponse);
		return "fixed the fault";
	}
	
	/*@RequestMapping(path="/AddError",method=RequestMethod.POST)
	public String addError(@RequestBody Error error){
		errorRepository.save(error);
		return "added the error";
	}*/
	

}
