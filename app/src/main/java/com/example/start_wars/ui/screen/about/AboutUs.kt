package com.example.start_wars.ui.screen.about

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//Pantalla que usamos para mostrar el about us, esta bastante personalizado, ayudado de la ia por que no sabemos tanta personalizacion de la misma
private val StarWarsYellow = Color(0xFFFFE81F)
private val BackgroundBlack = Color(0xFF121212)
private val CardBackground = Color(0xFF1E1E1E)

@Composable
fun AboutUsScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlack)
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Logo App",
            modifier = Modifier.size(80.dp),
            tint = StarWarsYellow
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "WikiSpecies",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Versión 1.0.0",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        CardInfo(
            title = "Sobre la App",
            content = "Esta aplicación permite la gestión y consulta de especies del universo Star Wars. Desarrollada como práctica para la asignatura de Desarrollo de Interfaces."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nuestro Equipo",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFFFFE81F),
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))


        DeveloperCard(
            name = "Javier García",
            icon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        DeveloperCard(
            name = "Álvaro Llamas",
            icon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        DeveloperCard(
            name = "David Casasola",
            icon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        DeveloperCard(
            name = "Daniel Gonzalez",
            icon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Contacto",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(8.dp))

        SocialRow(
            icon = Icons.Default.Email,
            text = "Enviar sugerencia"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "© 2026 I.E.S Portada Alta",
            color = Color.DarkGray,
            fontSize = 12.sp
        )
    }
}


@Composable
fun DeveloperCard(
    name: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        border = BorderStroke(1.dp, Color(0xFF444444)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
                    .padding(6.dp),
                tint = Color(0xFFFFE81F)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CardInfo(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = StarWarsYellow,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun SocialRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(CardBackground)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = StarWarsYellow)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun AboutUsPreview(){
    AboutUsScreen()
}