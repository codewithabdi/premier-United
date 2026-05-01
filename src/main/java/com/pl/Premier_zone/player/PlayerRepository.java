package com.pl.Premier_zone.player;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByTeam(String team);
    List<Player> findByNationContainingIgnoreCase(String nation);
    List<Player> findByPosContainingIgnoreCase(String pos);
    List<Player> findByNameContainingIgnoreCase(String name);
    List<Player> findByTeamAndPos(String team, String pos);
}