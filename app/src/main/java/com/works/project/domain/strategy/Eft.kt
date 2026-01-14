package com.works.project.domain.strategy

class Eft : IPay {

    override fun pay() {
        println("EFT ödeme işlemi yapılıyor...")
    }

}