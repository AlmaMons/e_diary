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

import com.iktpreobuka.e_diary.entities.SchoolYearEntity;
import com.iktpreobuka.e_diary.entities.SemesterEntity;
import com.iktpreobuka.e_diary.entities.dto.SemesterDTO;
import com.iktpreobuka.e_diary.services.SchoolYearServiceImpl;
import com.iktpreobuka.e_diary.services.SemesterServiceImpl;
import com.iktpreobuka.e_diary.util.RESTError;

@RestController
@RequestMapping(path = "/api/v1/semesters")
public class SemesterController {

	@Autowired
	private SchoolYearServiceImpl yearService;

	@Autowired
	private SemesterServiceImpl semesterService;

	// GET ALL
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SemesterDTO>> getAllSemesters() {
		List<SemesterDTO> semestersDto = new ArrayList<>();
		List<SemesterEntity> semesters = semesterService.getAllSemesters();

		for (SemesterEntity s : semesters) {
			semestersDto.add(new SemesterDTO(s));
		}
		return new ResponseEntity<>(semestersDto, HttpStatus.OK);
	}

	// GET BY ID
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getSemesterById(@PathVariable Long id) {

		try {
			SemesterEntity s = semesterService.findSemesterById(id);
			SemesterDTO dto = new SemesterDTO(s);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Semester not found!"), HttpStatus.NOT_FOUND);
		}
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveSemester(@Valid @RequestBody SemesterDTO semesterDTO, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				SchoolYearEntity sy = yearService.findYearById(semesterDTO.getSchoolYearID());
				if (sy == null) {
					return new ResponseEntity<>(("School year doesn't exist!"), HttpStatus.BAD_REQUEST);
				}
				SemesterEntity s = new SemesterEntity(semesterDTO, sy);
				SemesterDTO newSemester = new SemesterDTO(semesterService.saveSemester(s));
				return new ResponseEntity<>(newSemester, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(("Semester alredy exists!"), HttpStatus.BAD_REQUEST);
			}
		}
	}

	// PUT
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateItem(@Valid @RequestBody SemesterDTO semesterDTO, BindingResult result,
			@PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(RESTError.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			try {
				SchoolYearEntity sy = yearService.findYearById(semesterDTO.getSchoolYearID());
				if (sy == null) {
					return new ResponseEntity<>(("Year doesn't exist!"), HttpStatus.BAD_REQUEST);
				}
				SemesterEntity semester = new SemesterEntity(semesterDTO, sy);
				SemesterEntity updated = semesterService.updateSemester(semester, id);
				SemesterDTO updateSemester = new SemesterDTO(updated);
				return new ResponseEntity<>(updateSemester, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(("Error has occured!"), HttpStatus.BAD_REQUEST);
			}
		}
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteSemester(@PathVariable Long id) {
		try {
			if (semesterService.removeSemester(id)) {
				return new ResponseEntity<RESTError>(new RESTError("Delete successfully!"), HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError("Semester not found!"), HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Can't delete that semester"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
