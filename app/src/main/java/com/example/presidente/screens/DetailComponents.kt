package com.example.presidente.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.animation.*
import androidx.compose.animation.core.tween

@Composable
fun BioSection(bio: String) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Biografía",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (expanded) bio else bio.take(200) + "...",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF3C2F23),
            modifier = Modifier.animateContentSize()
        )

        Spacer(modifier = Modifier.height(4.dp))

        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = if (expanded) "Leer menos" else "Leer más",
                color = Color(0xFF23569E),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun MilestoneSection(milestones: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Hitos importantes",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.height(8.dp))

        milestones.forEachIndexed { index, milestone ->
            val delayMillis = index * 100
            val visible by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(delayMillis)) + slideInVertically(initialOffsetY = { it / 2 }),
                exit = fadeOut() + shrinkVertically()
            ) {
                MilestoneCard(text = milestone)
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun MilestoneCard(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5ECD9))
    ) {
        Text(
            text = "• $text",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            color = Color(0xFF3C2F23)
        )
    }
}

@Composable
fun BackButton(navController: NavHostController) {
    var visible by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth()) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInHorizontally(initialOffsetX = { -100 }),
            exit = fadeOut() + slideOutHorizontally(targetOffsetX = { -100 })
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF000000)
                )
            }
        }
    }
}