package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArtFrame(@DrawableRes drawableRes: Int,
             @StringRes title: Int,
             @StringRes artist: Int,
             @StringRes year: Int,
             modifier: Modifier = Modifier, onNextClick: () -> Unit, onPreviousClick: () -> Unit) {

    val fontSize = 15.sp

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(drawableRes),
            contentDescription = "Art",
            modifier = modifier
                .padding(10.dp)
                .width(IntrinsicSize.Max)
                .shadow(3.dp)
                .padding(30.dp)
        )
        Spacer(modifier = modifier.height(30.dp))

        Column (
            modifier = modifier
                .background(color = colorResource(R.color.gray))
                .width(320.dp)
                .padding(20.dp), // 순서가 중요
        ) {
            Text(text = stringResource(title), fontSize = 24.sp, textAlign = TextAlign.Start)

            Row {
                Text(
                    text = stringResource(artist),
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = modifier.padding(horizontal = 2.dp))
                Text(text = "(${stringResource(year)})", fontSize = fontSize)
            }
        }

        Spacer(modifier = modifier.height(15.dp))
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
            ) {
            Button(onClick = onPreviousClick) {
                Text("Previous")
            }
            Button(
                onClick = onNextClick
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {

    var drawableRes by remember { mutableStateOf(R.drawable.monet_impression_sunrise) }
    var title by remember { mutableStateOf(R.string.paint_1) }
    var artist by remember { mutableStateOf(R.string.paint_1_artist) }
    var year by remember { mutableStateOf(R.string.paint_1_year) }

    ArtFrame(
        drawableRes = drawableRes,
        title = title,
        artist = artist,
        year = year,
        onNextClick = {
            when (drawableRes) {
                R.drawable.monet_impression_sunrise -> {
                    drawableRes = R.drawable.monet_woman_with_a_parasol
                    title = R.string.paint_2
                    artist = R.string.paint_2_artist
                    year = R.string.paint_2_year
                }
                R.drawable.monet_woman_with_a_parasol -> {
                    drawableRes = R.drawable.vincent_van_gogh_the_harvest
                    title = R.string.paint_3
                    artist = R.string.paint_3_artist
                    year = R.string.paint_3_year
                }
                else -> {
                    drawableRes = R.drawable.monet_impression_sunrise
                    title = R.string.paint_1
                    artist = R.string.paint_1_artist
                    year = R.string.paint_1_year
                }
            }
        },
        onPreviousClick = {
            when (drawableRes) {
                R.drawable.monet_impression_sunrise -> {
                    drawableRes = R.drawable.vincent_van_gogh_the_harvest
                    title = R.string.paint_3
                    artist = R.string.paint_3_artist
                    year = R.string.paint_3_year
                }
                R.drawable.monet_woman_with_a_parasol -> {
                    drawableRes = R.drawable.monet_impression_sunrise
                    title = R.string.paint_1
                    artist = R.string.paint_1_artist
                    year = R.string.paint_1_year
                }
                else -> {
                    drawableRes = R.drawable.monet_woman_with_a_parasol
                    title = R.string.paint_2
                    artist = R.string.paint_2_artist
                    year = R.string.paint_2_year
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}