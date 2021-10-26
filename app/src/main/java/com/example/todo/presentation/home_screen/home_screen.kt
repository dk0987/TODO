package com.example.todo.presentation.home_screen

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.presentation.home_screen.component.NoteItem
import com.example.todo.presentation.theme.Background
import com.example.todo.presentation.theme.Blue
import com.example.todo.presentation.theme.LightBackground
import com.example.todo.presentation.util.Screen
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun Home(
    navController: NavController,
    viewmodel : home_view_model = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var search by remember {
        mutableStateOf("")
    }
    val dark = isSystemInDarkTheme()

    Scaffold(scaffoldState =  scaffoldState) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(if (dark) Background else LightBackground)
    ){
//        Image(painter = painterResource(id = R.drawable.ic_home_background),
//            contentDescription = "Home Background",
//            Modifier.fillMaxSize()
//        )
        Column {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            AnimatedVisibility(
                visible = viewmodel.state.value.visibility,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(Blue, 60.sp, fontWeight = FontWeight.Bold)) {
                            append("T")
                        }
                        withStyle(style = SpanStyle(Color.White, 60.sp)) {
                            append("o")
                        }
                        withStyle(style = SpanStyle(Blue, 60.sp, fontWeight = FontWeight.Bold)) {
                            append("D")
                        }
                        withStyle(style = SpanStyle(Color.White, 60.sp)) {
                            append("o")
                        }
                    },
                    textAlign = TextAlign.Start,
                    letterSpacing = 10.sp,
                )
            }

            AnimatedVisibility(
                visible = viewmodel.state.value.searchvisibility,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TextField(
                    value = search,
                    onValueChange = {
                        search = it
                        viewmodel.onQueryTextChange(it)
                    },
                    placeholder = {Text("Search..")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(15.dp),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewmodel.onQueryTextChange("")
                            search = ""
                            viewmodel.toggle()
                        },
                        modifier = Modifier.size(20.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "Close"
                            )
                        }
                    }
                )
            }

           AnimatedVisibility(
               visible = viewmodel.state.value.visibility,
               enter = fadeIn() + slideInVertically(),
               exit = fadeOut() + slideOutVertically()
           ) {
                   IconButton(onClick = {
                       viewmodel.toggle()
                   },
                       modifier = Modifier.padding(top = 18.dp)
                   ) {
                       Image(
                           painter = painterResource(id = R.drawable.ic_magnifying_glass),
                           contentDescription = "Search",
                           modifier = Modifier.size(25.dp),
                           colorFilter = ColorFilter.tint(if (dark) LightBackground else Background)
                       )
                   }
               }
        }
            Box(modifier = Modifier
                .height(600.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center){
                LazyColumn{
                    items(viewmodel.state.value.note){
                        NoteItem(note = it ,
                            navController = navController ,
                            Delete = {
                                viewmodel.deleteNote(it)
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("Item Deleted Successfully")
                                }
                            }
                        )
                       }
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){
            IconButton(onClick = {
                navController.navigate(Screen.Edit.screen) },
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 24.dp, bottom = 24.dp)
                    .clip(
                        RoundedCornerShape(10.dp),
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Add Note",
                )
            }
        }
    }
}
