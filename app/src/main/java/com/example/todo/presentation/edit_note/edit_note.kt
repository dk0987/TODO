package com.example.todo.presentation.edit_note

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.domain.modal.Note
import com.example.todo.presentation.theme.Background
import com.example.todo.presentation.theme.LightBackground
import com.example.todo.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun Edit(
    navController: NavController,
    viewmodel : edit_note_viewmodel = hiltViewModel(),
) {

    val dark = isSystemInDarkTheme()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (dark) Background else LightBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.screen)
                        navController.popBackStack()
                    },
                    Modifier
                        .size(32.dp)
                        .padding(bottom = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_left_arrow),
                        contentDescription = "Back",
                        colorFilter = ColorFilter.tint(Color.Black),
                    )
                }
                TextField(
                    value = viewmodel.state.value.title,
                    onValueChange = { viewmodel.ontitlevaluechange(it) },
                    placeholder = { Text(text = "Enter Title..." , color = if (dark) LightBackground else Background) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().background(if (dark) Background else LightBackground),
                    shape = RoundedCornerShape(7.dp),
                    textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold , color = if (dark) LightBackground else Background) ,
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = viewmodel.state.value.content,
                    onValueChange = { viewmodel.oncontentvaluechange(it)},
                    placeholder = { Text(text = "Description..." , color = if (dark) LightBackground else Background) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(if (dark) Background else LightBackground),
                    shape = RoundedCornerShape(7.dp),
                    textStyle = TextStyle(fontSize = 16.sp , color = if (dark) LightBackground else Background),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {
                        if (viewmodel.state.value.title.isEmpty()) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Title is empty")
                            }
                        }
                        else if (viewmodel.state.value.content.isEmpty()) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Content is empty")
                            }
                        }
                        else {
                            viewmodel.insert(note = Note(viewmodel.state.value.title, viewmodel.state.value.content, viewmodel.state.value.id))
                            navController.popBackStack()
                            navController.navigate(Screen.Home.screen)
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Note Inserted Successfully")
                            }
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 24.dp, bottom = 24.dp)
                        .clip(
                            RoundedCornerShape(10.dp),
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_floppy_disk),
                        contentDescription = "Save",
                    )
                }
            }
        }
    }
}