package com.itthisak.office.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.itthisak.office.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer>{
    List<Project> findByNameContaining(String name);
}
