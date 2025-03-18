package br.com.fiap.protege.screens

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.protege.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@Composable
fun LocaisDeApoioScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF181818))
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.localApoioTitulo),
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            MapaDelegacias()
        }
    }
}

// 📌 Data class para armazenar informações das delegacias
data class Delegacia(
    val nome: String,
    val location: LatLng,
    val telefone: String,
    val endereco: String
)

// 📌 Lista de delegacias com informações
val delegacias = listOf(
    Delegacia("Delegacia de São Paulo", LatLng(-23.550520, -46.633308), "(11) 99999-0000", "Av. Paulista, 1000"),
    Delegacia("Delegacia do Rio de Janeiro", LatLng(-22.908333, -43.196388), "(21) 88888-1111", "Rua Copacabana, 500"),
    Delegacia("Delegacia de Brasília", LatLng(-15.7801, -47.9292), "(61) 77777-2222", "SQN 309, Bloco A")
)

@SuppressLint("MissingPermission")
@Composable
fun MapaDelegacias() {
    val context = LocalContext.current
    val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var closestDelegacia by remember { mutableStateOf<Delegacia?>(null) }
    var selectedDelegacia by remember { mutableStateOf<Delegacia?>(null) }

    // 📌 Definindo o Brasil como posição inicial do mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-14.2350, -51.9253), 4f) // Inicia no Brasil
    }

    fun getUserLocation() {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val userLatLng = LatLng(it.latitude, it.longitude)
                    userLocation = userLatLng

                    closestDelegacia?.let { closest ->
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(closest.location, 14f)
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("Mapa", "Erro ao obter localização", e)
            }
    }

    LaunchedEffect(Unit) {
        getUserLocation()
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        delegacias.forEach { delegacia ->
            Marker(
                state = MarkerState(position = delegacia.location),
                title = delegacia.nome,
                snippet = "Clique para mais informações",
                onClick = {
                    selectedDelegacia = delegacia
                    true
                }
            )
        }

        closestDelegacia?.let {
            Marker(
                state = MarkerState(position = it.location),
                title = "Delegacia mais próxima",
                snippet = it.nome
            )
        }

        selectedDelegacia?.let { delegacia ->
            AlertDialog(
                onDismissRequest = { selectedDelegacia = null },
                title = { Text(text = delegacia.nome) },
                text = {
                    Column {
                        Text(text = "📍 Endereço: ${delegacia.endereco}")
                        Text(text = "📞 Telefone: ${delegacia.telefone}")
                    }
                },
                confirmButton = {
                    Button(onClick = { selectedDelegacia = null }) {
                        Text("Fechar")
                    }
                }
            )
        }
    }
}
