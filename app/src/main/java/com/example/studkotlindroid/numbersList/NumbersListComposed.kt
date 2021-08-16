package com.example.studkotlindroid.numbersList

import studkotlindroid.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studkotlindroid.numbersList.ui.theme.MyApplicationTheme
import androidx.compose.runtime.collectAsState as collectAsState

class NumbersListComposed : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainBody()
                }
            }
        }
    }
}

@Composable
fun MainBody(vm: NumbersListViewModel = NumbersListViewModel.instant) {
    TitlesWithList(vm)
    /* todo: must be slide out screen or always some ander list */
    Button(
        onClick = { vm.onOpenAddDialog() },
        shape = CircleShape,
        modifier= Modifier
            .size(50.dp)
            .offset(x = 330.dp, y = 730.dp)
    ) {
        Text(
            text = "+",
            fontSize = 20.sp
        )
    }
    /* that func draw dialog */
    if(vm.openAddDialog.collectAsState().value){
        AddNumberDialog(vm)
    } else if (vm.openEditDialog.collectAsState().value){
        EditNumberDialog(vm)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TitlesWithList(vm: NumbersListViewModel){
    val titleWithNumbers = vm.list.collectAsState().value
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(10.dp)
    ) {
        for ((t, numbers) in titleWithNumbers) {
            stickyHeader {
                Header(t.toString())
            }
            items(numbers) { item ->
                NumberRow(vm, item)
            }
        }
    }
}

@Composable
fun Header(text:String){
    val bgcolor = Color(R.color.lightBlueBG)
    val headerStyle = androidx.compose.ui.text.TextStyle(
        color = Color(R.color.lightBlueText),
        fontSize = 20.sp
    )
    Text(
        text = text,
        style = headerStyle,
        modifier = Modifier
            .fillMaxWidth()
            .background(bgcolor)
    )
}

@Composable
fun NumberRow(vm: NumbersListViewModel, pn : PhoneNumber){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.pointerInput(pn){
            detectTapGestures(
                onLongPress = { vm.onOpenEditDialog(pn); },
            )
        }
    ) {
        Image(
            painter = painterResource(id = pn.imgId),
            contentDescription = null,
            modifier = Modifier.size(width = 40.dp, height = 40.dp)
        )
        Text(
            text = pn.Name,
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = pn.Number,
            modifier = Modifier.fillMaxWidth()
        )
    }
}



@Composable
fun AddNumberDialog(vm: NumbersListViewModel){
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { vm.onOpenAddDialog() },
        title = { Text(text = "Add Number")},
        text = {
            Column() {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {Text(text = "Name")}
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = number,
                    onValueChange = { number = it },
                    label = {Text(text = "Number")}
                )
            }
        },
        confirmButton = {
            Button(onClick = { vm.onAdd(PhoneNumber(name, number)) }) {
                Text(text = "Add")
            }
        },
        dismissButton = {},
    )
}


@Composable
fun EditNumberDialog(vm: NumbersListViewModel){
    val titleWithNumbers = vm.list.collectAsState().value.flatMap { it.value }
    val pn = titleWithNumbers[vm.editIndex]
    var name by remember { mutableStateOf(pn.Name) }
    var number by remember { mutableStateOf(pn.Number) }
    AlertDialog(
        onDismissRequest = { vm.onOpenEditDialog() },
        title = { Text(text = "Add Number")},
        text = {
            Column() {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {Text(text = "Name")}
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = number,
                    onValueChange = { number = it },
                    label = {Text(text = "Number")}
                )
            }
        },
        confirmButton = {
            Row() {
                Button(onClick = { vm.onEdit(PhoneNumber(name, number)) }) {
                    Text(text = "Edit")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { vm.onDel(pn) }) {
                    Text(text = "Del")
                }
            }
        },
        dismissButton = {},
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainBody()
    }
}

