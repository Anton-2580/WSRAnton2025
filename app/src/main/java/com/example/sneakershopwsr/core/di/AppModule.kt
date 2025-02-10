package com.example.sneakershopwsr.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.sneakershopwsr.BuildConfig
import com.example.sneakershopwsr.auth.data.AuthSessionStorage
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.core.domain.SessionStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSessionStorage(sharedPreferences: SharedPreferences): SessionStorage<AuthInfo> {
        return AuthSessionStorage(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.API_URL,
        supabaseKey = BuildConfig.API_KEY,
        builder = {
        }
    )
}