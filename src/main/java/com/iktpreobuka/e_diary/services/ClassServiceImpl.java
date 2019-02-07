package com.iktpreobuka.e_diary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.e_diary.entities.ClassEntity;
import com.iktpreobuka.e_diary.repositories.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepo;

	// GET ALL
	@Override
	public List<ClassEntity> getAllClasses() {
		List<ClassEntity> classes = (List<ClassEntity>) classRepo.findAll();
		return classes;
	}

	// GET BY ID
	@Override
	public ClassEntity findClassById(Long id) {
		Optional<ClassEntity> c = classRepo.findById(id);
		if (c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}

	// POST
	@Override
	public ClassEntity saveClass(ClassEntity clas) {
		ArrayList<ClassEntity> classes = classRepo.findAllBySchoolYear(clas.getSchoolYear());

		if (classes.size() == 0) {
			return classRepo.save(clas);
		}

		for (ClassEntity c : classes) {
			if (c.getClas().equals(clas.getClas()) && c.getDepartment() == clas.getDepartment()) {
				return null;
			}
		}
		return classRepo.save(clas);

	}

	// PUT
	public ClassEntity editClass(ClassEntity clas, Long id) {
		try {
			Optional<ClassEntity> op = classRepo.findById(id);
			ClassEntity c = op.get();
			c.updateClass(clas);
			return classRepo.save(c);
		} catch (Exception e) {
			return null;
		}
	}

	// DELETE
	@Override
	public boolean removeClass(Long id) {
		Optional<ClassEntity> c = classRepo.findById(id);
		if (c.isPresent()) {
			classRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	// List of all classes ids for put method in subject controller
		@Override
		public ArrayList<ClassEntity> getAllClassesByID(ArrayList<Long> ids) {
			ArrayList<ClassEntity> classes = new ArrayList<>();

			for (Long classId : ids) {
				Optional<ClassEntity> indbClasses = classRepo.findById(classId);
				if (indbClasses.isPresent()) {
					classes.add(indbClasses.get());
				} else {
					return null;
				}
			}
			return classes;
		}

}
