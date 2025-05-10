package com.example.places.hexaarch.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.places.hexaarch.screen.FruitItem
import kotlin.collections.listOf

class DashboardViewModel : ViewModel() {

    private val _items = mutableStateListOf<FruitItem>()
    val items: List<FruitItem> get() = _items

    private val _cartItems = mutableStateListOf<FruitItem>()
    val cartItems: List<FruitItem> get() = _cartItems

    private val _quantities = mutableStateMapOf<FruitItem, Int>()
    val quantities: Map<FruitItem, Int> get() = _quantities

    init {
        loadFruits()
    }

    private fun loadFruits() {
        _items.addAll(
                listOf(
                    FruitItem("Apple", "₹100 /1 kg", "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg",100.00),
                    FruitItem("Orange", "₹80 /1 kg", "https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg",300.00),
                    FruitItem("Banana", "₹50 /1 dozen", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg",300.00),
                    FruitItem("Mango", "₹120 /1 kg", "https://upload.wikimedia.org/wikipedia/commons/9/90/Hapus_Mango.jpg",400.00),
                    FruitItem("Grapes", "₹90 /1 kg", "https://upload.wikimedia.org/wikipedia/commons/2/29/Grapes_Bunch.jpg",500.00),
                    FruitItem("Pineapple", "₹70 /1 pc", "https://upload.wikimedia.org/wikipedia/commons/c/cb/Pineapple_and_cross_section.jpg",50.00),
                    FruitItem("Strawberry", "₹150 /1 box", "https://upload.wikimedia.org/wikipedia/commons/2/29/PerfectStrawberry.jpg",1200.00),
                    FruitItem("Watermelon", "₹60 /1 pc", "https://upload.wikimedia.org/wikipedia/commons/e/e4/Watermelon_cross_BNC.jpg",600.00),
                    FruitItem("Papaya", "₹40 /1 kg", "https://upload.wikimedia.org/wikipedia/commons/6/6f/Papaya_cross_section_BNC.jpg",900.00),
                    FruitItem("Kiwi", "₹110 /1 pc", "https://upload.wikimedia.org/wikipedia/commons/d/d3/Kiwi_aka.jpg",700.00)
                )
        )
    }

    fun addToCart(item: FruitItem) {
        _cartItems.add(item)
    }
    fun isInCart(item: FruitItem): Boolean {
        return _cartItems.contains(item)
    }

    fun removeFromCart(item: FruitItem) {
        _cartItems.remove(item)
    }

    fun updateQuantity(item: FruitItem, qty: Int) {
        if (_cartItems.contains(item) && qty > 0) {
            _quantities[item] = qty
        }
    }

    fun getTotalAmount(): Int {
        return cartItems.sumOf { item ->
            val qty = quantities[item] ?: 1
            val price = extractPrice(item.price)
            price * qty
        }
    }

    // Helper to extract integer price from string like "₹120 /kg"
    private fun extractPrice(priceString: String): Int {
        return Regex("""\d+""").find(priceString)?.value?.toIntOrNull() ?: 0
    }
}
