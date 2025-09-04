package com.example.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

data class News(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val date: String = ""
)

@Composable
fun NewsScreen(
    topNews: List<News>,
    worldNews: List<News>
) {
    var query by rememberSaveable { mutableStateOf("") }
    val tabs = listOf("Noticias", "Eventos", "Clima")
    val selectedTab = 0

    Scaffold(
        topBar = {
            Surface(tonalElevation = 0.dp) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Buscar") },
                        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Buscar") },
                        singleLine = true,
                        shape = RoundedCornerShape(28.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Tabs
            TabRow(selectedTabIndex = selectedTab, modifier = Modifier.fillMaxWidth()) {
                Tab(selected = true, onClick = {}, enabled = true, text = { Text(tabs[0]) })
                Tab(selected = false, onClick = {}, enabled = false, text = { Text(tabs[1]) })
                Tab(selected = false, onClick = {}, enabled = false, text = { Text(tabs[2]) })
            }

            Spacer(Modifier.height(16.dp))

            // Últimas noticias
            SectionTitle("Últimas noticias")
            Spacer(Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(topNews) { news ->
                    NewsCard(width = 260.dp, height = 160.dp, news = news)
                }
            }

            Spacer(Modifier.height(24.dp))

            // Alrededor del mundo
            SectionTitle("Alrededor del mundo")
            Spacer(Modifier.height(8.dp))

            val cellHeight: Dp = 160.dp
            val rows = (worldNews.size + 1) / 2
            val gridHeight = rows * (cellHeight + 12.dp) + 16.dp

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .height(gridHeight)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false
            ) {
                items(worldNews) { news ->
                    NewsCard(width = Dp.Unspecified, height = cellHeight, news = news)
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(horizontal = 16.dp),
        maxLines = 1
    )
}

@Composable
fun NewsCard(
    width: Dp,
    height: Dp,
    news: News
) {
    Box(
        modifier = Modifier
            .then(if (width != Dp.Unspecified) Modifier.width(width) else Modifier.fillMaxWidth())
            .height(height)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = news.imageRes),
            contentDescription = news.title,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() {
    val topNews = listOf(
        News(1, "Tecnología en 2024", android.R.drawable.ic_menu_camera, "Febrero 08, 2024"),
        News(2, "Economía global", android.R.drawable.ic_menu_gallery, "Febrero 09, 2024"),
        News(3, "Ciencia y futuro", android.R.drawable.ic_menu_report_image, "Febrero 10, 2024")
    )

    val worldNews = listOf(
        News(4, "Europa hoy", android.R.drawable.ic_menu_gallery),
        News(5, "Asia y tecnología", android.R.drawable.ic_menu_camera),
        News(6, "África sostenible", android.R.drawable.ic_menu_gallery),
        News(7, "América y comercio", android.R.drawable.ic_menu_report_image),
        News(8, "Oceanía y turismo", android.R.drawable.ic_menu_camera),
        News(9, "Medio Oriente", android.R.drawable.ic_menu_gallery)
    )

    MaterialTheme {
        NewsScreen(topNews = topNews, worldNews = worldNews)
    }
}
