package com.example.mealsdatabase.di

import com.example.mealsdatabase.data.model.remote.ApiDetails
import com.example.mealsdatabase.data.model.remote.ApiRequest
import com.example.mealsdatabase.data.model.repository.Repository
import com.example.mealsdatabase.data.model.repository.RepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        provideOkHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideOkHttpClient)
            .build()
    }

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): ApiRequest{
        return retrofit.create(ApiRequest::class.java)
    }

    @Provides
    fun provideRepository(
        apiRequest: ApiRequest
    ): Repository{
        return RepositoryImpl(apiRequest)
    }
}