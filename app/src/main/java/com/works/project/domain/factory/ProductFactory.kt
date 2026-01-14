package com.works.project.domain.factory

class ProductFactory {

    fun createProduct(type: EProduct) : Result<IProduct> {
        return runCatching {
            when (type) {
                EProduct.ProductA -> ProductA()
                EProduct.ProductB -> ProductB()
                else -> throw IllegalArgumentException("Invalid product type: $type")
            }
        }

    }

}