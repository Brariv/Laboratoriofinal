package com.uvg23088.laboratoriofinal.Presentation.CurrencyList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.ejercicioslabs.ejercicios.ktor.presentation.common.ErrorLayout
import com.uvg.ejercicioslabs.ejercicios.ktor.presentation.common.LoadingLayout
import com.uvg23088.laboratoriofinal.Presentation.ViewModel.CurrencyEvent
import com.uvg23088.laboratoriofinal.Presentation.ViewModel.CurrencyState
import com.uvg23088.laboratoriofinal.Presentation.ViewModel.CurrencyViewModel
import com.uvg23088.laboratoriofinal.R
import com.uvg23088.laboratoriofinal.domain.model.Currency


@Composable
fun CurrencyListRoute(
    onCurrencyClick: (String) -> Unit,
    viewModel: CurrencyViewModel = viewModel(factory = CurrencyViewModel.Factory)

){
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onEvent(CurrencyEvent.onCurrencyListClick)

    }
    CurrencyListScreen(
        state = state,
        onOfflineMode = { viewModel.onEvent(CurrencyEvent.onOfflineClick) },
        onRetryClick = { viewModel.onEvent(CurrencyEvent.onCurrencyListClick) }
    )

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrencyListScreen(
    state: CurrencyState,
    onOfflineMode: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = modifier.fillMaxSize()){
            when {
                state.isLoading -> {
                    LoadingLayout(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.isError -> {
                    ErrorLayout(
                        onRetryClick = onRetryClick,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else ->{
                    TopAppBar(title = {
                        Row(modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon( painterResource(id = R.drawable.offline_ic) , contentDescription = null,
                                modifier = Modifier.clickable { onOfflineMode() })
                            Spacer(modifier = Modifier.padding(8.dp, 0.dp))
                            Text("Currency List")
                        }

                    }, colors = TopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        scrolledContainerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp, 16.dp, 16.dp, 0.dp),
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(40.dp)
                    ) {

                    }


                }
            }
        }

    }

}

@Composable
private fun CurrencyItem(
    currency: Currency,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Box (
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ){
            Text(currency.symbol)
        }
        Column {
            Text(currency.name)
            Row {
                Text(currency.priceUsd)
                Text(currency.changePercent24Hr)
            }
        }


    }
}