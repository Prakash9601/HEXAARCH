package com.example.places.hexaarch.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.places.hexaarch.navigation.AllDestinations.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    totalAmount: Int,
    onPaymentConfirmed: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf("Cash") }
    var paymentSuccess by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PaymentScreen") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Payment", style = MaterialTheme.typography.headlineMedium)

            Text("Total Amount: ₹$totalAmount", style = MaterialTheme.typography.titleLarge)

            // Payment method selection
            Text("Select Payment Method:")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("Cash", "UPI", "Card").forEach { method ->
                    FilterChip(
                        selected = selectedMethod == method,
                        onClick = { selectedMethod = method },
                        label = { Text(method) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    paymentSuccess = true
                    onPaymentConfirmed()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm Payment")
            }

            if (paymentSuccess) {
                AlertDialog(
                    onDismissRequest = { paymentSuccess = false },
                    title = { Text("Payment Successful") },
                    text = { Text("Your payment via $selectedMethod was successful!") },
                    confirmButton = {
                        TextButton(onClick = {
                            paymentSuccess = false
                            onPaymentConfirmed() // Navigate or perform post-payment action
                        }) {
                            Text("OK")
                            viewModel.cartItems.forEach {
                                viewModel.removeFromCart(it)
                            }
                        }
                    }
                )
            }
        }
    }
}
