package com.example.sneakershopwsr.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.sneakershopwsr.BuildConfig
import com.example.sneakershopwsr.auth.data.AuthInfoConverter
import com.example.sneakershopwsr.auth.data.AuthInfoSerializable
import com.example.sneakershopwsr.auth.data.AuthSessionStorage
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.core.data.local.ShopDatabase
import com.example.sneakershopwsr.core.data.repository.DatabaseRepositoryImpl
import com.example.sneakershopwsr.core.data.repository.FullStackDataInteractorImpl
import com.example.sneakershopwsr.core.data.repository.SupabaseRepositoryImpl
import com.example.sneakershopwsr.core.domain.Converter
import com.example.sneakershopwsr.core.domain.SessionStorage
import com.example.sneakershopwsr.core.domain.repository.DatabaseRepository
import com.example.sneakershopwsr.core.domain.repository.FullStackDataInteractor
import com.example.sneakershopwsr.core.domain.repository.SupabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    @Named("AuthInfoConverter")
    fun provideConverter(): Converter<AuthInfoSerializable, AuthInfo> = AuthInfoConverter()

    @Provides
    @Singleton
    fun provideSessionStorageAuth(
        sharedPreferences: SharedPreferences,
        @Named("AuthInfoConverter") authConverter: Converter<AuthInfoSerializable, AuthInfo>,
    ): SessionStorage<AuthInfo> = AuthSessionStorage(
        sharedPreferences = sharedPreferences,
        converter = authConverter,
    )

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.API_URL,
        supabaseKey = BuildConfig.API_KEY,
        builder = {
            install(Postgrest)
            install(Storage)
            install(Auth)
        }
    )

    @Provides
    @Singleton
    fun provideShopDatabase(app: Application): ShopDatabase = Room
        .databaseBuilder(
            context = app,
            klass = ShopDatabase::class.java,
            name = "Shop"
        ).build()

    @Provides
    @Singleton
    fun provideSupabaseRepository(supabaseClient: SupabaseClient): SupabaseRepository =
        SupabaseRepositoryImpl(supabaseClient)

    @Provides
    @Singleton
    fun provideDatabaseRepository(db: ShopDatabase): DatabaseRepository =
        DatabaseRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideFullStackInteractor(
        supabase: SupabaseRepository,
        database: DatabaseRepository,
    ): FullStackDataInteractor =
        FullStackDataInteractorImpl(
            supabaseRepository = supabase,
            databaseRepository = database,
        )
}
