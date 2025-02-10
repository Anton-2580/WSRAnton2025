package com.example.sneakershopwsr.core.di

import com.example.sneakershopwsr.shop.data.repository.SupabaseRepositoryImpl
import com.example.sneakershopwsr.shop.domain.SupabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.jan.supabase.SupabaseClient


@Module
@InstallIn(ViewModelComponent::class)
object ShopModule {

    @Provides
    @ViewModelScoped
    fun provideSupabaseRepository(supabaseClient: SupabaseClient): SupabaseRepository =
        SupabaseRepositoryImpl(supabaseClient)

}