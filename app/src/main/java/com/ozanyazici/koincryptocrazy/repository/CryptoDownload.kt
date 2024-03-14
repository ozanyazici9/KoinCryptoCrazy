package com.ozanyazici.koincryptocrazy.repository

import com.ozanyazici.koincryptocrazy.model.Crypto
import com.ozanyazici.koincryptocrazy.util.Resource

interface CryptoDownload {
    suspend fun downloadCryptos() : Resource<List<Crypto>>
}