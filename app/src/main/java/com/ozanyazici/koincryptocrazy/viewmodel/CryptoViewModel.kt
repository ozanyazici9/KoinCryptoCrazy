package com.ozanyazici.koincryptocrazy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozanyazici.koincryptocrazy.model.Crypto
import com.ozanyazici.koincryptocrazy.repository.CryptoDownload
import com.ozanyazici.koincryptocrazy.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoViewModel(
    // İnterface i bağımlılık olarak alma sebebimiz; kodunuzu esnek hale getirmek ve bağımlılıkları azaltmaktır.
    // Dependency Injection (Bağımlılık Enjeksiyonu) prensiplerine uygun olarak, arayüzü kullanarak somut bir sınıf yerine genel bir
    // arayüz tipiyle bağlantı kurmak, kodunuzu daha test edilebilir ve değiştirilebilir hale getirir.
    private val cryptoDowloadRepository : CryptoDownload
): ViewModel() {

    val cryptoList = MutableLiveData<Resource<List<Crypto>>>()
    val cryptoError = MutableLiveData<Resource<Boolean>>()
    val cryptoLoading = MutableLiveData<Resource<Boolean>>()

    private var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cryptoError.value = Resource.error(throwable.localizedMessage ?: "error",data = true)
    }

    fun getDataFromAPI() {
        cryptoLoading.value = Resource.loading(data = true)

        // Burada job yerine viewmodelscope kullanılabilir eğer coroutinin işleyişini kontrol etmek istiyorsak
        // onCleared de cancel etmek gibi job kullanabiliriz. buradaki tercihi sebepsiz yaptık viewmodelscope da olurdu yani.
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val resource = cryptoDowloadRepository.downloadCryptos()
            withContext(Dispatchers.Main) {
                resource.data?.let {
                    cryptoList.value = resource
                    cryptoLoading.value = Resource.loading(data = false)
                    cryptoError.value = Resource.error("",false)
                }
            }
        }
    }
}
