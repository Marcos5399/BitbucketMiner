package integrationProjectBM.BitbucketMiner.util;


import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class Util {
    // Método genérico para obtener recursos paginados de Bitbucket
    public static <T> List<T> getPaginatedResources(
            RestTemplate restTemplate,
            String username,
            String token,
            String initialUrl,
            ParameterizedTypeReference<PaginatedResponse<T>> responseType,
            Integer maxPages) {

        List<T> results = new ArrayList<>();
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String nextPageUrl = initialUrl;
        int currentPage = 1;
        int pagesNumber = (maxPages != null) ? maxPages : 2;

        while (nextPageUrl != null && currentPage <= pagesNumber) {
            HttpEntity<Void> request = new HttpEntity<>(headers);
            ResponseEntity<PaginatedResponse<T>> response = restTemplate.exchange(
                    nextPageUrl, HttpMethod.GET, request, responseType
            );
            PaginatedResponse<T> body = response.getBody();
            if (body != null && body.getValues() != null) {
                results.addAll(body.getValues());
            }
            nextPageUrl = (body != null) ? body.getNext() : null;
            System.out.println("Página consultada: " + currentPage); // <--- AQUI
            currentPage++;
        }

        return results;
    }
}

