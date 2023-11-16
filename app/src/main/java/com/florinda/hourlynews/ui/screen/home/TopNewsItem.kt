package com.florinda.hourlynews.ui.screen.home

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.florinda.domain.model.Source
import com.florinda.domain.model.TopNewsArticle
import com.florinda.hourlynews.R
import java.util.Locale

@Composable
fun TopNewsItem(article: TopNewsArticle?, onItemClick: () -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onItemClick()
            }
            .semantics {
                contentDescription = "NewItem"
            }
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
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 6.dp
                )
            ,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            article?.publishedAt?.let {
                Text(
                    text =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssxx", Locale.ENGLISH).parse(it).toString(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.semantics {
                        contentDescription = "PublishedNew"
                    }
                )
            }
            Text(
                text = article?.title ?: String(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.semantics {
                    contentDescription = "TitleNew"
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true )
@Composable
fun TopNewsItemPreview() {
    TopNewsItem(
        TopNewsArticle(
            author =  "Maxwell Zeff",
            title = "Crypto Exchanges, Not Just FTX, Are All a Mess Right" ,
            description = "The founders of cryptocurrency exchanges face a mountain of regulatory challenges and billions in personal losses." +
                    " Binance CEO Changpeng Zhao personally lost $12 billion this year as trading volumes on Binance declined, according to a Bloomberg report Friday.…",
            url = "https://gizmodo.com/crypto-exchanges-ftx-binance-genesis-gemini-are-a-mess-1850968083",
            urlToImage = "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/46326bbf3c33c4ddb3f18069c82167d7.jpg",
            publishedAt = "2023-10-27T20:10:00Z",
            content =  "The founders of cryptocurrency exchanges face a mountain of regulatory challenges and billions in personal losses. Binance CEO Changpeng Zhao personally lost $12 billion this year as trading volumes … [+1852 chars]",
            source = Source("1","")
        ), onItemClick = {}
    )
}