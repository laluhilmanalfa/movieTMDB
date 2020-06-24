package id.movie.tmdb.di

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import id.movie.tmdb.BuildConfig
import id.movie.tmdb.repository.MovieRepository
import id.movie.tmdb.screen.DetailMovieViewModel
import id.movie.tmdb.screen.MainViewModel
import id.movie.tmdb.screen.genrescreen.GenreViewModel
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideUserRepoRxJava3(get()) }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}


fun provideOkHttpClient(): OkHttpClient {

    var okHttpClient = OkHttpClient().newBuilder()
    okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    okHttpClient.addInterceptor(object : Interceptor {


        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "387344049ef5720406cb6aa63f9aa49d")
                .addQueryParameter("language", "en-US")
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            return chain.proceed(request)
        }
    })

    return okHttpClient.build()

}


fun provideUserRepoRxJava3(retrofit: Retrofit): MovieRepository = retrofit.create(MovieRepository::class.java)

private val viewModelModules = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        GenreViewModel(get())
    }

    viewModel {
        DetailMovieViewModel(get())
    }
}

val appModules = listOf(networkModule, viewModelModules)
