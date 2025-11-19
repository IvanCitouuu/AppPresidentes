package com.example.presidente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presidente.models.President

@Composable
fun PresidentDetailScreen(president: President, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDDDAD0))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { HeaderSection(president) }
            item { BioSection(president.bio) }
            item { MilestoneSection(president.milestones) }
        }

        BackButton(navController)
    }
}

@Composable
fun HeaderSection(president: President) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = president.photoResId),
            contentDescription = president.name,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, Color.White, CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = president.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF000000)
        )
        Text(
            text = president.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF212121)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = president.term,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF424242)
        )
    }
}