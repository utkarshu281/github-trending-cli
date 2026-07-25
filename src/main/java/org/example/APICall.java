package org.example;
//https://api.github.com/search/repositories?q=created:>2026-07-17&sort=stars&order=desc&per_page=10
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.*;


public class APICall {
    private final Main.Duration DURATION;
    private final int LIMIT;
    APICall(Main.Duration duration, int limit){
    this.DURATION=duration;
        this.LIMIT=limit;
    }
    public LocalDate calculateDare(){
        LocalDate localDate = LocalDate.now();
        return switch (DURATION) {
            case Main.Duration.MONTH -> localDate.minusMonths(1);
            case Main.Duration.WEEK -> localDate.minusWeeks(1);
            case Main.Duration.DAY -> localDate.minusDays(1);
            case Main.Duration.YEAR -> localDate.minusYears(1);
        };
    }
    public void call(){
        String query="created:>"+this.calculateDare();
        String encodedURL;
        try {
             encodedURL = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request;
        String url= "https://api.github.com/search/repositories?q="+encodedURL
                +"&sort=stars&order=desc&per_page="+LIMIT;

            try {
                request = HttpRequest.newBuilder().uri(new URI(url))
                        .header("Accept", "application/vnd.github+json")
                        .header("X-GitHub-Api-Version", "2022-11-28")
                        .header("User-Agent", "GitHubTrendingCLI")
                        .GET().build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        HttpResponse<String> response;


            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.statusCode());
                System.out.println(response.body());
                //parser function
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }
}
//TODO: check api code status and body