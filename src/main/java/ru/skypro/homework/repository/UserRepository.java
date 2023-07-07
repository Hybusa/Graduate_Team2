package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto,Long> {
}
