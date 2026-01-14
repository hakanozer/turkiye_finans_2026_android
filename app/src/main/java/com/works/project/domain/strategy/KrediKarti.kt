package com.works.project.domain.strategy

class KrediKarti : IPay {

    override fun pay() {
        println("Kredi kartı ödeme işlemi yapılıyor...")
    }

}
