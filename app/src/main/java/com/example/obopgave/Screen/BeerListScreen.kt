package com.example.obopgave.Screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.obopgave.NavRouters
import com.example.obopgave.ViewModel.Beer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerListScreen(
    beers: List<Beer>,
    errorMessage: String,
    modifier: Modifier = Modifier,
    onBeerSelected: (Beer) -> Unit = {},
    onBeerDeleted: (Beer) -> Unit = {},
    Beerlist: () -> Unit = {},
    onAdd: () -> Unit = {},
    sortByName: (up: Boolean) -> Unit = {},
    sortByAbv: (up: Boolean) -> Unit = {},
    filterByName: (String) -> Unit = {},
    filterByAbv: (Double) -> Unit = {},
    filterByNameAndAbv: (String, Double) -> Unit = { _, _ -> },
    onRefreshBeerList: () -> Unit = {},
    user: FirebaseUser? = null,
    signOut: () -> Unit = {},
    navigateToAuthentication: () -> Unit = {}
) {
    var isRefreshing by rememberSaveable  { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val pullRefreshState = rememberPullToRefreshState()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF8B8D7B),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Beer") },
                        actions = {
                    IconButton(onClick = { signOut() }) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Log out")
                        if (user == null) {
                            navigateToAuthentication()
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { onAdd() },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            PullToRefreshBox(
                modifier = Modifier.fillMaxWidth(),
                state = pullRefreshState,
                isRefreshing = isRefreshing,
                onRefresh = {
                    isRefreshing = true
                    coroutineScope.launch {
                        onRefreshBeerList()
                        delay(500)
                        isRefreshing = false
                    }
                },
            ){
                BeerListPanel(
                    beers = beers,
                    modifier = Modifier,
                    errorMessage = errorMessage,
                    onBeerSelected = onBeerSelected,
                    Beerlist = Beerlist,
                    onBeerDeleted = onBeerDeleted,
                    sortByName = sortByName,
                    sortByAbv = sortByAbv,
                    filterByName = filterByName,
                    filterByAbv = filterByAbv,
                    filterByNameAndAbv = filterByNameAndAbv,
                    onRefreshBeerList = onRefreshBeerList
                )
            }
        }
    }
}

@Composable
private fun BeerListPanel(
    beers: List<Beer>,
    modifier: Modifier = Modifier,
    errorMessage: String,
    onBeerSelected: (Beer) -> Unit,
    onBeerDeleted: (Beer) -> Unit,
    Beerlist: () -> Unit,
    sortByName: (up: Boolean) -> Unit,
    sortByAbv: (up: Boolean) -> Unit,
    filterByName: (String) -> Unit,
    filterByAbv: (Double) -> Unit ,
    filterByNameAndAbv: (String, Double) -> Unit,
    onRefreshBeerList: () -> Unit = {}


) {
    var isFilterVisible by remember { mutableStateOf(false) }
    var nameFilter by remember { mutableStateOf("") }
    var abvFilter by remember { mutableStateOf("") }
    var sortNameAscending by remember { mutableStateOf(true) }
    var sortAbvAscending by remember { mutableStateOf(true) }

    Column(modifier = modifier) {
        if (errorMessage.isNotEmpty()) {
            Text(text = "Problem: $errorMessage")
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                OutlinedButton(onClick = {
                    sortByName(sortNameAscending)
                    sortNameAscending = !sortNameAscending
                }) {
                    Text(text = if (sortNameAscending) "Sort Name \u2191" else "Sort Name \u2193")
                }

                OutlinedButton(onClick = {
                    sortByAbv(sortAbvAscending)
                    sortAbvAscending = !sortAbvAscending
                }) {
                    Text(text = if (sortAbvAscending) "Sort ABV \u2191" else "Sort ABV \u2193")
                }
            }


            TextButton(onClick = { isFilterVisible = !isFilterVisible }) {
                Text(if (isFilterVisible) "Hide Filters" else "Show Filters")
            }
        }

        // 使用 AnimatedVisibility 显示/隐藏过滤部分
        AnimatedVisibility(visible = isFilterVisible) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // 过滤名字
                OutlinedTextField(
                    value = nameFilter,
                    onValueChange = { nameFilter = it },
                    label = { Text("Filter by Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                // 过滤 ABV
                OutlinedTextField(
                    value = abvFilter,
                    onValueChange = { abvFilter = it },
                    label = { Text("Filter by ABV") },
                    modifier = Modifier.fillMaxWidth()
                )

                // 应用过滤按钮
                Button(onClick = {
                    val abvValue = abvFilter.toDoubleOrNull()
                    when {
                        nameFilter.isNotEmpty() && abvValue != null -> {
                            filterByNameAndAbv(nameFilter, abvValue)
                        }
                        nameFilter.isNotEmpty() -> {
                            filterByName(nameFilter)
                        }
                        abvValue != null -> {
                            filterByAbv(abvValue)
                        }
                        else -> {
                            onRefreshBeerList()
                        }
                    }
                }) {
                    Text("Apply Filter")
                }
            }
        }

        // 表格布局
        val orientation = LocalConfiguration.current.orientation
        val columns = if (orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns) ,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(beers) { beer ->
                BeerItem(
                    beer,
                    onBeerSelected = onBeerSelected,
                    onBeerDeleted = onBeerDeleted,
                    Beerlist = Beerlist
                )
            }
        }
    }
}

    @Composable
    private fun BeerItem(
        beer: Beer,
        modifier: Modifier = Modifier,
        onBeerSelected: (Beer) -> Unit = {},
        Beerlist: () -> Unit,
        onBeerDeleted: (Beer) -> Unit = {}
    ) {
        Card(modifier = modifier
            .padding(4.dp)
            .fillMaxSize(), onClick = { onBeerSelected(beer) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "${beer.name}: ${beer.abv}% ${beer.user} ${beer.brewery} ${beer.style} ${beer.volume}L ${beer.howMany}x",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remove",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onBeerDeleted(beer)
                        }
                )
            }
        }
    }



