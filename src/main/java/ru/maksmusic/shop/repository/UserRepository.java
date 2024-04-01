package ru.maksmusic.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksmusic.shop.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
