package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class APICall {

    private final Main.Duration duration;
    private final int limit;
    private final Parser parser;
    private final HttpClient client = HttpClient.newHttpClient();

    public APICall(Main.Duration duration, int limit, Parser parser) {
        this.duration = duration;
        this.limit = limit;
        this.parser = parser;
    }

    private LocalDate calculateDate() {
        LocalDate today = LocalDate.now();

        return switch (duration) {
            case DAY -> today.minusDays(1);
            case WEEK -> today.minusWeeks(1);
            case MONTH -> today.minusMonths(1);
            case YEAR -> today.minusYears(1);
        };
    }

    private String buildUrl() {

        String query = "created:>" + calculateDate();
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        return "https://api.github.com/search/repositories?q="
                + encodedQuery
                + "&sort=stars"
                + "&order=desc"
                + "&per_page="
                + limit;
    }

    private HttpRequest buildRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .header("User-Agent", "GitHubTrendingCLI")
                .GET()
                .build();
    }

    public void call() {

        HttpRequest request = buildRequest(buildUrl());

        try {

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "GitHub API returned HTTP " + response.statusCode());
            }

            SearchResponse searchResponse =
                    parser.parseRepositories(response.body());

            parser.print(searchResponse);

        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to GitHub.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Request was interrupted.", e);
        }
    }
}