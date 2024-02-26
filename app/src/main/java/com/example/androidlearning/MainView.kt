package com.example.androidlearning


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidlearning.ui.theme.AndroidLearningTheme

@Composable
fun Clicker() {
    val coinsAmount = remember { mutableIntStateOf(0) }
    val upgradeValue = remember { mutableIntStateOf(100) }
    val clickBonus = remember { mutableIntStateOf(1) }

    Image(
        painter = painterResource(id = R.drawable.cookie_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = 0.85F
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = coinsAmount.value.toString(),
            fontFamily = FontFamily(
                Font(R.font.cookie_regular)
            ),
            fontSize = 92.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 48.dp),
            textAlign = TextAlign.Center
        )

        ImageButton(
            painter = painterResource(id = R.drawable.cookie),
            modifier = Modifier
                .fillMaxWidth(1f)
                .bounceClick(),
            onClick = {
                coinsAmount.value += clickBonus.value
            }
        )

        val context = LocalContext.current
        ImageButton(
            painter = painterResource(id = R.drawable.cookie_milk),
            modifier = Modifier
                .size(186.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .bounceClick(),
            onClick = {
                if (coinsAmount.value >= upgradeValue.value) {
                    coinsAmount.value -= upgradeValue.value
                    upgradeValue.value *= 2
                    clickBonus.value *= 2
                } else {
                    Toast.makeText(context, "Not enough coins!", Toast.LENGTH_LONG).show()
                }
            }
        )

        Text(
            text = "Price: ${upgradeValue.value}",
            fontFamily = FontFamily(
                Font(R.font.cookie_regular)
            ),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 64.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ImageButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        modifier = modifier,
        onClick = onClick
    ) {
        Image(
            painter = painter,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AndroidLearningTheme {
        Clicker()
    }
}
