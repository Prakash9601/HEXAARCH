package com.example.places.hexaarch.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.places.hexaarch.navigation.AllDestinations.navController
import com.example.places.hexaarch.navigation.AllDestinations.viewModel
import com.example.places.hexaarch.navigation.Route
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val items = viewModel.items
    val cartItems = viewModel.cartItems

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fruit Shop".uppercase()) },
                actions = {
                    BadgedBox(
                        badge = {
                            if (cartItems.isNotEmpty()) {
                                Badge { Text("${cartItems.size}") }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            modifier = Modifier.padding(8.dp).clickable {
                                // Navigate to the CartScreen
                                navController.navigate(Route.CartScreen.route)

                            }
                        )
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
            items(items.size) { item ->
                DashboardCard(
                    item = items.get(item),
                    isInCart = viewModel.isInCart(items.get(item)),
                    onAddToCart = { viewModel.addToCart(it) },
                    onRemoveFromCart = { viewModel.removeFromCart(it) }
                )
            }
        }
    }
}



data class FruitItem(
    val name: String,
    val price: String,
    val imageRes: String,
    val pricePerUnit: Double // Price per unit (for calculation)
)


@Composable
fun DashboardCard(
    item: FruitItem,
    isInCart: Boolean,
    onAddToCart: (FruitItem) -> Unit,
    onRemoveFromCart: (FruitItem) -> Unit
) {
    var isAdded by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isAdded) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "scale"
    )

    LaunchedEffect(isAdded) {
        if (isAdded) {
            delay(300)
            isAdded = false
        }
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.imageRes,
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                Text(item.price, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            if (isInCart) {
                IconButton(onClick = { onRemoveFromCart(item) }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Remove from Cart",
                        tint = Color.Red
                    )
                }
            } else {
                IconButton(onClick = {
                    if (!isInCart) {
                        isAdded = true
                        onAddToCart(item)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Add to Cart",
                        tint = if (isInCart || isAdded) Color(0xFF4CAF50) else Color.Gray
                    )
                }
            }
        }
    }
}



