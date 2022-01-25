package com.game.repository;

import com.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {
boolean existsById(Long id);
    void deleteById(Long id);
}
