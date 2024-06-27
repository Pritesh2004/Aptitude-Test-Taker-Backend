package com.aptitude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptitude.entity.Category;
import com.aptitude.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    // Add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            Category addedCategory = categoryService.addCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);
        } catch (Exception e) {
            logger.error("Error adding category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding category");
        }
    }

    // Get category
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            Category category = categoryService.getCategory(categoryId);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            logger.error("Error retrieving category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving category");
        }
    }

    // Get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        try {
            return ResponseEntity.ok(categoryService.getCategories());
        } catch (Exception e) {
            logger.error("Error retrieving categories: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving categories");
        }
    }

    // Update category
    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            logger.error("Error updating category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating category");
        }
    }

    // Delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().body("Category deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting category");
        }
    }
}
