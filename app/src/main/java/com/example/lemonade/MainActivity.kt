package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {

                    Lemonade(modifier=Modifier.fillMaxSize())

            }
        }
    }
}

@SuppressLint("AutoboxingStateCreation")
@Composable
fun Lemonade(modifier: Modifier=Modifier){
    var squeezeCount by remember { mutableStateOf(0) }
    var currentStep by remember {mutableStateOf(1)}
    var count by remember { mutableStateOf(1) }


    val imageResource = when(currentStep){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val string = when(currentStep){
        1 -> R.string.first_page
        2 ->R.string.second_page
        3 -> R.string.third_page
        4 -> R.string.fourth_page
        else -> R.string.first_page
    }
    val content = when(currentStep){
        1 -> R.string.content_1
        2 ->R.string.content_2
        3-> R.string.content_3
        4 -> R.string.content_4
        else -> R.string.content_1
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier= modifier
        ) {

        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier
                .background(color = Color.Yellow)
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier= modifier
    ) {
        Button(
            onClick = {
                when(count){
                    1 -> {currentStep = 2
                    squeezeCount = (2..4).random()
                    count++}
                    1 + squeezeCount -> {currentStep = 3
                    count++}
                    2 + squeezeCount  -> {currentStep = 4
                    count++}
                    3 + squeezeCount -> {currentStep = 1
                    squeezeCount = 0
                    count =1 }
                    else -> {count++}

                }
            },
            colors = ButtonDefaults.buttonColors(Color.Green),
            shape = RoundedCornerShape(16.dp)
        )
        {
            Image(painter = painterResource(imageResource),
                contentDescription = stringResource(content))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(string),
            fontSize = 18.sp)


    }

}



@Preview(showBackground = true)
@Composable
fun LemonadeApp(){
    LemonadeTheme {
        Lemonade(modifier=Modifier.fillMaxSize())
    }
}