package com.example.newsapp

object SampleData {
    val topNews = listOf(
        News(1, "Tecnología: nuevos lanzamientos", "https://images.unsplash.com/photo-1518770660439-4636190af475?w=1200&auto=format"),
        News(2, "Economía: mercados en alza", "https://images.unsplash.com/photo-1454165205744-3b78555e5572?w=1200&auto=format"),
        News(3, "Ciencia: hallazgo reciente", "https://images.unsplash.com/photo-1559757175-08e1456fd69c?w=1200&auto=format")
    )

    val worldNews = listOf(
        News(10, "Europa: cumbre regional", "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=1200&auto=format"),
        News(11, "Asia: innovación y consumo", "https://images.unsplash.com/photo-1549692520-acc6669e2f0c?w=1200&auto=format"),
        News(12, "África: desarrollo urbano", "https://images.unsplash.com/photo-1491553895911-0055eca6402d?w=1200&auto=format"),
        News(13, "América: integración comercial", "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=1200&auto=format"),
        News(14, "Oceanía: turismo en foco", "https://images.unsplash.com/photo-1437622368342-7a3d73a34c8f?w=1200&auto=format"),
        News(15, "Medio Oriente: cooperación", "https://images.unsplash.com/photo-1583259334202-9b3f50a1a2a5?w=1200&auto=format")
    )
}

annotation class News(val i: Int, val string: String, val string2: String)
