package com.example.presidente.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presidente.models.President
import androidx.compose.animation.core.*

@Composable
fun PresidentDetailScreen(president: President, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDDDAD0))
                .padding(26.dp),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedBorderPresidentImage(
            photoResId = president.photoResId,
            name = president.name
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
            color = Color(0xFF424242)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = president.term,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF424242)
        )
    }
}

@Composable
fun AnimatedBorderPresidentImage(photoResId: Int, name: String) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Box(
        modifier = Modifier.size(170.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = photoResId),
            contentDescription = name,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Canvas(modifier = Modifier.matchParentSize()) {
            drawArc(
                color = Color(0xFFF8F3CE),
                startAngle = angle,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 6.dp.toPx())
            )
        }
    }
}
