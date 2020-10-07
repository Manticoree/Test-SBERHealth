package com.app.test_sberhealth.di.modules

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.app.test_sberhealth.retrofit.NetworkInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class RetrofitModule(val context: Context, private val url: String) {

    lateinit var retrofit: Retrofit

    @Provides
    @Singleton
    fun getUnsafeOkHttpClient(context: Context): OkHttpClient {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PROXY SETTINGS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //java.net.Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.25.36", 10001));
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PROXY SETTINGS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            var okHttpClient = OkHttpClient()
            okHttpClient = okHttpClient.newBuilder().sslSocketFactory(sslSocketFactory)
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .addInterceptor(NetworkInterceptor(context)).connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS).readTimeout(25, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()
                    var pInfo: PackageInfo? = null
                    val builder = original.newBuilder()
                        .header("currentOS", String.format("Android %s", Build.VERSION.RELEASE))
                    try {
                        pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                        val version = pInfo.versionName
                        builder.header("version", version)
                    } catch (e: PackageManager.NameNotFoundException) {
                        e.printStackTrace()
                    }
                    builder.method(original.method(), original.body())
                    chain.proceed(builder.build())
                }.build()
            //.proxy(proxy)
            okHttpClient
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Provides
    @Singleton
    fun getClient(context: Context, url: String): Retrofit? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        retrofit = Retrofit.Builder().baseUrl(url)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(getUnsafeOkHttpClient(context))
            .build()
        return retrofit
    }

}