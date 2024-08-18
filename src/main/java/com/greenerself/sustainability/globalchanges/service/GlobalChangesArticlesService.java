package com.greenerself.sustainability.globalchanges.service;

import com.greenerself.sustainability.globalchanges.entity.Article;
import com.greenerself.sustainability.globalchanges.repo.ArticleRepository;
import com.greenerself.sustainability.util.ApiUrlBuilder;
import com.greenerself.sustainability.util.api.GenericAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.greenerself.sustainability.constants.ApplicationConstants.APIConstants.NEWSAPI_BASE_URL;
import static com.greenerself.sustainability.constants.ApplicationConstants.APIConstants.NEWSAPI_KEY;

@Service
public class GlobalChangesArticlesService {
    private final GenericAPIService apiService;
    @Autowired
    public GlobalChangesArticlesService(GenericAPIService apiService) {
        this.apiService = apiService;
    }

    @Autowired
    ArticleRepository articleRepository;

    List<String> keywords = Arrays.asList("Environmental", "ESG", "Global Warming", "Sustainability", "Biodiversity", "Pollution", "Sustainable", "Green Technology", "Eco-friendly", "Conservation", "Carbon Emissions", "Renewable", "Climate Change", "Solar");

    private Set<String> seenArticles = new HashSet<>();

    private int requestCount = 0;
    private final int maxRequestsPerDay = 100;

    public void fetchArticles(String fromDate, String toDate) {
        for (String keyword : keywords) {
            if (requestCount >= maxRequestsPerDay) {
                // TODO: Implement logic to wait until the next day or throttle the requests.
                break;
            }

            int page = 1;
            while (true) {
                if (requestCount >= maxRequestsPerDay) break;

                Map<String, String> queryParams = new HashMap<>();
                queryParams.put("q", keyword);
                queryParams.put("from", fromDate);
                queryParams.put("to", toDate);
                queryParams.put("sortBy", "publishedAt");
                queryParams.put("pageSize", "100");
                queryParams.put("page", String.valueOf(page));

                String apiUrl = ApiUrlBuilder.buildUrl(NEWSAPI_BASE_URL, NEWSAPI_KEY, queryParams);
                List<Article> articles = Arrays.asList(apiService.getForObject(apiUrl, Article[].class));

                requestCount++;

                if (articles == null || articles.isEmpty()) {
                    break;
                }

                for (Article article : articles) {
                    if (!seenArticles.contains(article.getSourceUrl())) {
                        seenArticles.add(article.getSourceUrl());
                        articleRepository.save(article);
                    }
                }
                page++;
            }
        }
    }

}
