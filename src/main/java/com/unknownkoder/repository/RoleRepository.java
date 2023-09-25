package com.unknownkoder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.unknownkoder.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
     Optional<Role> findByAuthority(String authority);
}
