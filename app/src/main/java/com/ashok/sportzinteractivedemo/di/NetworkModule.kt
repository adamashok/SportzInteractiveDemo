
package com.ashok.sportzinteractivedemo.di

import com.ashok.sportzinteractivedemo.network.NetworkUtils
import com.ashok.sportzinteractivedemo.network.SportzAPI
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
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpIntercetor() =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
           .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,retrofitBuilder: Retrofit.Builder) =

        retrofitBuilder.baseUrl(NetworkUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideYelpAPI(retrofit: Retrofit): SportzAPI = retrofit.create(SportzAPI::class.java)

}