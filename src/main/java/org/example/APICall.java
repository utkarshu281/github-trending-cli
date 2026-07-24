package org.example;
//https://api.github.com/search/repositories?q=created:>2026-07-17&sort=stars&order=desc&per_page=10
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class APICall {
    private Main.Duration duration;
    private int limit;
    APICall(Main.Duration duration, int limit){
        this.duration=duration;
        this.limit=limit;
    }
    public void call(){
        LocalDate localDate = LocalDate.now();
        LocalDate date=null;
        switch(duration){
            case Main.Duration.MONTH:
                date=localDate.minusMonths(1);
                break;
            case Main.Duration.WEEK:
                date=localDate.minusWeeks(1);
                break;
            case Main.Duration.DAY:
                date=localDate.minusDays(1);
                break;
            case Main.Duration.YEAR:
                date=localDate.minusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Something, isn't right here");
        }
        String url="https://api.github.com/search/repositories?q=created:>"+date.now()+"&sort=stars&order=desc&per_page="+limit;
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request;

        {
            try {
                request = HttpRequest.newBuilder().uri(new URI(url))
                        .header("Accept", "application/vnd.github+json")
                        .header("X-GitHub-Api-Version", "2022-11-28")
                        .header("User-Agent", "GitHubTrendingCLI")
                        .GET().build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        HttpResponse<String> response;

        {
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                //parser function
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
