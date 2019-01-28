package com.iktpreobuka.e_diary.controllers;

import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.e_diary.entities.ParentEntity;
import com.iktpreobuka.e_diary.entities.dto.ParentDTO;
import com.iktpreobuka.e_diary.services.ParentServiceImpl;



@RestController
@RequestMapping ( path = "/api/v1/parents")
public class ParentController {

	
	@Autowired
	private ParentServiceImpl parentService;
	
	//GET
	
	//POST
	@RequestMapping (method = RequestMethod.POST)
	public ResponseEntity<ParentDTO> saveParent(@RequestBody ParentDTO parentDto){
//		if (parentDto == null) {
//			return ResponseEntity<ParentDTO>();
//		}
//
//		if (newUser.getFirstName() == null || newUser.getLastName() == null || newUser.getUsername() == null
//				|| newUser.getPassword() == null) {
//			return null;
//		}   validacija sa valid i gen. msg
		return new ResponseEntity<>(parentService.saveParentDto(parentDto), HttpStatus.CREATED);
	}
	
	//PUT
	
	
	
	//DELETE
//	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//	public ResponseEntity<?> deleteBill(@PathVariable Integer id) {
//
//		if (!billRepository.findById(id).isPresent()) {
//			return new ResponseEntity<RESTError>(new RESTError("Bill with provided ID not found"),
//					HttpStatus.NOT_FOUND);
//		}
//
//		BillEntity bill = billRepository.findById(id).get();
//		billRepository.deleteById(id);
//		return new ResponseEntity<BillEntity>(bill, HttpStatus.OK);
//	}
	
//	 @DeleteMapping(value = "/{id}")
//	    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
//	        if (cenovnikService.deleteCenovnik(id)) return new ResponseEntity<>(HttpStatus.OK);
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	
//	public ResponseEntity<?> deleteParent (Integer id){
//		if (parentService.)
//		return new ResponseEntity<ParentEntity>(HttpStatus.OK);
//	}
	
}
