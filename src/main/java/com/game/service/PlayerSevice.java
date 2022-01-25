package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerDAO;
import javassist.NotFoundException;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PlayerSevice {
    Player findOne(Long id) throws NotFoundException;

    Integer levelCalculation(Integer experience);
    @Transactional
    List<Player> findAll();

    Integer getCount(Map<String, String> params);
    List<Player> getAll(Map<String, String> params);

    Player save(Player player);

    void deleteById(Long id);


    boolean existsById(Long id);

    ResponseEntity<Player> findById(Long id);
    ResponseEntity<Player> update(Long id, Player player);
}
