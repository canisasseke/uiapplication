package com.zopenlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.models.User;

@Repository
public interface IUserDao extends JpaRepository<User, String> {

}
