package io.bootify.user_management_app.repos;

import io.bootify.user_management_app.domain.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TokensRepository extends JpaRepository<Tokens, Long> {
}
