package com.pl.Premier_zone.player;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository repo;

    public DataLoader(PlayerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {

        // prevent reloading if already loaded
        if (repo.count() > 0) {
            System.out.println("Data already loaded");
            return;
        }

        ClassPathResource resource = new ClassPathResource("prem_stats.csv");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
        );

        reader.readLine(); // skip header

        String line;

        while ((line = reader.readLine()) != null) {

            try {
                // 🔥 FIX: handles commas inside quotes
                String[] d = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // 🔥 clean quotes and spaces
                for (int i = 0; i < d.length; i++) {
                    d[i] = d[i].replace("\"", "").trim();
                }

                // skip bad rows
                if (d.length < 15) continue;

                Player p = new Player(
                        d[0],  // name
                        d[1],  // nation
                        d[2],  // pos
                        parseInteger(d[3]),
                        parseInteger(d[4]),
                        parseInteger(d[5]),
                        parseDouble(d[6]),
                        parseDouble(d[7]),
                        parseDouble(d[8]),
                        parseDouble(d[9]),
                        parseDouble(d[10]),
                        parseDouble(d[11]),
                        parseDouble(d[12]),
                        parseDouble(d[13]),
                        d[14]   // team
                );

                repo.save(p);

            } catch (Exception e) {
                // 🔥 skip broken rows instead of crashing
                System.out.println("Skipped row: " + line);
            }
        }

        reader.close();

        System.out.println("✅ CSV LOADED SUCCESSFULLY");
    }

    // 🔥 SAFE parsing (no crashes)
    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }
}