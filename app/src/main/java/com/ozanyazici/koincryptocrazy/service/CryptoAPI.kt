package com.ozanyazici.koincryptocrazy.service

import com.ozanyazici.koincryptocrazy.model.Crypto
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    //https://raw.githubusercontent.com/https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getCryptoList(): Response<List<Crypto>>
}