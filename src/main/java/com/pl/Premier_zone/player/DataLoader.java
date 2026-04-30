package com.pl.Premier_zone.player;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    public DataLoader(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Only load the CSV if the database is empty
        if (playerRepository.count() > 0) {
            return;
        }

        ClassPathResource resource = new ClassPathResource("prem_stats.csv");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))) {

            // Skip header row
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length < 15) {
                    continue;
                }

                Player player = new Player(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        parseInteger(data[3]),
                        parseInteger(data[4]),
                        parseInteger(data[5]),
                        parseDouble(data[6]),
                        parseDouble(data[7]),
                        parseDouble(data[8]),
                        parseDouble(data[9]),
                        parseDouble(data[10]),
                        parseDouble(data[11]),
                        parseDouble(data[12]),
                        parseDouble(data[13]),
                        data[14].trim()
                );

                playerRepository.save(player);
            }
        }
    }

    private Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value.trim());
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
    }
}