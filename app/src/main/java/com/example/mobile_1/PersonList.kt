package com.example.mobile_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import java.util.UUID

@Composable
fun PersonList() {
    var person by remember {
        mutableStateOf(Person(UUID.randomUUID(),"", 0))
    }
    var persons by remember {
        mutableStateOf(listOf<Person>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            TextField(
                value = person.name,
                placeholder = {Text("Enter name")},
                onValueChange = { text ->
                    person.name = text
                }
            )
            Spacer(modifier = Modifier
                .weight(1f))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            TextField(
                value = person.age,
                placeholder = {Text("Enter age")},
                onValueChange = { text ->
                    person.age = text
                }
            )
            Spacer(modifier = Modifier
                .weight(1f))
        }

        Button(modifier = Modifier
            .padding(end = 16.dp),
            onClick = {
                persons = persons + person
                person = Person(UUID.randomUUID(), "", 0)
            }
        )
        {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Icono de añadir"
            )
        }
        LazyColumn{
            items(persons){currentPersona ->
                Text(text = "${currentPersona.name}, ${currentPersona.age}",
                modifier = Modifier
                    .padding(16.dp)
                )
                Divider()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PersonListPreview() {
    PersonList()
}