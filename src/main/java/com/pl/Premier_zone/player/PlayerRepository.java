package com.pl.Premier_zone.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This interface talks to the database
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    // Delete a player by name
    void deleteByName(String playerName);

    // Find one player by exact name
    Optional<Player> findByName(String name);

    // Find all players from one team
    List<Player> findByTeam(String team);

    // Search by nation, ignoring upper/lower case
    List<Player> findByNationContainingIgnoreCase(String nation);

    // Search by position, ignoring upper/lower case
    List<Player> findByPosContainingIgnoreCase(String pos);

    // Search by name, ignoring upper/lower case
    List<Player> findByNameContainingIgnoreCase(String name);

    // Find players by exact team and exact position
    List<Player> findByTeamAndPos(String team, String pos);
}