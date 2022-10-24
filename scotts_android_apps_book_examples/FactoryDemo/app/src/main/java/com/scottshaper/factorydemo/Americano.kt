package com.scottshaper.factorydemo

class Americano : Coffee {
    override fun recipe(): String = "Expresso, Hot water"
    override fun name(): String = "Caffè Americano"
}