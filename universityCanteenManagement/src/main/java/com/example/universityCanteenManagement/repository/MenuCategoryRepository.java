package com.example.universityCanteenManagement.repository;

import com.example.universityCanteenManagement.domain.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {

    Optional<MenuCategory> findByNameAndBranch_Id(String name, Long branchId);


}
