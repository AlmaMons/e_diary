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

import com.iktpreobuka.e_diary.entities.TeacherEntity;
import com.iktpreobuka.e_diary.entities.dto.TeacherDTO;
import com.iktpreobuka.e_diary.services.TeacherServiceImpl;
import com.iktpreobuka.e_diary.util.RESTError;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherServiceImpl teacherService;

	// GET ALL
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
		List<TeacherDTO> teacherDto = new ArrayList<>();
		List<TeacherEntity> teachers = teacherService.getAllTeachers();

		for (TeacherEntity t : teachers) {
			teacherDto.add(new TeacherDTO(t));
		}
		return new ResponseEntity<>(teacherDto, HttpStatus.OK);
	}

	// GET BY ID
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Long id) {

		try {
			TeacherEntity teacher = teacherService.findTeacherById(id);
			TeacherDTO dto = new TeacherDTO(teacher);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Teacher not found!"), HttpStatus.NOT_FOUND);
		}
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveTeacher(@Valid @RequestBody TeacherDTO teacherDTO, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				TeacherEntity teacher = new TeacherEntity(teacherDTO);
				TeacherDTO newTeacherDto = new TeacherDTO(teacherService.saveTeacher(teacher));
				return new ResponseEntity<>(newTeacherDto, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(("Teacher alredy exist!"), HttpStatus.BAD_REQUEST);
			}
		}
	}

	// PUT, da li treba dozvoliti da menja i predmete
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateItem(@Valid @RequestBody TeacherDTO teacherDTO, BindingResult result,
			@PathVariable("id") Long id) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				TeacherEntity teacher = new TeacherEntity(teacherDTO);
				TeacherDTO updateTeacher = new TeacherDTO(teacherService.updateTeacher(teacher, id));
				return new ResponseEntity<>(updateTeacher, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(("Error has occured!"), HttpStatus.BAD_REQUEST);
			}
		}
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
		try {
			if (teacherService.removeTeacher(id)) {
				return new ResponseEntity<RESTError>(new RESTError("Delete successfully!"), HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError("Teacher not found!"), HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Can't delete that teacher"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
