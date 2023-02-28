package com.example.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdemo.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
