package com.pl.Premier_zone.player;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {

        if (team != null && position != null) return service.getPlayersByTeamAndPosition(team, position);
        if (team != null) return service.getPlayersFromTeam(team);
        if (name != null) return service.getPlayersByName(name);
        if (position != null) return service.getPlayersByPos(position);
        if (nation != null) return service.getPlayersByNation(nation);

        return service.getPlayers();
    }
}