package org.example.springboot_study_alone.repository;

import org.example.springboot_study_alone.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
}
