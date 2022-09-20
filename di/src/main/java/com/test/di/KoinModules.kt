package com.test.di

import androidx.room.Room
import com.test.data.BuildConfig
import com.test.data.api.data_source.GenericNetSource
import com.test.data.api.service.GenericApi
import com.test.data.hasNetwork
import com.test.data.repository.GenericRepositoryImpl
import com.test.data.room.Database
import com.test.data.room.data_source.GenericDbSource
import com.test.domain.repository.GenericRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.data.api.data_source.NumberNetSource
import com.test.data.api.service.NumberApi
import com.test.data.repository.FactNumberImpl
import com.test.data.room.data_source.FactDBSource
import com.test.domain.repository.FactNumberRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val repositoriesModule = module {
    factory<GenericRepository> { GenericRepositoryImpl() }
    factory<FactNumberRepository> { FactNumberImpl(get(), get()) }

}

val dataProvidersModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, BuildConfig.DATABASE_NAME
        ).build()
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .cache(get<Cache>())
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(androidContext()))
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 5
                    ).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single<Gson> {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single<Cache> {
        val cacheSize = (5 * 1024 * 1024).toLong()
        Cache(androidContext().cacheDir, cacheSize)
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(ScalarsConverterFactory.create()).build()
    }
}

val sourcesModule = module {
    single { get<Database>().genericDao() }
    single { get<Database>().factDao() }
    single { get<Retrofit>().create(GenericApi::class.java) }
    single { get<Retrofit>().create(NumberApi::class.java) }
    single { GenericDbSource(get()) }
    single { GenericNetSource(get()) }
    single { NumberNetSource(get()) }
    single { FactDBSource(get()) }
}

