package com.example.universityCanteenManagement.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menuCategories")
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private MenuCategory parentCategory;

    @JsonManagedReference
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MenuCategory> categories;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MenuItem> menuItems;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
}
