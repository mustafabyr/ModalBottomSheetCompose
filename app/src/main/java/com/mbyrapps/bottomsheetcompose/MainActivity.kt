package com.mbyrapps.bottomsheetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
                    val sheetScaffoldState = rememberBottomSheetScaffoldState(
                        bottomSheetState = SheetState(
                            skipPartiallyExpanded = true,
                            skipHiddenState = false,
                            initialValue = SheetValue.Hidden
                        )
                    )
                    val scope = rememberCoroutineScope()
                    var isSheetFullScreen by remember {
                        mutableStateOf(false)
                    }
                    BottomSheetScaffold(
                        scaffoldState = sheetScaffoldState,
                        sheetContent = {
                            ModalBottomSheet(
                                modifier =
                                if (isSheetFullScreen) {
                                    Modifier
                                        .fillMaxSize()
                                        .padding(12.dp)
                                } else {
                                    Modifier
                                        .padding(12.dp)
                                }

                            ) {
                                isSheetFullScreen = true
                            }
                        },
                        sheetPeekHeight = 0.dp,
                        sheetSwipeEnabled = false,
                        sheetDragHandle = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = {
                                    scope.launch {
                                        sheetScaffoldState.bottomSheetState.hide()
                                        isSheetFullScreen = false
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
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
fun ModalBottomSheet(modifier: Modifier = Modifier, onClickFullScreen: () -> Unit) {
    Column(
        modifier = modifier
    ) {
        for (i in 0..6) {
            Text(text = "Item $i")
        }
        Button(onClick = {
            onClickFullScreen()
        }
        ) {
            Text(text = "Open Sheet")
        }
    }
}