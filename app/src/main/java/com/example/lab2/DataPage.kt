package com.example.lab2

import DataApi
import DataResponse
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

object Data {
    var apiData: DataResponse? = null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPage(navController: NavController, dataApi: DataApi) {
    Data.apiData = dataApi.getData().execute().body()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Lab2")
                },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (Data.apiData != null) {
                val list = Data.apiData as DataResponse
                list.data.forEach {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(size = 15.dp))
                                .background(color = MaterialTheme.colorScheme.primary)
                                .fillMaxWidth(0.8f)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(70.dp),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                            Box(modifier = Modifier.size(20.dp))
                            Column(
                                modifier = Modifier.height(70.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "${it.first_name} ${it.last_name}",
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(it.email, color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }

                }
            }
        }
    }
}