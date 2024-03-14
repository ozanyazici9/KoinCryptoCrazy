package com.ozanyazici.koincryptocrazy.di

import com.ozanyazici.koincryptocrazy.repository.CryptoDownload
import com.ozanyazici.koincryptocrazy.repository.CryptoDownloadImpl
import com.ozanyazici.koincryptocrazy.service.CryptoAPI
import com.ozanyazici.koincryptocrazy.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // qualifier ile isimlendirme işlemi bütün scopelarda yapılabilir.
    //singleton scope
    single {
        val BASE_URL = "https://raw.githubusercontent.com/"

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }

    // ViewModelda CryptoDownload ı inject etmiştik peki birden fazla sınıf bu arayüzü uygularsa hangi sınıfın inject edileceğini nasıl bileceğiz.
    // işte burda CryptoDownload ın inject edileceği yerlerde CryptoDownloadImpl sınıfını kullan diyoruz.
    single<CryptoDownload> {
        // get() diyerek yukarıdakı api yi yani retrofit i parametre olarak veriyoruz.
        CryptoDownloadImpl(get())
    }

    viewModel {
        // get() diyerek yukarıda oluşturduğumuz repositoryi parametre olarak veriyoruz.
        CryptoViewModel(get())
    }

    // factory -> every time we inject -> new instance -> not olsun diye yazdık.
    factory {

    }
}