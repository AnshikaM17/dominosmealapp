package com.example.dominossepaisawasool

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

val DominoRed = Color(0xFFE31837) // Domino's Red
val DominoBlue = Color(0xFF0055A5) // Domino's Blue
val DominoWhite = Color(0xFFFFFFFF) // White
val DominoBlack = Color(0xFF000000) // Black
val DominoDarkGray = Color(0xFF333333) // Dark Gray
val LightGrey = Color(0xFFEBEBEB) // Light grey color

// MenuItem Data Class
data class MenuItem(
    val name: String,
    val price: Int,
    val isVegetarian: Boolean // Add a flag to indicate if the item is vegetarian
)

// Sample Menu Data
val dominoMenu = listOf(
    // Pizzas
    MenuItem("Margherita", 200, true),
    MenuItem("Pepperoni", 250, false),
    MenuItem("Veggie Paradise", 220, true),
    MenuItem("Cheese Burst", 300, true),
    MenuItem("Farmhouse", 280, true),
    MenuItem("Chicken Dominator", 350, false),
    MenuItem("Paneer Makhani", 270, true),
    MenuItem("Spicy Chicken", 320, false),
    MenuItem("Tandoori Veg", 240, true),
    MenuItem("Tandoori Chicken", 330, false),
    MenuItem("Double Cheese Margherita", 310, true),
    MenuItem("Mexican Green Wave", 290, true),
    MenuItem("Chicken Golden Delight", 249, false),
    MenuItem("Non-Veg Supreme", 319, false),
    MenuItem("Veg Extravaganza", 260, true),
    MenuItem("Pepper Barbecue Chicken & Onion", 229, false),
    MenuItem("Chicken Sausage", 189, false),
    MenuItem("Chicken Pepperoni", 319, false),
    MenuItem("Chicken Fiesta", 249, false),
    MenuItem("Indi Chicken Tikka", 319, false),
    MenuItem("Keema Do Pyaza", 189, false),

    // Sides
    MenuItem("Garlic Breadsticks", 100, true),
    MenuItem("Stuffed Garlic Bread", 150, true),
    MenuItem("Paneer Zingy Parcel", 120, true),
    MenuItem("Chicken Wings", 180, false),
    MenuItem("Potato Wedges", 90, true),
    MenuItem("Chicken Pepperoni Stuffed Garlic Bread", 200, false),
    MenuItem("Veg Pasta Italiano White", 130, true),
    MenuItem("Non-Veg Pasta Italiano White", 160, false),
    MenuItem("Veg Pasta Italiano Red", 130, true),
    MenuItem("Non-Veg Pasta Italiano Red", 160, false),

    // Desserts
    MenuItem("Choco Lava Cake", 110, true),
    MenuItem("Butterscotch Mousse Cake", 140, true),
    MenuItem("New York Cheesecake", 170, true),
    MenuItem("Dark Fantasy", 120, true),
    MenuItem("Chocolate Brownie", 100, true),
    MenuItem("Vanilla Ice Cream", 80, true),
    MenuItem("Strawberry Ice Cream", 80, true),
    MenuItem("Chocolate Ice Cream", 80, true),

    // Beverages
    MenuItem("Pepsi 500ml", 60, false),
    MenuItem("Mirinda 500ml", 60, false),
    MenuItem("7Up 500ml", 60, false),
    MenuItem("Mountain Dew 500ml", 60, false),
    MenuItem("Water Bottle 1L", 40, true),
    MenuItem("Iced Tea", 70, true),
    MenuItem("Cold Coffee", 90, true),
    MenuItem("Orange Juice", 80, true),
    MenuItem("Mango Juice", 80, true),

    // Combos
    MenuItem("Meal for 2: 2 Medium Pizzas + Garlic Bread + Pepsi", 800, true),
    MenuItem("Meal for 4: 4 Medium Pizzas + Stuffed Garlic Bread + Pepsi", 1500, true),
    MenuItem("Snack Combo: Garlic Bread + Potato Wedges + Pepsi", 300, true),
    MenuItem("Dessert Combo: Choco Lava Cake + Brownie + Ice Cream", 250, true),
    MenuItem("Family Combo: 1 Large Pizza + 1 Medium Pizza + Garlic Bread + Pepsi", 1200, true)
)

@Composable
fun DominoMealApp() {
    var budget by remember { mutableStateOf("") }
    var isVegMode by remember { mutableStateOf(false) }

    // Recalculate combinations based on budget and Veg Mode
    val combinations = remember(budget, isVegMode) {
        calculateCombinations(budget.toIntOrNull() ?: 0, isVegMode)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey) // Apply greyish background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(28.dp))
            // Heading
            Text(
                text = "Domino's Meal Planner",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DominoBlue
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Domino's Logo Below Heading
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground), // Add your logo to res/drawable
                contentDescription = "Domino's Logo",
                modifier = Modifier.size(100.dp), // Adjust size as needed
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Budget Input Field
            TextField(
                value = budget,
                onValueChange = { budget = it },
                label = { Text("Enter your budget", color = DominoDarkGray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DominoWhite,
                    unfocusedContainerColor = DominoWhite,
                    focusedTextColor = DominoBlack,
                    unfocusedTextColor = DominoBlack,
                    focusedIndicatorColor = DominoBlue,
                    unfocusedIndicatorColor = DominoRed
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Veg Mode Toggle Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Veg Mode", color = DominoBlack, fontSize = 16.sp)
                Switch(
                    checked = isVegMode,
                    onCheckedChange = { isVegMode = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display Combinations
            LazyColumn {
                items(combinations) { combination ->
                    CombinationCard(combination)
                }
            }
        }
    }
}

@Composable
fun CombinationCard(combination: List<MenuItem>) {
    val totalPrice = combination.sumOf { it.price }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = DominoWhite
        ),
        border = BorderStroke(1.dp, DominoBlue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                combination.forEach { item ->
                    Text(text = "${item.name} - ₹${item.price}", fontSize = 16.sp, color = DominoDarkGray)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Total: ₹$totalPrice", fontSize = 18.sp, color = DominoBlue)
            }

            // Domino's Logo on the Right Side of the Card
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground), // Add your logo to res/drawable
                contentDescription = "Domino's Logo",
                modifier = Modifier.size(50.dp), // Adjust size as needed
                contentScale = ContentScale.Fit
            )
        }
    }
}

fun calculateCombinations(budget: Int, isVegMode: Boolean): List<List<MenuItem>> {
    val validCombinations = mutableListOf<List<MenuItem>>() // List to store valid combinations

    // Filter menu items based on Veg Mode and calculate combinations
    val filteredMenu = if (isVegMode) {
        dominoMenu.filter { it.isVegetarian }
    } else {
        dominoMenu
    }

    // Loop through all filtered menu items
    for (i in filteredMenu.indices) {
        val combination = mutableListOf<MenuItem>() // Temporary list for a single combination
        var totalPrice = 0

        // Add items one by one to the combination
        for (j in i until filteredMenu.size) {
            val item = filteredMenu[j]
            if (totalPrice + item.price <= budget + 10) {
                combination.add(item) // Add item to the combination
                totalPrice += item.price // Update the total price

                // Check if this combination fits the budget range
                if (totalPrice in (budget - 10)..(budget + 10)) {
                    validCombinations.add(combination.toList()) // Add a copy of the combination
                }
            }
        }
    }

    return validCombinations // Return all valid combinations
}