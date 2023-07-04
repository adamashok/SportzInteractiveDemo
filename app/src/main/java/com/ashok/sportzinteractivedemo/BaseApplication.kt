package com.ashok.sportzinteractivedemo

import android.app.Application
import com.google.android.gms.security.ProviderInstaller
import dagger.hilt.android.HiltAndroidApp
import javax.net.ssl.SSLContext

@HiltAndroidApp
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
            val sslContext : SSLContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (e:Exception) {
            e.printStackTrace();
        }
    }
}