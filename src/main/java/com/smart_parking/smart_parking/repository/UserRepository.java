package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.dto.UserResponse;
import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    boolean existsByRole(UserRole role);
}
