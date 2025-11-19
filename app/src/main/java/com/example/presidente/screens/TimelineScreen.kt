package com.example.presidente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presidente.R
import com.example.presidente.models.President

@Composable
fun TimelineItem(
    president: President,
    isLeftAligned: Boolean,
    onClick: () -> Unit
) {
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
                Box(modifier = Modifier.weight(0.6f)) {
                    Column(
                        modifier = Modifier
                            .clickable { onClick() }
                            .widthIn(max = 300.dp),
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
                }

                Spacer(modifier = Modifier.width(2.dp))

                Box(modifier = Modifier.width(72.dp)) {
                    Image(
                        painter = painterResource(id = president.photoResId),
                        contentDescription = president.name,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                            .clickable { onClick() }
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.height(5.dp).background(Color.White))
            Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color.White))
        }

        if (!isLeftAligned) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = president.photoResId),
                    contentDescription = president.name,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .clickable { onClick() }
                )

                Spacer(modifier = Modifier.width(2.dp))

                Column(
                    modifier = Modifier.clickable { onClick() },
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = president.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = president.term, style = MaterialTheme.typography.bodyMedium)
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
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.escudo_chile),
            contentDescription = "Escudo de Chile",
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Línea de Tiempo \nPresidentes de Chile",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFFFFFFFF)
        )
    }
}