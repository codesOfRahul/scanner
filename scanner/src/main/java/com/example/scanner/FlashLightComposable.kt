package com.example.scanner

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TorchApplication(context: Context) {
    val torchStatus = remember {
        mutableStateOf(false)
    }
    val torchMsg = remember {
        mutableStateOf("Off")
    }
    // on below line we are creating a column,
    Column(
        // on below line we are adding
        // a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // on below line we are creating a text
        // for displaying torch status.
        Text(
            text = "Torch is " + torchMsg.value,
            // on below line we are
            // displaying a text color
            color = Color.Black,

            // on below line we are
            // setting font weight
            fontWeight = FontWeight.Bold,

            // on below line we are setting
            // font family
            fontFamily = FontFamily.Default,

            // on below line we are setting
            // font size and padding.
            fontSize = 20.sp, modifier = Modifier.padding(5.dp)
        )

        // on below line creating a switch for displaying a torch
        Switch(checked = torchStatus.value, onCheckedChange = {
            torchStatus.value = it
            // on below line we are creating
            // a variable for camera manager.
            lateinit var cameraManager: CameraManager

            // one below line we are
            // creating a string for camera ID
            lateinit var cameraID: String

            // on below line we are initializing our camera manager.
            cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            try {
                // O means back camera unit,
                // 1 means front camera unit
                // on below line we are getting camera id
                // for back camera as we will be using
                // torch for back camera
                cameraID = cameraManager.cameraIdList[0]
            } catch (e: Exception) {
                // on below line we are handling exception.
                e.printStackTrace()
            }

            if (torchStatus.value) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // if button is checked we are simply
                        // initializing our camera manager and
                        // setting torch mode for back camera
                        // as true to switch on torch
                        cameraManager.setTorchMode(cameraID, true)

                        // on below line we are simply displaying
                        // a toast message for torch on.
                        Toast.makeText(context, "Torch turned on..", Toast.LENGTH_LONG)
                            .show()

                        // on below line we are setting text
                        // to our text view as torch on.
                        torchMsg.value = "On"
                    }
                } catch (e: Exception) {
                    // on below line we are handling exception.
                    e.printStackTrace()
                }
            } else {
                // this condition will be called
                // when switch is off.
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // if button is unchecked this method will be called.
                        // In this method we will initializing our camera
                        // manager with camera id and setting torch to off.
                        cameraManager.setTorchMode(cameraID, false)

                        // on below line we are simply displaying a toast message.
                        Toast.makeText(context, "Torch turned off..", Toast.LENGTH_SHORT)
                            .show()

                        // on below line we are
                        // setting text to text view.
                        torchMsg.value = "Off"
                    }
                    // on below line we are
                    // handling exception
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
}
