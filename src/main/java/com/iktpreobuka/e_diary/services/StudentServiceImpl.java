package com.iktpreobuka.e_diary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.e_diary.entities.StudentEntity;
import com.iktpreobuka.e_diary.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;

	//GET ALL
	@Override
	public List<StudentEntity> getAllStudents() {
		List<StudentEntity> students = (List<StudentEntity>) studentRepo.findAll();
		return students;
	}

}
