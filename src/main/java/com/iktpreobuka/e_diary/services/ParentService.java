package com.iktpreobuka.e_diary.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.e_diary.entities.dto.ParentDTO;

public interface ParentService {
	
	//public boolean deleteParent(Long id) {}
	public ParentDTO saveParentDto(ParentDTO parentDto);
}
