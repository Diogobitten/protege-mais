package br.com.fiap.protege.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.protege.R

@Composable
fun PerfilScreen(navController: NavController) {
    Scaffold (
        bottomBar = { BottomNavigationBar(navController) }
    ){
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF181818))
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Foto de perfil
            Box(
                modifier = Modifier
                    .size(200.dp)
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

            Spacer(modifier = Modifier.height(16.dp))

            // Nome do usuário
            Text(
                text = "Cláudio Silva",
                color = Color.White,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descrição
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.perfilTexto),
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para sair
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.perfilB), color = Color.White)
            }
        }
    }
}
