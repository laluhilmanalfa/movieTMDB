package id.edupay.customer.di

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import id.edupay.customer.network.Rx3UserRepo
import id.movie.tmdb.screen.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideUserRepoRxJava3(get()) }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://www.mocky.io/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY)).build()
}


fun provideUserRepoRxJava3(retrofit: Retrofit): Rx3UserRepo = retrofit.create(Rx3UserRepo::class.java)

private val viewModelModules = module {
    viewModel<MainViewModel>()
}

val appModules = listOf(networkModule, viewModelModules)
