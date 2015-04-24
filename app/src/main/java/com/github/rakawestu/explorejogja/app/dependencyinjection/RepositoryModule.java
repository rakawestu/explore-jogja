package com.github.rakawestu.explorejogja.app.dependencyinjection;

import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.ResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.ExploreJogjaApiResponseMapper;
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
    public ResponseMapper provideResponseMapper() {
        return new ExploreJogjaApiResponseMapper();
    }

    @Provides
    @Named("production_api")
    public RetrofitExploreJogjaApiRepository provideExploreJogjaRepository(ResponseMapper responseMapper, @Named("api_base_url") String endpoint) {
        return new RetrofitExploreJogjaApiRepository(endpoint, responseMapper);
    }

    @Provides
    @Named("mock_api")
    public ExploreJogjaRepository provideExploreJogjaMockRepository() {
        return new ExploreJogjaMockRepository();
    }
}
