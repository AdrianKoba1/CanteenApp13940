package com.example.universityCanteenManagement.repository;

import com.example.universityCanteenManagement.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByNameAndMenuCategory_Id(String name, Long categoryId);



}
