package com.zopenlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.models.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, String> {

}
