package com.example.ideaplatformtesttask.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideaplatformtesttask.R
import com.example.ideaplatformtesttask.data.model.ItemCard
import com.example.ideaplatformtesttask.utilts.TimeParser


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCard(
    itemCard: ItemCard,
    onDelete: () -> Unit,
    onUpdateQuantity: (Int) -> Unit
) {

    var openEditDialog by remember { mutableStateOf(false) }
    var openDeleteDialog by remember { mutableStateOf(false) }


    Card(


        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemCard.name,
                    style = MaterialTheme.typography.titleMedium,

                    )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { openEditDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = ""
                    )
                }

                IconButton(onClick = { openDeleteDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = ""
                    )
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemCard.tags.forEach { tag ->
                    AssistChip(
                        onClick = {  },
                        label = {
                            Text(
                                text = tag,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = colorScheme.primary.copy(alpha = 0.1f)
                        )
                    )
                }
            }



            Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 0.dp)) {
                Column {
                    Text(
                        text = stringResource(R.string.in_stock),
                        style = MaterialTheme.typography.titleMedium,

                        )
                    Text(
                        text = itemCard.amount.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Light

                        )

                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                ) {
                    Text(
                        text = stringResource(R.string.date_added),
                        style = MaterialTheme.typography.titleMedium,
                        )

                    Text(
                        text = TimeParser.formatTimestamp(itemCard.time),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Light
                        )
                }
            }


        }
    }


    if (openEditDialog) {

        var dialogQuantity by remember { mutableIntStateOf(itemCard.amount) }

        AlertDialog(
            onDismissRequest = { openEditDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = ""
                )
            },
            title = { Text(text = stringResource(R.string.product_quantity)) },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = { if (dialogQuantity > 0) dialogQuantity-- }

                        ) {
                            Icon(
                                imageVector = Icons.Default.RemoveCircleOutline,
                                contentDescription = "",
                                modifier = Modifier.size(36.dp)
                            )

                        }
                        Text(
                            text = dialogQuantity.toString(),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = TextStyle(fontSize = 20.sp)
                        )
                        IconButton(
                            onClick = { dialogQuantity++ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircleOutline,
                                contentDescription = "",
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onUpdateQuantity(dialogQuantity)
                        openEditDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.accept))
                }
            },
            dismissButton = {
                Button(
                    onClick = { openEditDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    if (openDeleteDialog) {
        AlertDialog(
            onDismissRequest = { openDeleteDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            title = { Text(text = stringResource(R.string.delete_product)) },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.question_about_removing_product))
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete()
                        openDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDeleteDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.no))
                }
            }
        )
    }
}
