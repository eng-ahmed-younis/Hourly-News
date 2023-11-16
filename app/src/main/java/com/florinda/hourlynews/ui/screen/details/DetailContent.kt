package com.florinda.hourlynews.ui.screen.details


import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.florinda.domain.model.Source
import com.florinda.domain.model.TopNewsArticle
import com.florinda.hourlynews.R
import java.util.Locale

@Composable
fun DetailContent(article: TopNewsArticle) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(article?.urlToImage)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_broken_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoWithIcon(
                modifier = Modifier.weight(1f).padding(end = 4.dp),
                icon = Icons.Default.Edit,
                info = article.author ?: stringResource(id = R.string.not_available),
            )
            InfoWithIcon(
                modifier = Modifier.weight(0.4f),
                icon = Icons.Default.DateRange,
                info = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssxx", Locale.ENGLISH)
                    .parse(article.publishedAt).toString()
            )
        }
        Text(
            text = article.title ?: stringResource(id = R.string.not_available),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = article.description ?: stringResource(id = R.string.not_available),
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    DetailContent(
        article = TopNewsArticle(
            author =  "Maxwell Zeff",
            title = "Crypto Exchanges, Not Just FTX, Are All a Mess Right" ,
            description = "The founders of cryptocurrency exchanges face a mountain of regulatory challenges and billions in personal losses." +
                    " Binance CEO Changpeng Zhao personally lost $12 billion this year as trading volumes on Binance declined, according to a Bloomberg report Friday.…",
            url = "https://gizmodo.com/crypto-exchanges-ftx-binance-genesis-gemini-are-a-mess-1850968083",
            urlToImage = "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/46326bbf3c33c4ddb3f18069c82167d7.jpg",
            publishedAt = "2023-10-27T20:10:00Z",
            content =  "The founders of cryptocurrency exchanges face a mountain of regulatory challenges and billions in personal losses. Binance CEO Changpeng Zhao personally lost $12 billion this year as trading volumes … [+1852 chars]",
            source = Source("1","")
        )
    )
}