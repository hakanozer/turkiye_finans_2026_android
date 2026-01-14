package com.works.project.domain.strategy

class Havale : IPay {

    override fun pay() {
        println("Havale ödeme işlemi yapılıyor...")
    }

}