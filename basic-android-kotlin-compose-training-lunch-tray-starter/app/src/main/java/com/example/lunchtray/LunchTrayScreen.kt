/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen
import com.example.lunchtray.ui.theme.LunchTrayTheme

// Screen enum
enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(R.string.app_name),
    MainMenu(R.string.choose_entree),
    SideMenu(R.string.choose_side_dish),
    SnackMenu(R.string.choose_accompaniment),
    Order(R.string.order_checkout)
}

private val Tag = "MainActivity"

// TODO: AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    modifier: Modifier = Modifier,
    backStackEntry: Boolean = false,
    currentScreen: LunchTrayScreen,
    onBackClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (backStackEntry) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    navController: NavHostController = rememberNavController()
) {
    // Create Controller and initialization
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                backStackEntry = currentScreen != LunchTrayScreen.Start,
                currentScreen = currentScreen,
                onBackClicked = {
                    Log.d(Tag, "back button clicked")
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = { navController.navigate(LunchTrayScreen.MainMenu.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = LunchTrayScreen.MainMenu.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { cancelButtonClicked(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.SideMenu.name) },
                    onSelectionChanged = { viewModel.updateEntree(it) }
                )
            }
            composable(route = LunchTrayScreen.SideMenu.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { cancelButtonClicked(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.SnackMenu.name) },
                    onSelectionChanged = { viewModel.updateSideDish(it) }
                )
            }
            composable(route = LunchTrayScreen.SnackMenu.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { cancelButtonClicked(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Order.name) },
                    onSelectionChanged = { viewModel.updateAccompaniment(it) }
                )
            }
            composable(route = LunchTrayScreen.Order.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    onCancelButtonClicked = { cancelButtonClicked(viewModel, navController) })
            }
        }
    }
}

private fun cancelButtonClicked(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
}

@Preview
@Composable
fun LunchTrayAppPreview() {
    LunchTrayTheme {
        LunchTrayApp()
    }
}
