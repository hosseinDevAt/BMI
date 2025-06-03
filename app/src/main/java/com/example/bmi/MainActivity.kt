package com.example.bmi

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi.ui.theme.BMITheme

class MainActivity : ComponentActivity() {
    var stateHeight = mutableStateOf("")
    var stateWeight = mutableStateOf("")
    var res = mutableStateOf("please fill that fields")
    var resColor = mutableStateOf(Color.Transparent)
    var classIf = mutableStateOf("")
    var src = mutableStateOf<String?>(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BmiApp(innerPadding)
                }
            }
        }
    }


    @Composable
    fun BmiApp(innerPadding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            Spacer(Modifier.height(25.dp))

            Text(
                text = res.value,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(45.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = classIf.value,
                    fontSize = 38.sp,
                    textAlign = TextAlign.Center,
                    color = resColor.value
                )

                Spacer(Modifier.width(10.dp))

                val currentIcon = src.value
                if (!currentIcon.isNullOrBlank()) {
                    Image(
                        painter = painterResource(id = currentIcon.toInt()),
                        contentDescription = "",
                        modifier = Modifier.size(45.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }


            Card()


        }
    }


    @Composable
    private fun Card() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Box(
                modifier = Modifier
                    .height(360.dp)
                    .fillMaxWidth()

                    .background(
                        Color(0XFFe03d50),
                        RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                    ),

                ) {

                Form()

            }

        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    private fun Form() {

        Column(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                value = stateHeight.value,
                onValueChange = { stateHeight.value = it },
                label = {
                    Text(
                        "Enter Your Height",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            textDirection = TextDirection.Ltr
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDirection = TextDirection.Ltr
                ),
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )

            Spacer(Modifier.height(25.dp))

            TextField(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                value = stateWeight.value,
                onValueChange = { stateWeight.value = it },
                label = {
                    Text(
                        "Enter Your Weight",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            textDirection = TextDirection.Ltr
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDirection = TextDirection.Ltr
                ),
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )

            Spacer(Modifier.height(25.dp))

            Button(
                onClick = {

                    val heightStr = stateHeight.value
                    val weightStr = stateWeight.value

                    val heightCM = heightStr.toDoubleOrNull()
                    val weightKg = weightStr.toDoubleOrNull()
                    if (heightCM != null && weightKg != null && heightCM > 0 && weightKg > 0){
                        val heightM = heightCM / 100.0
                        val bmiResult = weightKg / ((heightM * heightM))
                        val bmi2Decimal = String.format("%.2f", bmiResult)
                        res.value = " your bmi is : $bmi2Decimal "
                        val newColor : Color
                        val text : String
                        val icon : Int

                            when{
                            bmiResult < 18.5 -> {
                                text = " Slimming "
                                newColor = Color(0XFF11c8d9)
                                icon = R.drawable.slimming
                            }
                            bmiResult < 24.9 -> {
                                text = " Healthy "
                                newColor = Color(0XFF04b825)
                                icon = R.drawable.healthy
                            }
                            bmiResult < 29.9 -> {
                                text = " Overweight "
                                newColor = Color(0XFFb38300)
                                icon = R.drawable.overweight
                            }
                            else -> {
                                text = " Obesity "
                                newColor = Color(0XFFa10e03)
                                icon = R.drawable.obesity
                            }
                        }

                        classIf.value = text
                        resColor.value = newColor
                        src.value = icon.toString()

                    }else{
                        res.value = "wrong values for this fields"
                    }

                },
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFF068c23)
                ),
                shape = RoundedCornerShape(10.dp),

            ) {
                Text(
                    "Click to Calculate"
                )
            }

        }
    }
}

