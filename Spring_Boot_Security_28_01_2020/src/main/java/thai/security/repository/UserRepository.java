package thai.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import thai.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
