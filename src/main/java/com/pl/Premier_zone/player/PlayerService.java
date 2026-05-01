package com.pl.Premier_zone.player;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    public List<Player> getPlayers() {
        return repo.findAll();
    }

    public List<Player> getPlayersFromTeam(String team) {
        return repo.findByTeam(team);
    }

    public List<Player> getPlayersByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Player> getPlayersByPos(String pos) {
        return repo.findByPosContainingIgnoreCase(pos);
    }

    public List<Player> getPlayersByNation(String nation) {
        return repo.findByNationContainingIgnoreCase(nation);
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String pos) {
        return repo.findByTeamAndPos(team, pos);
    }

    public Player addPlayer(Player p) {
        return repo.save(p);
    }
}