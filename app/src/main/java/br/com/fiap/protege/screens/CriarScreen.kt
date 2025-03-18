package br.com.fiap.protege.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.protege.R
import java.lang.reflect.Parameter

@Composable
fun CriarScreen(navController: NavController) {

    var cpf by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var comsenha by remember { mutableStateOf("") }
    var tamanhosenha = 6
    var cpferror by remember { mutableStateOf(false) }
    var nomeerror by remember { mutableStateOf(false) }
    var senhaerror by remember { mutableStateOf(false) }
    var comsenhaerror by remember { mutableStateOf(false) }
    var tamanhocpf = 11

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181818)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.protege),
                contentDescription = "logo da Protege+",
                modifier = Modifier
                    .width(300.dp)
                    .height(70.dp)

            )

            Text(
                stringResource(id = R.string.criaçaodeconta),
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )

            Text(
                stringResource(id = R.string.informedados),
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .padding(top = 2.dp)
            )


            OutlinedTextField(
                value = cpf,
                onValueChange = { if (it.length <= tamanhocpf) cpf = it },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(300.dp)
                    .background(Color(0xFF181818)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = stringResource(id = R.string.digitecpf)) },
                shape = RoundedCornerShape(12.dp),
                isError = cpferror,
                label = { Text("CPF") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color(0xFFFFFFFF),
                    focusedPlaceholderColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    errorBorderColor = Color.Red,
                    errorPlaceholderColor = Color.White,
                    errorTextColor = Color.White,


                    ),
                singleLine = true
            )
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                isError = nomeerror,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(300.dp)
                    .background(Color(0xFF181818)),
                placeholder = { Text(text = stringResource(id = R.string.nome)) },
                shape = RoundedCornerShape(12.dp),
                label = { Text(stringResource(id = R.string.digitenome)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color(0xFFFFFFFF),
                    focusedPlaceholderColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    errorBorderColor = Color.Red,
                    errorPlaceholderColor = Color.White,
                    errorTextColor = Color.White,
                ),
                singleLine = true
            )
            OutlinedTextField(
                value = senha,
                onValueChange = { if (it.length <= tamanhosenha) senha = it },
                isError = senhaerror,
                modifier = Modifier
                    .width(300.dp)
                    .background(Color(0xFF181818)),
                placeholder = { Text(text = stringResource(id = R.string.senha)) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                label = { Text(stringResource(id = R.string.criesenha)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color(0xFFFFFFFF),
                    focusedPlaceholderColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    errorBorderColor = Color.Red,
                    errorPlaceholderColor = Color.White,
                    errorTextColor = Color.White,
                ),
                singleLine = true
            )

            Text(
                stringResource(id = R.string.suasenhadeve),
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .padding(top = 2.dp)
                    .width(300.dp)
            )

            OutlinedTextField(
                value = comsenha,
                onValueChange = { if (it.length <= tamanhosenha) comsenha = it },
                isError = comsenhaerror,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(300.dp)
                    .background(Color(0xFF181818)),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(text = stringResource(id = R.string.senha)) },
                shape = RoundedCornerShape(12.dp),
                label = { Text(stringResource(id = R.string.confirmarsenha)) },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color(0xFFFFFFFF),
                    focusedPlaceholderColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                    errorBorderColor = Color.Red,
                    errorPlaceholderColor = Color.White,
                    errorTextColor = Color.White,

                    ),
                singleLine = true
            )
            Button(
                onClick = {
                    cpferror = cpf.isEmpty()
                    senhaerror = senha.isEmpty()
                    nomeerror = nome.isEmpty()
                    comsenhaerror = comsenha.isEmpty()

                    if (!cpferror && !senhaerror && !nomeerror && !comsenhaerror) {
                        navController.navigate("login")
                    }
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(bottom = 5.dp)
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD20000))
            ) {
                Text(
                    text = stringResource(id = R.string.criarcadas),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            TextButton(onClick = {
                navController.navigate("login")
            }) {
                Text(text = stringResource(id = R.string.fazerlog), color = Color.White)
            }


        }

    }

}

@Preview
@Composable
fun CriarScreenPreview() {
    CriarScreen(
        navController = rememberNavController()
    )
}