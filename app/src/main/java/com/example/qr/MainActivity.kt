package com.example.qr

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qr.ui.theme.QrTheme

class MainActivity : ComponentActivity(), ButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            QrTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Simple(this@MainActivity)
                  }
            }
        }
    }

    override fun onButtonClick() {
        startActivity(Intent(this, com.example.scanner.MainActivity::class.java))
    }
}

interface ButtonClickListener {
    fun onButtonClick()
}

@Composable
fun Simple(buttonClickListener: ButtonClickListener) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Scanner app",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textDecoration = TextDecoration.None
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { buttonClickListener.onButtonClick() },
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Start Scanner",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.None
                ),
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            )
        }
    }
}
