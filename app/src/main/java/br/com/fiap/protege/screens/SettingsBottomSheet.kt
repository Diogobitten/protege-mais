package br.com.fiap.protege.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet(
    navController: NavController,
    onDismiss: () -> Unit // Callback para fechar o BottomSheet
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color(0xFF181818) // Cor de fundo do modal
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Configurações",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de Perfil
            TextButton(
                onClick = {
                    navController.navigate("perfil")
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Perfil", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Perfil", color = Color.White)
            }

            // Botão de Logout
            TextButton(
                onClick = {
                    navController.navigate("login") // Redireciona para a tela de login
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.Red)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Logout", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
