package com.example.sneakershopwsr.core.di

import com.example.sneakershopwsr.auth.data.UserDataValidatorImpl
import com.example.sneakershopwsr.auth.data.UserInfoConverter
import com.example.sneakershopwsr.auth.data.UserInfoSerializable
import com.example.sneakershopwsr.auth.data.repository.AuthInteractorImpl
import com.example.sneakershopwsr.auth.data.repository.AuthSupabaseRepositoryImpl
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.auth.domain.UserDataValidator
import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.core.domain.Converter
import com.example.sneakershopwsr.core.domain.SessionStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.user.UserInfo as SupabaseUserInfo
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideUserDataValidator(): UserDataValidator = UserDataValidatorImpl()

    @ViewModelScoped
    @Provides
    @Named("UserInfoConverter")
    fun provideUserInfoConverter(): Converter<UserInfoSerializable, UserInfo> = UserInfoConverter()

    @ViewModelScoped
    @Provides
    fun provideAuthSupabaseRepository(
        supabaseClient: SupabaseClient,
        @Named("UserInfoConverter") converter: Converter<UserInfoSerializable, UserInfo>,
    ): AuthSupabaseRepository<SupabaseUserInfo> = AuthSupabaseRepositoryImpl(
        supabaseClient = supabaseClient,
        converter = converter,
    )

    @ViewModelScoped
    @Provides
    fun provideAuthInteractor(
        authSupabaseRepository: AuthSupabaseRepository<SupabaseUserInfo>,
        authSessionStorage: SessionStorage<AuthInfo>,
    ): AuthInteractor =
        AuthInteractorImpl(
            authSupabaseRepository = authSupabaseRepository,
            authSessionStorage = authSessionStorage,
        )
}