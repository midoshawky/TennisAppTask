package com.shawky.zimozitennisapptask.data.di
import com.ihsanbal.logging.LoggingInterceptor
import com.ihsanbal.logging.Level
import com.shawky.zimozitennisapptask.data.services.Services
import com.shawky.zimozitennisapptask.shared.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.annotation.Nullable

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .log(Platform.WARN)
            .request(" |==Req==|  ")
            .response(" |==Response==|  ")
            .addQueryParam("query", "0")
            .build()

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): Services {
        return retrofit.create(Services::class.java)
    }

}