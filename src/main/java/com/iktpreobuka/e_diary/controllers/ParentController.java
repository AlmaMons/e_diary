package com.iktpreobuka.e_diary.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.e_diary.entities.ParentEntity;
import com.iktpreobuka.e_diary.entities.dto.ParentDTO;
import com.iktpreobuka.e_diary.services.ParentServiceImpl;
import com.iktpreobuka.e_diary.util.RESTError;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	private ParentServiceImpl parentService;

	// GET
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ParentDTO>> getAllParents() {
		List<ParentDTO> parentsDto = new ArrayList<>(); 
		List<ParentEntity> parents =  parentService.getAllParents();
			
			for (ParentEntity p : parents) {
				parentsDto.add(new ParentDTO(p));
			}

		return new ResponseEntity<>(parentsDto, HttpStatus.OK);
	}

	// GET BY ID
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getParentById(@PathVariable Long id) {
		
		try {
			ParentEntity p = parentService.findParentById(id);
			ParentDTO dto = new ParentDTO(p);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Parent not found!"), HttpStatus.NOT_FOUND);
		}
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveParent(@Valid @RequestBody ParentDTO parentDto, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				ParentEntity parent = new ParentEntity(parentDto);
				ParentEntity saved = parentService.saveParent(parent);
				ParentDTO newParentDto = new ParentDTO(saved);
				return new ResponseEntity<>(newParentDto, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(("Parent alredy exist!"), HttpStatus.BAD_REQUEST);
			}
		}
	}

	// PUT
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateItem(@Valid @RequestBody ParentDTO parentDTO, BindingResult result, @PathVariable("id") Long id) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				ParentEntity parent = new ParentEntity(parentDTO);
				ParentEntity updated = parentService.updateParent(parent, id);
				ParentDTO updateParent = new ParentDTO(updated);
				return new ResponseEntity<>(updateParent, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(("Error has occured!"), HttpStatus.BAD_REQUEST);
			}
		}
	}


	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteParent(@PathVariable Long id) {
		try {
			if(parentService.removeParent(id))
				{return new ResponseEntity<RESTError>(new RESTError("Delete successfully!"), HttpStatus.OK);}
			else {
				return new ResponseEntity<RESTError>(new RESTError("Parent not found!"), HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Id not valid"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}



}
