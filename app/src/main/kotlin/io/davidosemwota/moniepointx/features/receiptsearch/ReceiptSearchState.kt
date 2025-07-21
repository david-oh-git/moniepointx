package io.davidosemwota.moniepointx.features.receiptsearch

import androidx.annotation.Keep

@Keep
data class ReceiptSearchState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val receipts: List<Receipt> = listOf(
        Receipt(
            id = 1,
            itemName = "Macbook pro M2",
            receiptNumber = "NE43857340857904",
            route = "Paris -> Morocco",
        ),
        Receipt(
            id = 2,
            itemName = "Summer linen jacket",
            receiptNumber = "NEJ20089934122231",
            route = "Barcelona -> Paris",
        ),
        Receipt(
            id = 3,
            itemName = "Tapered-fit jeans AW",
            receiptNumber = "NEJ35870264978658",
            route = "Colombia -> Paris",
        ),
        Receipt(
            id = 4,
            itemName = "Slim fit jeans AW",
            receiptNumber = "NEJ35870264978659",
            route = "Bogota -> Dhaka",
        ),
        Receipt(
            id = 5,
            itemName = "Office setup desk",
            receiptNumber = "NEJ23481570754963",
            route = "France -> Germany",
        ),
    ),
) {
    val filteredReceipts: List<Receipt>
        get() = if (searchQuery.isEmpty()) {
            receipts
        } else {
            receipts.filter {
                it.receiptNumber.contains(searchQuery, ignoreCase = true) ||
                        it.itemName.contains(searchQuery, ignoreCase = true)
            }
        }
}

data class Receipt(
    val id: Int,
    val receiptNumber: String,
    val itemName: String,
    val route: String,
)