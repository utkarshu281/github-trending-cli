package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SearchResponse parseRepositories(String json) {
        try {
            return objectMapper.readValue(json, SearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse GitHub response.", e);
        }
    }

    public void print(SearchResponse searchResponse) {

        int id = 1;

        for (Repository repository : searchResponse.items()) {

            String description = repository.description() == null
                    ? "No description available."
                    : repository.description();

            String language = repository.language() == null
                    ? "Unknown"
                    : repository.language();

            System.out.println("----------------------------------------------");
            System.out.printf("#%d %s%n%n", id++, repository.name());
            System.out.printf("Description : %s%n", description);
            System.out.printf("Language    : %s%n", language);
            System.out.printf("Stars       : %,d%n", repository.stargazersCount());
            System.out.println("----------------------------------------------");
        }
    }
}