package com.example.todo.presentation.home_screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.domain.modal.Note
import com.example.todo.presentation.util.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    note : Note,
    navController: NavController,
    Delete : () -> Unit
) {
    Card(modifier = Modifier
        .padding(10.dp)
        .width(300.dp)
        .clickable {
            navController.navigate(Screen.Edit.screen + "?id=${note.id}")
        },
        elevation = 9.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding( 10.dp)
        ) {
            Text(
                text = note.title,
                textDecoration = null ,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier
                .height(0.5.dp)
            )
            Text(
                text = note.content,
                fontSize = 16.sp,
                maxLines = 13 ,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(
                    onClick = Delete,
                    modifier = Modifier.size(25.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garbage),
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}