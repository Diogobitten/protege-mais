package br.com.fiap.protege

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.protege.database.repository.DenunciaRepository
import br.com.fiap.protege.model.Denuncia
import br.com.fiap.protege.screens.BottomNavigationBar
import br.com.fiap.protege.screens.CriarScreen

@Composable
fun DenunciaScreen(navController: NavController) {
    val context = LocalContext.current
    val denunciaRepository = DenunciaRepository(context)
    var descricao by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var localizacao by remember { mutableStateOf("") }
    var listaDenunciasState by remember { mutableStateOf(denunciaRepository.listar()) }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color(0xFF181818))
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            DenunciaForm(
                descricao = descricao,
                categoria = categoria,
                localizacao = localizacao,
                onDescricaoChange = { descricao = it },
                onCategoriaChange = { categoria = it },
                onLocalizacaoChange = { localizacao = it },
                onSalvar = {
                    val novaDenuncia = Denuncia(
                        descricao = descricao,
                        categoria = categoria,
                        localizacao = localizacao
                    )
                    denunciaRepository.salvar(novaDenuncia)
                    listaDenunciasState = denunciaRepository.listar()
                }
            )

            DenunciaList(listaDenunciasState) {
                listaDenunciasState = denunciaRepository.listar()
            }
        }
    }
}

@Composable
fun DenunciaForm(
    descricao: String,
    categoria: String,
    localizacao: String,
    onDescricaoChange: (String) -> Unit,
    onCategoriaChange: (String) -> Unit,
    onLocalizacaoChange: (String) -> Unit,
    onSalvar: () -> Unit
) {
    Spacer(modifier = Modifier.height(50.dp))
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            stringResource(id = R.string.faca),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { onDescricaoChange(it) },
            modifier = Modifier
                .background(Color(0xFF181818))
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp),
            label = { Text(text = stringResource(id = R.string.descricao)) },
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

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { onCategoriaChange(it) },
            modifier = Modifier
                .background(Color(0xFF181818))
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp),
            label = { Text(text = stringResource(id = R.string.categoria))},
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

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = localizacao,
            onValueChange = { onLocalizacaoChange(it) },
            modifier = Modifier
                .background(Color(0xFF181818))
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp),
            label = { Text(text = stringResource(id = R.string.localizacao))},
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSalvar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.registrar))
        }
    }
}

@Composable
fun DenunciaList(listaDenuncias: List<Denuncia>, onSalvar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (denuncia in listaDenuncias) {
            DenunciaCard(denuncia = denuncia, onSalvar)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun DenunciaCard(denuncia: Denuncia, onSalvar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        val context = LocalContext.current
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            ) {
                Text(text = denuncia.categoria, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = denuncia.descricao, fontSize = 16.sp)
                Text(text = denuncia.localizacao, fontSize = 14.sp, fontWeight = FontWeight.Light)
            }
            IconButton(onClick = {
                val denunciaRepository = DenunciaRepository(context = context)
                denunciaRepository.excluir(denuncia = denuncia)
                onSalvar()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}
