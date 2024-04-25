package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.ui.theme.MyCityTheme
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog


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
        NavigationBar(
            currentView = currentView,
            onNavigateBack = { if (currentView > 1) currentView -= 1 },
            onNavigateToFirst = { currentView = 1 },
            onNavigateToSecond = { currentView = 2 },
            onNavigateToThird = { currentView = 3 }
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (currentView) {
            1 -> FirstView(onNavigateToSecond = { currentView = 2 })
            2 -> SecondView(onNavigateToThird = { currentView = 3 }, onNavigateBack = { currentView = 1 })
            3 -> ThirdView(onNavigateBack = { currentView = 2 })
        }
    }
}

@Composable
fun NavigationBar(
    currentView: Int,
    onNavigateBack: () -> Unit,
    onNavigateToFirst: () -> Unit,
    onNavigateToSecond: () -> Unit,
    onNavigateToThird: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentView > 1) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
                }
            } else {
                Spacer(modifier = Modifier.width(72.dp)) // Espacio para mantener la alineación
            }
        }
    }
}

@Composable
fun FirstView(onNavigateToSecond: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting("¡Bienvenidos a la Ciudad de Campeche!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToSecond) {
            Text("Explorar", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondView(onNavigateToThird: () -> Unit, onNavigateBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Conoce los mejores lugares de Campeche")
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(10) { index -> // Cambia el número 10 por la cantidad de cartas que tengas
            Card(
                onClick = onNavigateToThird,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.catedral),
                        contentDescription = "Catedral de Campeche",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(16.dp)
                    )
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Catedral de Campeche",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Breve descripción",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateBack) {
                Text("Back")
            }
        }
    }
}

@Composable
fun ThirdView(onNavigateBack: () -> Unit) {
    // Estado para controlar la visibilidad del diálogo modal
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carta con imagen
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.parque),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Apartado de descripción
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Text(
                    text = "Coloca aquí la descripción de la carta.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Apartado de características
                Text(
                    text = "Características",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Text(
                    text = "Característica 1",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Text(
                    text = "Característica 2",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Text(
                    text = "Característica 3",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Text(
                    text = "Característica 4",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
                Text(
                    text = "Característica 5",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center // Alineación centrada
                )
            }
        }
        // Botón para agendar visita
        Button(onClick = { showDialog = true }) {
            Text("Agendar visita")
        }

        // Diálogo modal para el formulario de agendar visita
        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                content = {
                    Card(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Formulario de agendar visita")
                            // Aquí puedes colocar los campos del formulario
                            // Por ejemplo:
                            TextField(value = "", onValueChange = {}, label = { Text("Nombre") })
                            TextField(value = "", onValueChange = {}, label = { Text("Fecha") })
                            // Otros campos del formulario...
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { showDialog = false }) {
                                Text("Confirmar")
                            }
                            Button(onClick = { showDialog = false }) {
                                Text("Cerrar")
                            }
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
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