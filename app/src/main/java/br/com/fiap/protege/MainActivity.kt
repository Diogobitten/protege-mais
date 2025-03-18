package br.com.fiap.protege

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.protege.screens.CriarScreen
import br.com.fiap.protege.screens.DashboardScreen
import br.com.fiap.protege.screens.LocaisDeApoioScreen
import br.com.fiap.protege.screens.PerfilScreen
import br.com.fiap.protege.ui.theme.ProtegeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProtegeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable(route = "login") {
                            Login(navController)
                        }
                        composable(route = "criar") {
                            CriarScreen(navController)
                        }
                        composable(route = "denuncia") {
                            DenunciaScreen(navController)
                        }
                        composable(route = "dashboard") {
                            DashboardScreen(navController)
                        }
                        composable(route = "locais") {
                            LocaisDeApoioScreen(navController)
                        }
                        composable(route = "perfil") {
                            PerfilScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Login(navController: NavController) {

    var cpf by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181818)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.protege),
                contentDescription = "logo da Protege+",
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
            )


            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(300.dp)
                    .background(Color(0xFF181818)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = stringResource(id = R.string.digitecpf), color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                label = { Text("CPF", color = Color.White) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,
                    errorBorderColor = Color.Red,
                    errorPlaceholderColor = Color.White,
                    errorTextColor = Color.White,
                ),
                singleLine = true
            )


            OutlinedTextField(
                value = senha, onValueChange = { senha = it },
                modifier = Modifier
                    .background(Color(0xFF181818))
                    .width(300.dp),
                placeholder = { Text(text = stringResource(id = R.string.digitedenha), color = Color.Gray) },
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.senha), color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedPlaceholderColor = Color.White,
                )
            )

            Button(
                onClick = { navController.navigate("dashboard") },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(bottom = 30.dp)
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD20000))
            ) {
                Text(
                    text = stringResource(id = R.string.entrar),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            TextButton(onClick = {
                navController.navigate("criar")
            }) {
                Text(text = stringResource(id = R.string.criar), color = Color.White)
            }


            Text(
                text = stringResource(id = R.string.ou),
                fontSize = 15.sp,
                color = Color(0xFFFAFAFA),
            )

            Button(
                onClick = { navController.navigate("denuncia") },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 30.dp)
                    .width(220.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF181818)
                ),
                border = BorderStroke(1.dp, color = Color.Red)

            ) {
                Text(
                    text = stringResource(id = R.string.anonimo),
                    color = Color.White,
                    fontSize = 15.sp
                )
            }

        }
    }
}







