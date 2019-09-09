package com.findalldays.api.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.findalldays.api.entities.Category;
import com.findalldays.api.services.CategoryService;

@RestController
public class CategoryCotroller {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/category")
	@ResponseBody
	public ArrayList<Category> findAll() {
		ArrayList<Category> category = categoryService.findAll();
		return category;
	}
}
