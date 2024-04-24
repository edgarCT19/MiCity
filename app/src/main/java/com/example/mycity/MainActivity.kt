package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComponent()
                }
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    var currentView by remember { mutableStateOf(1) }

    Column {
        when (currentView) {
            1 -> FirstView(onNavigateToSecond = { currentView = 2 })
            2 -> SecondView(onNavigateToThird = { currentView = 3 }, onNavigateBack = { currentView = 1 })
            3 -> ThirdView(onNavigateBack = { currentView = 2 })
        }
    }
}

@Composable
fun FirstView(onNavigateToSecond: () -> Unit) {
    Column {
        Greeting("Android")
        Button(onClick = onNavigateToSecond) {
            Text("Go to Second View")
        }
    }
}

@Composable
fun SecondView(onNavigateToThird: () -> Unit, onNavigateBack: () -> Unit) {
    Column {
        Text(text = "Second Vista")
        Button(onClick = onNavigateToThird) {
            Text("Go to Third View")
        }
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}

@Composable
fun ThirdView(onNavigateBack: () -> Unit) {
    Column {
        Text(text = "Third View - Description")
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun FirstViewPreview() {
    MyCityTheme {
        FirstView({})
    }
}
