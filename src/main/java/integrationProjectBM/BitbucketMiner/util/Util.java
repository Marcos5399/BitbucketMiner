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
    public static <T> List<T> getPaginatedBitbucketResources(
            String endpointUrl,
            Integer maxPages,
            Class<? extends PaginatedResponse<T>> responseClass,
            RestTemplate restTemplate){

        List<T> results = new ArrayList<>();
        String nextPageUrl = endpointUrl;
        int pagesRetrieved = 0;

        while (nextPageUrl != null && (maxPages == null || pagesRetrieved < maxPages)) {
            PaginatedResponse<T> response = restTemplate.exchange(
                    nextPageUrl,
                    HttpMethod.GET,
                    null,
                    responseClass
            ).getBody();

            if (response == null) break;

            results.addAll(response.getValues());
            nextPageUrl = response.getNext();
            pagesRetrieved++;

            System.out.println("Página " + pagesRetrieved + " recuperada con éxito. " +
                    "Elementos obtenidos: " + response.getValues().size() +
                    (nextPageUrl != null ? " | Hay más páginas" : " | Última página alcanzada"));
        }

            System.out.println("Paginación completada. Total de páginas recuperadas: " + pagesRetrieved +
                ". Total de elementos: " + results.size());

        return results;
    }
}

