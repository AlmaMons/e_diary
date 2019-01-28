package com.iktpreobuka.e_diary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.iktpreobuka.e_diary.entities.ParentEntity;
import com.iktpreobuka.e_diary.entities.dto.ParentDTO;
import com.iktpreobuka.e_diary.repositories.ParentRepository;

public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentRepository parentRepo;

	@Override
	public ParentDTO saveParentDto(ParentDTO parentDto) {
		ParentEntity parent = new ParentEntity(parentDto);
		return ;
	}

	
//	  public boolean deleteParent(Long id) {
//	        ParentEntity c = findOne(id);
//	        if (c != null) {
//	            remove(id);
//	            return true;
//	        } else {
//	            return false;
//	        }
//	    }
}
