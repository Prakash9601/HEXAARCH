package com.example.places.hexaarch.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.places.hexaarch.navigation.AllDestinations.navController
import com.example.places.hexaarch.navigation.AllDestinations.viewModel
import com.example.places.hexaarch.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen() {
    val cartItems = viewModel.cartItems
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Cart") }
            )

            TopAppBar(
                title = { Text("Your Cart".uppercase()) },
                actions = {
                    BadgedBox(
                        badge = {
                            if (cartItems.isNotEmpty()) {
                                Badge { Text("${cartItems.size}") }
                            }
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Route.PaymentScreen.route)
                                }
                                .padding(8.dp)
                        ) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Pay Now")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(cartItems.size) { index ->
                val item = cartItems[index]
                CartItemCard(
                    item = item,
                    quantity = 1,
                    onQuantityChange = { newQty -> viewModel.updateQuantity(item, newQty) },
                    onRemoveFromCart = { viewModel.removeFromCart(item) }
                )
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: FruitItem,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onRemoveFromCart: () -> Unit
) {
    var qty by remember { mutableStateOf(quantity) }
    val unitPrice = item.price.filter { it.isDigit() }.toIntOrNull() ?: 0
    val totalPrice = qty * unitPrice

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = item.imageRes,
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(item.name, style = MaterialTheme.typography.titleMedium)
                    Text(item.price, style = MaterialTheme.typography.bodySmall, color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (qty > 1) {
                                qty--
                                onQuantityChange(qty)
                            }
                        }) {
                            Icon(Icons.Default.Clear, contentDescription = "Decrease")
                        }

                        Text(text = qty.toString(), modifier = Modifier.width(24.dp), textAlign = TextAlign.Center)

                        IconButton(onClick = {
                            qty++
                            onQuantityChange(qty)
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Increase")
                        }
                    }
                }

                IconButton(onClick = onRemoveFromCart) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Remove from Cart")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* handle checkout or calculation */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Total: ₹$totalPrice")
            }
        }
    }
}

