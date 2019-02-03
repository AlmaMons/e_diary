package com.iktpreobuka.e_diary.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.e_diary.entities.ParentEntity;
import com.iktpreobuka.e_diary.entities.StudentEntity;
import com.iktpreobuka.e_diary.entities.dto.StudentDTO;
import com.iktpreobuka.e_diary.repositories.StudentRepository;
import com.iktpreobuka.e_diary.services.ParentServiceImpl;
import com.iktpreobuka.e_diary.services.StudentServiceImpl;
import com.iktpreobuka.e_diary.util.RESTError;

@RestController
@RequestMapping ( path = "/api/v1/students")
public class StudentController {
	
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
	private StudentRepository srepo;
	
	@Autowired
	private ParentServiceImpl parentService;
	
	//GET
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<StudentDTO> studentsDto = new ArrayList<>(); 
		List<StudentEntity> students =  studentService.getAllStudents();
			
			for (StudentEntity s : students) {
				studentsDto.add(new StudentDTO(s));
			}

		return new ResponseEntity<>(studentsDto, HttpStatus.OK);
	}
	
	//GET BY ID
	
	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				ParentEntity p = parentService.findParentById(studentDTO.getParentID());
				StudentEntity s = new StudentEntity(studentDTO, p);
				StudentDTO newStudent = new StudentDTO(srepo.save(s)); //da bude servis
				return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(("Student already exists"), HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	//PUT
	
	//DELETE

}
