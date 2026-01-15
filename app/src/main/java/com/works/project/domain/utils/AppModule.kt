package com.works.project.domain.utils

import com.works.project.data.remote.TodoApi
import com.works.project.data.remote.UserApi
import com.works.project.domain.factory.ActionImpl
import com.works.project.domain.factory.IAction
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun apiRetrofit() : Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonbulut.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit
    }



    @Provides
    @Singleton
    fun userApi( retrofit: Retrofit ) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun todoApi( retrofit: Retrofit ) : TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @Provides
    @IntoSet
    fun provideAction(): IAction {
        return ActionImpl()
    }

    @Provides
    @IntoSet
    fun provideAction1(): IAction {
        return ActionImpl1()
    }



}