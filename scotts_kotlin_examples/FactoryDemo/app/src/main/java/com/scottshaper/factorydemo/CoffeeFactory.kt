package com.scottshaper.factorydemo

object CoffeeFactory {

    enum class Type{
        LATTE, AMERICANO
    }

    fun getCoffee(type: Type): Coffee{
        if (type == CoffeeFactory.Type.LATTE){
            return CaffeLatte()
        }else if(type == CoffeeFactory.Type.AMERICANO){
            return Americano()
        }
        throw IllegalArgumentException("Can't handle your command ${type.name}")
    }
}