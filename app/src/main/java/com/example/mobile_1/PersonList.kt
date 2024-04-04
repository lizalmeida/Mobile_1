package com.example.mobile_1

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun PersonList() {
    var timeLeft by remember {
        mutableIntStateOf(10)
    }
    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0){
            delay(1000L)
            timeLeft --
        }
    }
    var name by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    var persons by remember {
        mutableStateOf(listOf<Person>())
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Time left: ${timeLeft.toString()}",
                modifier = Modifier
                    .padding(end = 16.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier
                .weight(1f))

            Button(modifier = Modifier
                .padding(end = 16.dp),
                onClick = {
                    timeLeft = 10
                }
            )
            {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = ""
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            TextField(
                value = name,
                placeholder = {Text("Enter name")},
                onValueChange = { text ->
                    name= text
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
                value = age,
                placeholder = {Text("Enter age")},
                onValueChange = { text ->
                    age= text
                }
            )
            Spacer(modifier = Modifier
                .weight(1f))
        }

        Button(modifier = Modifier
            .padding(end = 16.dp),
            onClick = {
                if (name.isNotBlank() && age.isNotBlank()){
                    persons = persons + Person(name = name, age = age.toInt())
                } else {
                    Toast
                        .makeText(
                            context,
                            "Enter a valid name",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                name = ""
                age = ""
            }
        )
        {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Icono de añadir"
            )
        }

        LazyColumn{
            items(persons){currentPerson ->
                Row {
                    Text(
                        text = currentPerson.name,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = currentPerson.age.toString(),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                Divider()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ListPersonPreview() {
    PersonList()
}