package org.example;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record SearchResponse(
        List<Repository> items

) {
}
@JsonIgnoreProperties(ignoreUnknown = true)
record Repository(
        String name,
        String description,
        String language,
        @JsonProperty("stargazers_count") int stargazersCount
){}