package com.example.ideaplatformtesttask.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ideaplatformtesttask.R
import com.example.ideaplatformtesttask.viewmodel.RoomViewModel


@Composable
fun ProductListScreen(
    viewModel: RoomViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val products by viewModel.itemCardListResult.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.list_of_products),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchProducts(it)
            },
            label = { Text(stringResource(R.string.product_search)) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",

                    )
            },
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(products) { product ->
                ProductCard(
                    itemCard = product,
                    onDelete = { viewModel.deleteCard(product) },
                    onUpdateQuantity = { newQuantity ->
                        viewModel.updateQuantity(product, newQuantity)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}