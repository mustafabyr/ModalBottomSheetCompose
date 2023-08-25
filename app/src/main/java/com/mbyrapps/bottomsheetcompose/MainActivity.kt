package com.mbyrapps.bottomsheetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbyrapps.bottomsheetcompose.ui.theme.BottomSheetComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //implementation(platform("androidx.compose:compose-bom:2023.08.00"))
                    val sheetScaffoldState = rememberBottomSheetScaffoldState()
                    val scope = rememberCoroutineScope()
                    BottomSheetScaffold(

                        scaffoldState = sheetScaffoldState,
                        sheetContent = {
                            ModalBottomSheet(
                                modifier = Modifier.padding(12.dp)
                            )
                        },
                        sheetPeekHeight = 0.dp
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                scope.launch {
                                    sheetScaffoldState.bottomSheetState.expand()
                                }
                            }
                            ) {
                                Text(text = "Open Sheet")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ModalBottomSheet(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        for (i in 0..6) {
            Text(text = "Item $i")
        }
    }
}