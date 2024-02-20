package com.example.quarter_view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quarter_view.ui.theme.Quarter_viewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Quarter_viewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
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

@Composable
fun ComposableCardView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier.weight(1F)
        ) {
            ComposableCard(
                title = stringResource(R.string.text_title),
                body = stringResource(R.string.text_body),
                color = colorResource(R.color.purple_5),
                modifier = Modifier.weight(1F)
            )
            ComposableCard(
                title = stringResource(R.string.image_title),
                body = stringResource(R.string.image_body),
                color = colorResource(R.color.purple_100),
                modifier = Modifier.weight(1F)
            )
        }
        Row(
            modifier = Modifier.weight(1F) // 상대적 비율을 나타내는 값
        ) {
            ComposableCard(
                title = stringResource(R.string.row_title),
                body = stringResource(R.string.row_body),
                color = colorResource(R.color.purple_150),
                modifier = Modifier.weight(1F)
            )
            ComposableCard(
                title = stringResource(R.string.column_title),
                body = stringResource(R.string.column_body),
                color = colorResource(R.color.purple_5),
                modifier = Modifier.weight(1F)
            )
        }
    }
}

@Composable
fun ComposableCard(title: String, body: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = color)
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = body,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Quarter_viewTheme {
        ComposableCardView()
    }
}