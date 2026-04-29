package com.pl.Premier_zone.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// This class holds the main logic between controller and repository
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Get all players
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // Get players from one team
    public List<Player> getPlayersFromTeam(String teamName) {
        return playerRepository.findByTeam(teamName);
    }

    // Search players by name
    public List<Player> getPlayersByName(String searchText) {
        return playerRepository.findByNameContainingIgnoreCase(searchText);
    }

    // Search players by position
    public List<Player> getPlayersByPos(String searchText) {
        return playerRepository.findByPosContainingIgnoreCase(searchText);
    }

    // Search players by nation
    public List<Player> getPlayersByNation(String searchText) {
        return playerRepository.findByNationContainingIgnoreCase(searchText);
    }

    // Get players by team and position
    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findByTeamAndPos(team, position);
    }

    // Add a new player
    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    // Update a player if that player already exists
    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName());

        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();

            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPos(updatedPlayer.getPos());
            playerToUpdate.setNation(updatedPlayer.getNation());

            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }

        return null;
    }

    // Delete a player by name
    @Transactional
    public void deletePlayer(String playerName) {
        playerRepository.deleteByName(playerName);
    }
}