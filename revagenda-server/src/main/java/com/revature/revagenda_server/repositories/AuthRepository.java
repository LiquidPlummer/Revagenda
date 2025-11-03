package com.revature.revagenda_server.repositories;

import com.revature.revagenda_server.models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
}
