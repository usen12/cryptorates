package com.makhabatusen.coinrates.feature_coin.di

import android.app.Application
import androidx.room.Room
import com.makhabatusen.coinrates.feature_coin.data.remote_source.CoinGeckoApi
import com.makhabatusen.coinrates.feature_coin.data.local_source.CoinDao
import com.makhabatusen.coinrates.feature_coin.data.local_source.CoinDatabase
import com.makhabatusen.coinrates.feature_coin.data.repository.CoinsRepositoryImpl
import com.makhabatusen.coinrates.feature_coin.domain.repository.CoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CoinGeckoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRateApi(retrofit: Retrofit): CoinGeckoApi {
        return retrofit.create(CoinGeckoApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinsRepository(api: CoinGeckoApi, db: CoinDatabase): CoinsRepository {
        return CoinsRepositoryImpl(api, db)
    }


    @Provides
    @Singleton
    fun provideDatabase(application: Application): CoinDatabase {
        return Room.databaseBuilder(
            application,
            CoinDatabase::class.java,
            CoinDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    fun provideExchangeRateDao(database: CoinDatabase): CoinDao {
        return database.coinDataDao()
    }

}
