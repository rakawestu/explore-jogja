package com.github.rakawestu.explorejogja.app.dependencyinjection;

import com.github.rakawestu.explorejogja.domain.repository.api.mapper.CategoryResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.ExploreJogjaApiSubCategoryResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.PlaceResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.ExploreJogjaApiCategoryResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.ExploreJogjaApiPlaceResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.SubCategoryResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.mock.ExploreJogjaMockRepository;
import com.github.rakawestu.explorejogja.domain.repository.api.retrofit.RetrofitExploreJogjaApiRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author rakawm
 */
@Module(
        complete = false,
        library = true
)
public class RepositoryModule {
    @Provides
    @Named("api_base_url")
    public String provideApiBaseUrl() {
        return "http://urkk59580ecb.rakawestu.koding.io:8000";
    }

    @Provides
    public PlaceResponseMapper provideResponseMapper() {
        return new ExploreJogjaApiPlaceResponseMapper();
    }

    @Provides
    public CategoryResponseMapper provideCategoryMapper() {
        return new ExploreJogjaApiCategoryResponseMapper();
    }

    @Provides
    public SubCategoryResponseMapper provideSubCategoryMapper() {
        return new ExploreJogjaApiSubCategoryResponseMapper();
    }

    @Provides
    @Named("production_api")
    public RetrofitExploreJogjaApiRepository provideExploreJogjaRepository(CategoryResponseMapper categoryResponseMapper,
                                                                           PlaceResponseMapper responseMapper,
                                                                           SubCategoryResponseMapper subCategoryResponseMapper,
                                                                           @Named("api_base_url") String endpoint) {
        return new RetrofitExploreJogjaApiRepository(endpoint, responseMapper, categoryResponseMapper, subCategoryResponseMapper);
    }

    @Provides
    @Named("mock_api")
    public ExploreJogjaRepository provideExploreJogjaMockRepository() {
        return new ExploreJogjaMockRepository();
    }
}
