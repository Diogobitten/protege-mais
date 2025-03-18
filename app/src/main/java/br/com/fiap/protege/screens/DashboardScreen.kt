package br.com.fiap.protege.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.protege.R
import br.com.fiap.protege.screens.BottomNavigationBar



@Composable
fun ButtonCard(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .height(80.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF181818))
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                //Usuário
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = CircleShape)
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Foto do usuário",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(id = R.string.perfil),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                //Denúncias recebidas
                Text(
                    text = stringResource(id = R.string.dashTitulo),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "2300",
                    color = Color.White,
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Bold
                )

                // gráfico
                DenunciaChart()

                Spacer(modifier = Modifier.height(60.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ButtonCard(text = stringResource(id = R.string.dashBDenuncia), Modifier.weight(1f)) {
                        navController.navigate("denuncia")
                    }

                    ButtonCard(text = stringResource(id = R.string.dashBApoio), Modifier.weight(1f)) {
                        navController.navigate("locais")
                    }
                }

                Spacer(modifier = Modifier.height(90.dp))


                Text(
                    text = stringResource(id = R.string.dashTexto),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// 📌 Função do gráfico de denúncias
@Composable
fun DenunciaChart() {
    val denuncias = listOf(1800, 2000, 1900, 2200, 2100, 2300)

    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp)
    ) {
        val maxValue = denuncias.maxOrNull() ?: 1
        val minValue = denuncias.minOrNull() ?: 0
        val range = maxValue - minValue
        val stepX = size.width / (denuncias.size - 1).toFloat()

        val points = denuncias.mapIndexed { index, value ->
            val x = index * stepX
            val y = size.height - ((value - minValue) / range.toFloat() * size.height)
            Offset(x, y)
        }

        for (i in 0 until points.size - 1) {
            drawLine(
                color = Color.Red,
                start = points[i],
                end = points[i + 1],
                strokeWidth = 4f,
                pathEffect = PathEffect.cornerPathEffect(10f) // Suaviza as linhas
            )
        }
    }
}


// Função para desenhar o gráfico
@Composable
fun DenunciaChart(data: List<Int>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        val maxValue = data.maxOrNull() ?: 1
        val minValue = data.minOrNull() ?: 0
        val range = maxValue - minValue
        val stepX = size.width / (data.size - 1).toFloat()

        val points = data.mapIndexed { index, value ->
            val x = index * stepX
            val y = size.height - ((value - minValue) / range.toFloat() * size.height)
            Offset(x, y)
        }

        for (i in 0 until points.size - 1) {
            drawLine(
                color = Color.Red,
                start = points[i],
                end = points[i + 1],
                strokeWidth = 4f,
                pathEffect = PathEffect.cornerPathEffect(10f) // Suaviza as linhas
            )
        }
    }
}



