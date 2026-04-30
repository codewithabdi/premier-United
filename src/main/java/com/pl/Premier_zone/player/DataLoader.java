package com.pl.Premier_zone.player;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    public DataLoader(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) {

        // Only add sample data if the database is empty
        if (playerRepository.count() == 0) {

            playerRepository.save(new Player("Erling Haaland", "NOR", "FW", 25, 31, 28,
                    2480.0, 27.0, 5.0, 6.0, 2.0, 0.0, 28.4, 4.1, "Manchester City"));

            playerRepository.save(new Player("Mohamed Salah", "EGY", "FW", 33, 32, 30,
                    2700.0, 18.0, 10.0, 5.0, 3.0, 0.0, 20.1, 9.3, "Liverpool"));

            playerRepository.save(new Player("Bukayo Saka", "ENG", "FW", 24, 30, 29,
                    2600.0, 14.0, 9.0, 4.0, 4.0, 0.0, 15.7, 8.8, "Arsenal"));

            playerRepository.save(new Player("Cole Palmer", "ENG", "MF", 23, 33, 29,
                    2650.0, 22.0, 11.0, 9.0, 7.0, 0.0, 18.9, 10.2, "Chelsea"));

            playerRepository.save(new Player("Bruno Fernandes", "POR", "MF", 31, 34, 34,
                    3000.0, 10.0, 8.0, 5.0, 8.0, 0.0, 11.4, 9.6, "Manchester United"));

            playerRepository.save(new Player("Son Heung-min", "KOR", "FW", 33, 32, 31,
                    2750.0, 17.0, 10.0, 2.0, 1.0, 0.0, 15.8, 8.1, "Tottenham"));

            playerRepository.save(new Player("Alexander Isak", "SWE", "FW", 26, 30, 27,
                    2400.0, 21.0, 3.0, 5.0, 2.0, 0.0, 19.5, 3.2, "Newcastle United"));

            playerRepository.save(new Player("Ollie Watkins", "ENG", "FW", 30, 35, 34,
                    3100.0, 19.0, 13.0, 0.0, 4.0, 0.0, 17.9, 10.5, "Aston Villa"));

            playerRepository.save(new Player("Declan Rice", "ENG", "MF", 27, 34, 33,
                    2950.0, 7.0, 8.0, 0.0, 5.0, 0.0, 5.8, 6.9, "Arsenal"));

            playerRepository.save(new Player("Virgil van Dijk", "NED", "DF", 34, 32, 32,
                    2880.0, 3.0, 2.0, 0.0, 4.0, 1.0, 3.2, 1.4, "Liverpool"));
        }
    }
}