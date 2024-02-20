package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {

    var state by remember { mutableIntStateOf(0) }
    var squeeze by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) 
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            //LemonStep()
            when(state) {
                0 -> LemonImageButton(
                    painter = painterResource(R.drawable.lemon_tree),
                    example = stringResource(R.string.lemon_tree),
                    contentDescription = "lemon tree",
                    onClick = {
                        ++state
                        squeeze = (2..4).random()
                    })

                1 -> LemonImageButton(
                    painter = painterResource(R.drawable.lemon_squeeze),
                    example = stringResource(R.string.lemon),
                    contentDescription = "squeeze lemon",
                    onClick = {
                        if (squeeze > 0) {
                            --squeeze
                        } else {
                            ++state
                        }
                    })

                2 -> LemonImageButton(
                    painter = painterResource(R.drawable.lemon_drink),
                    example = stringResource(R.string.glass_of_lemonade),
                    contentDescription = "glass of lemonade",
                    onClick = { ++state })

                else -> LemonImageButton(
                    painter = painterResource(R.drawable.lemon_restart),
                    example = stringResource(R.string.empty_glass),
                    contentDescription = "empty glass",
                    onClick = { state = 0 })
            }
        }
    }
}

@Composable
fun LemonImageButton(
    painter: Painter,
    example: String,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.mint))
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
            )
        }
        Text(
            example,
            modifier = modifier.padding(top = 16.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
fun LemonStep(modifier: Modifier = Modifier) {

    var state by remember { mutableIntStateOf(1) }
    val image = when(state) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val description = when(state) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    val contentDescription = when(state) {
        1 -> "Lemon tree"
        2 -> "Lemon"
        3 -> "glass of lemonade"
        else -> "empty glass"
    }
    val squeeze = (2..4).random()
    var cnt by remember { mutableIntStateOf(1) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (state != 2 || squeeze <= cnt) {
                    state = (state + 1) % 4
                } else {
                    ++cnt
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.mint))
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = contentDescription,
            )
        }
        Text(
            stringResource(description),
            modifier = modifier.padding(top = 16.dp),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}