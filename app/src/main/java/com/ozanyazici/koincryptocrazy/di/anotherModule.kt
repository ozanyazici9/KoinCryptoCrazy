package com.ozanyazici.koincryptocrazy.di

import com.ozanyazici.koincryptocrazy.view.ListFragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val anotherModule = module {

    // injection un hangi scope da olacağını belirtiyoruz o scope a inject yapacak ve böyle string inject edebiliyoruz.
    // Normalde birçok yerde kullanacağımız stringleri böyle yapmayız da utilde tanımlarız örneğin Constants dosyası gibi
    // String burada örnek vermek için bunu diyelim retrofit oluşturmak için kullanabiliriz.
    // ListFragmentta diyelimki farklı konfigürasyolara sahip iki farklı retrofit örneği inject etmek istiyorum
    // böyle yapabilirim isimlendirerekte birbirinden ayırt edeblirim.
    scope<ListFragment> {
        scoped(qualifier = named("hello")) {
            "Hello Kotlin"
        }

        scoped(qualifier = named("hi")) {
            "hi kotlin"
        }
    }




}