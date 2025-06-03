package com.example.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi.ui.theme.BMITheme
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {
    var stateHeight = mutableStateOf("")
    var stateWeight = mutableStateOf("")
    var res = mutableStateOf("مقادیر را وارد نمایید")
    var classif = mutableStateOf("")
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
                fontSize = 46.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(45.dp))

            Text(
                text = classif.value,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )


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
                        res.value = " $bmi2Decimal "

                        val classification = when{
                            bmiResult < 18.5 -> " لاغری "
                            bmiResult < 24.9 -> " سالم "
                            bmiResult < 29.9 -> " اضافه وزن "
                            else -> " چاقی "
                        }

                        classif.value += classification

                    }else{
                        res.value = "مقادیر ورودی نا معتبر است"
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

