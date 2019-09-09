package com.findalldays.api.implement;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findalldays.api.entities.Category;
import com.findalldays.api.repository.CategoryRepository;
import com.findalldays.api.services.CategoryService;

@Service
public class CategoryImplements implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public ArrayList<Category> findAll() {
		return (ArrayList<Category>) categoryRepository.findAll();
	}
}
