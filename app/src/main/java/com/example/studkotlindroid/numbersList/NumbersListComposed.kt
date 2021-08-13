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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun MainBody(vm: NumbersListViewModel = NumbersListViewModel()) {
    TitlesWithList(vm)
    /* todo: must be slide out screen or always some ander list */
    Button(onClick = { /* todo: dialog for add new PhoneNumber */ },
        shape = CircleShape,
        modifier= Modifier
            .size(50.dp)
            .offset(x = 330.dp, y = 770.dp)
    ) {
        Text(text = "+", fontSize = 20.sp)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TitlesWithList(vm: NumbersListViewModel){
    val titleWithNumbers = vm.list.collectAsState().value
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(10.dp)
    ) {
        for ((t, numbers) in titleWithNumbers) {
            stickyHeader {
                Header(t.toString())
            }
            items(numbers) { item ->
                NumberRow(item)
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
    Text(text = text,
        style = headerStyle,
        modifier = Modifier
            .fillMaxWidth()
            .background(bgcolor)
    )
}

@Composable
fun NumberRow(pn : PhoneNumber){
    /* todo: pressing for edit number object */
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.pointerInput(pn){
            detectTapGestures(
                onPress = { /* todo: dialog for edit PhoneNumber */ },
            )
        }
    ) {
        Image(painter = painterResource(id = pn.imgId),
            contentDescription = null,
            modifier = Modifier
                .size(width = 40.dp, height = 40.dp)
        )
        Text(text = pn.Name,
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(text = pn.Number,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainBody()
    }
}

