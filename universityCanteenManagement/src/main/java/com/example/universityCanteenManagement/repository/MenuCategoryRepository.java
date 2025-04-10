package com.example.universityCanteenManagement.repository;

import com.example.universityCanteenManagement.domain.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
