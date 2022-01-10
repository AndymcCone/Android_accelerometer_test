package com.example.pallojuttu

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var tekstikentta: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        tekstikentta = findViewById(R.id.sensorText)

        sensoriHommat()
    }

    private fun sensoriHommat() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val sivulle = event.values[0]
            val ylosAlas = event.values[1]

            findViewById<MyView>(R.id.myView).setXY(sivulle , ylosAlas)

            val color = if (ylosAlas.toInt() == 0 && sivulle.toInt() == 0) Color.GREEN else Color.RED

            tekstikentta.setTextColor(color)

            tekstikentta.text = "Y-akseli ${String.format("%.1f", ylosAlas)}\nX-akseli ${String.format("%.1f", sivulle)} "
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }

    override fun onResume() {
        sensoriHommat()
        super.onResume()
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}