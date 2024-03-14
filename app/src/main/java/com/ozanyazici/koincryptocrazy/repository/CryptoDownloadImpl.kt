package com.ozanyazici.koincryptocrazy.repository

import com.ozanyazici.koincryptocrazy.model.Crypto
import com.ozanyazici.koincryptocrazy.service.CryptoAPI
import com.ozanyazici.koincryptocrazy.util.Resource

class CryptoDownloadImpl(private val api: CryptoAPI) : CryptoDownload {

    // LiveDataları burada işleyemeceğim için Resource dosyasını kullanıyorum
    // böylelikle viewmodel da liveDataların durumunu bu cevaba göre ayarlıyorum.
    // LiveDsataları burada olşturmuyorum çünkü viewmodel ın görevi gözlemlenecek livedataları içermek.
    override suspend fun downloadCryptos(): Resource<List<Crypto>> {
        return try {
            val response = api.getCryptoList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e : Exception) {
            Resource.error("No data", null)
        }
    }
}