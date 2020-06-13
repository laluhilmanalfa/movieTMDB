package id.movie.tmdb

import android.app.Application
import id.edupay.customer.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TmdbApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@TmdbApp)
            // modules
            modules(appModules)
        }
    }
}