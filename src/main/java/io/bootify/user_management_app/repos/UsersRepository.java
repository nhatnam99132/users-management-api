package io.bootify.user_management_app.repos;

import io.bootify.user_management_app.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
}
