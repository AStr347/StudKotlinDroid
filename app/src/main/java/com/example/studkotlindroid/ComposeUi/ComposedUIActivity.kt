package com.example.studkotlindroid.ComposeUi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.studkotlindroid.ComposeUi.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ComposedUIActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModel = ComposedUIViewModel()
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.padding(all = 20.dp),

                        ) {
                        Greeting("Anton")
                        Spacer(modifier = Modifier.height(40.dp))
                        CounterForm(viewModel)
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }

    @Composable
    fun CounterForm(viewModel : ComposedUIViewModel){
        Row(modifier = Modifier.padding(all = 20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Text(text = "${viewModel.count.collectAsState().value}")
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { viewModel.onClick() })
            {
                Text(text = "inc")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        var viewModel = ComposedUIViewModel()
        MyApplicationTheme {
            Column(
                modifier = Modifier.padding(all = 20.dp),

            ) {
                Greeting("Android")
                Spacer(modifier = Modifier.height(40.dp))
                CounterForm(viewModel)
            }
        }
    }
}


class ComposedUIViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun onClick(){
        _count.value++
        Log.i("",_count.value.toString())
    }
}

