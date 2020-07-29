package br.com.everis.applicant.conf;

import br.com.everis.applicant.constants.Constants;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CaffeineCache centersCache() {
        return new CaffeineCache(Constants.CENTERS_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache countrysCache() {
        return new CaffeineCache(Constants.COUNTRYS_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache manageHomeOfficesCache() {
        return new CaffeineCache(Constants.MANAGE_HOME_OFFICE_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache positionsCache() {
        return new CaffeineCache(Constants.POSITION_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache professionalsCache() {
        return new CaffeineCache(Constants.PROFESSIONAL_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache profilesCache() {
        return new CaffeineCache(Constants.PROFILE_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache programmingLanguagesCache() {
        return new CaffeineCache(Constants.PROGRAMMING_LANGUAGE_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache projectsCache() {
        return new CaffeineCache(Constants.PROJECT_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache teamsCache() {
        return new CaffeineCache(Constants.TEAM_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache usersCache() {
        return new CaffeineCache(Constants.USER_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache documentRegionsCache() {
        return new CaffeineCache(Constants.DOCUMENT_REGIONS_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache personTypesCache() {
        return new CaffeineCache(Constants.PERSON_TYPES_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache applicantsCache() {
        return new CaffeineCache(Constants.APPLICANT_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }

    @Bean
    public CaffeineCache applicantEvaluationsCache() {
        return new CaffeineCache(Constants.APPLICANT_EVALUATION_IN_CACHE,
                Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
    }
}


