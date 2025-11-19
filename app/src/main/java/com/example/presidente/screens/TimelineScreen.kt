package com.example.presidente.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.presidente.R
import com.example.presidente.models.President

@Composable
fun TimelineItem(
    president: President,
    isLeftAligned: Boolean,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLeftAligned) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onClick() }
                        .padding(end = 6.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = president.name,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.End,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = president.term,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }

                Box(
                    modifier = Modifier.size(70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = president.photoResId),
                        contentDescription = president.name,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .clickable { onClick() }
                    )
                    Canvas(modifier = Modifier.matchParentSize()) {
                        drawArc(
                            color = Color(0xFFF8F3CE),
                            startAngle = angle,
                            sweepAngle = 350f,
                            useCenter = false,
                            style = Stroke(width = 4.dp.toPx())
                        )
                    }
                }
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.height(5.dp).background(Color(0xFFF8F3CE)))
            Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFF8F3CE)))
        }

        if (!isLeftAligned) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = president.photoResId),
                        contentDescription = president.name,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .clickable { onClick() }
                    )
                    Canvas(modifier = Modifier.matchParentSize()) {
                        drawArc(
                            color = Color(0xFFF8F3CE),
                            startAngle = angle,
                            sweepAngle = 350f,
                            useCenter = false,
                            style = Stroke(width = 4.dp.toPx())
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onClick() }
                        .padding(start = 6.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = president.name,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = president.term,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun TimelineScreen(presidents: List<President>, navController: NavHostController) {
    val antiqueBackground = Color(0xFFDDDAD0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(antiqueBackground)
    ) {
        TimelineHeader()
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(Color.Black)
                    .align(Alignment.Center)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 2.dp)
            ) {
                itemsIndexed(presidents) { index, president ->
                    val isLeftAligned = index % 2 == 0
                    TimelineItem(
                        president = president,
                        isLeftAligned = isLeftAligned,
                        onClick = {
                            try {
                                navController.navigate("detail/$index")
                            } catch (_: Exception) {
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TimelineHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF7A7A73))
            .padding(vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedEscudoChile()
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "Línea de Tiempo \nPresidentes de Chile",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFFF8F3CE)
        )
    }
}


@Composable
fun AnimatedEscudoChile() {
    val transition = rememberInfiniteTransition()

    val offsetX by transition.animateFloat(
        initialValue = -90f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val offsetY by transition.animateFloat(
        initialValue = 10f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(id = R.drawable.escudo_chile),
        contentDescription = "Escudo animado",
        modifier = Modifier
            .offset(x = offsetX.dp, y = offsetY.dp)
            .size(90.dp)
            .padding(top = 24.dp)
    )
}
