package com.example.places.hexaarch.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.places.hexaarch.viewModel.DashboardViewModel

@Composable
fun CardManagementScreen(viewModel:DashboardViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Card Management", style = MaterialTheme.typography.titleLarge)

//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(viewModel.items) { item ->
//                CardItem(
//                    item = item,
//                    onIncreaseQuantity = { viewModel.increaseQuantity(item) },
//                    onDecreaseQuantity = { viewModel.decreaseQuantity(item) },
//                    totalPrice = viewModel.getTotalPrice(item),
//                    currentQuantity = viewModel.itemQuantities[item] ?: 1
//                )
//            }
//        }
    }
}

//@Composable
//fun CardItem(
//    item: FruitItem,
//    onIncreaseQuantity: () -> Unit,
//    onDecreaseQuantity: () -> Unit,
//    totalPrice: String,
//    currentQuantity: Int
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(6.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            AsyncImage(
//                model = item.imageUrl,
//                contentDescription = item.name,
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(modifier = Modifier.weight(1f)) {
//                Text(item.name, style = MaterialTheme.typography.titleMedium)
//                Text(item.price, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//            }
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                // Quantity Controls
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    IconButton(onClick = onDecreaseQuantity) {
//                        Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease Quantity")
//                    }
//                    Text("$currentQuantity", style = MaterialTheme.typography.bodyLarge)
//                    IconButton(onClick = onIncreaseQuantity) {
//                        Icon(imageVector = Icons.Default.Add, contentDescription = "Increase Quantity")
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Total Price
//                Text(totalPrice, style = MaterialTheme.typography.bodyLarge)
//            }
//        }
//    }
//}
