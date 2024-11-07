package com.example.starter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TodoList(
    todoList: List<String>, modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        when (todoList.isEmpty()) {
            true -> Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
            ) {
                Text(
                    "No TODO available",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                )
            }

            false -> LazyColumn {
                itemsIndexed(todoList) { _, item ->
                    ListItem(headlineContent = { Text(item) })
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen() {
    var textFieldValue by remember { mutableStateOf("") }
    val todoList = remember { mutableListOf<String>() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        topBar = {
            TopAppBar(title = { Text("Home") })
        },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TodoList(
                todoList = todoList, modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                TextField(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                    value = textFieldValue,
                    shape = RoundedCornerShape(30.dp),
                    placeholder = { Text("Enter your DATA") },
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (textFieldValue.isNotEmpty()) {
                                todoList.add(textFieldValue)
                                textFieldValue = ""
                            }
                            focusManager.clearFocus(true)
                        }
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    onValueChange = {
                        textFieldValue = it
                    })
                Box(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        if (textFieldValue.isNotEmpty()) {
                            todoList.add(textFieldValue)
                            textFieldValue = ""
                        }

                        focusManager.clearFocus(true)
                    }, shape = CircleShape, modifier = Modifier.clip(CircleShape)
                ) {
                    Icon(
                        contentDescription = null,
                        imageVector = Icons.Default.Add,
                    )
                }
            }
        }


    }

}