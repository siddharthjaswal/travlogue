package com.aurora.genesis.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aurora.data.data.entity.trip.TripEntity
import java.io.File

private const val BANNER_MAX_HEIGHT = 150
private const val CORNER_RADIUS = 12

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GetTimelineLayout(
    trip: TripEntity
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Banner Image Section
        trip.bannerImagePath?.let { imagePath ->
            if (imagePath.isNotBlank()) {
                BannerImage(
                    imagePath = imagePath,
                    tripName = trip.name,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Trip Duration Text
        Text(
            text = "${trip.days} Days",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun BannerImage(
    imagePath: String,
    tripName: String,
    modifier: Modifier = Modifier
) {
    val imageFile = File(imagePath)

    if (imageFile.exists()) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageFile)
                    .crossfade(true)
                    .build()
            ),
            contentDescription = "Trip banner for $tripName",
            modifier = modifier
                .height(BANNER_MAX_HEIGHT.dp)
                .clip(RoundedCornerShape(CORNER_RADIUS.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

