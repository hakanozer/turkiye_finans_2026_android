package com.works.project.domain.factory

import android.util.Log

class ProductA : IProduct {

    override fun addBasket() {
        Log.d("Product", "ProductA ")
    }

}