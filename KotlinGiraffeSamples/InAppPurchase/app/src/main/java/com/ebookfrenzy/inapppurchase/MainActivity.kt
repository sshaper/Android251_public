package com.ebookfrenzy.inapppurchase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.billingclient.api.*
import com.android.billingclient.api.QueryProductDetailsParams.Product
import com.google.common.collect.ImmutableList
import com.android.billingclient.api.BillingFlowParams.ProductDetailsParams
import android.view.View
import kotlinx.coroutines.*

import com.ebookfrenzy.inapppurchase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private lateinit var binding: ActivityMainBinding
    private lateinit var billingClient: BillingClient
    private lateinit var productDetails: ProductDetails
    private lateinit var purchase: Purchase
    private val demo_product = "one_button_click"

    val TAG = "InAppPurchaseTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        billingSetup()
    }

    fun makePurchase(view: View?) {
        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                ImmutableList.of(
                    ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .build()
                )
            )
            .build()

        billingClient.launchBillingFlow(this, billingFlowParams)
    }

    private fun completePurchase(item: Purchase) {
        purchase = item
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            runOnUiThread {
                binding.consumeButton.isEnabled = true
                binding.statusText.text = "Purchase Complete"
                binding.buyButton.isEnabled = false
            }
        }
    }

    fun consumePurchase(view: View?) {

        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        coroutineScope.launch {
            val result = billingClient.consumePurchase(consumeParams)

            if (result.billingResult.responseCode ==
                BillingClient.BillingResponseCode.OK) {
                runOnUiThread() {
                    binding.consumeButton.isEnabled = false
                    binding.statusText.text = "Purchase consumed"
                    binding.buyButton.isEnabled = true
                }
            }
        }
    }

    private fun reloadPurchase() {

        val queryPurchasesParams = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        billingClient.queryPurchasesAsync(
            queryPurchasesParams,
            purchasesListener
        )
    }

    private val purchasesListener =
        PurchasesResponseListener { billingResult, purchases ->
            if (purchases.isNotEmpty()) {
                purchase = purchases.first()
                binding.consumeButton.isEnabled = true
                binding.buyButton.isEnabled = false
            } else {
                binding.consumeButton.isEnabled = false
            }
        }

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode ==
                BillingClient.BillingResponseCode.OK
                && purchases != null
            ) {
                for (purchase in purchases) {
                    completePurchase(purchase)
                }
            } else if (billingResult.responseCode ==
                BillingClient.BillingResponseCode.USER_CANCELED
            ) {
                Log.i(TAG, "onPurchasesUpdated: Purchase Canceled")
            } else {
                Log.i(TAG, "onPurchasesUpdated: Error")
            }
        }


    private fun billingSetup() {
        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(
                billingResult: BillingResult
            ) {
                if (billingResult.responseCode ==
                    BillingClient.BillingResponseCode.OK
                ) {
                    Log.i(TAG, "OnBillingSetupFinish connected")
                    queryProduct(demo_product)
                    reloadPurchase()
                } else {
                    Log.i(TAG, "OnBillingSetupFinish failed")
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.i(TAG, "OnBillingSetupFinish connection lost")
            }
        })
    }

    private fun queryProduct(productId: String) {
        val queryProductDetailsParams = QueryProductDetailsParams.newBuilder()
            .setProductList(
                ImmutableList.of(
                    Product.newBuilder()
                        .setProductId(productId)
                        .setProductType(
                            BillingClient.ProductType.INAPP
                        )
                        .build()
                )
            )
            .build()

        billingClient.queryProductDetailsAsync(
            queryProductDetailsParams
        ) { billingResult, productDetailsList ->
            if (productDetailsList.isNotEmpty()) {
                productDetails = productDetailsList[0]
                runOnUiThread {
                    binding.statusText.text = productDetails.name
                }
            } else {
                Log.i(TAG, "onProductDetailsResponse: No products")
            }
        }
    }
}