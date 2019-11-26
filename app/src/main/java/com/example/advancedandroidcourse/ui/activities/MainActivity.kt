package com.example.advancedandroidcourse.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.advancedandroidcourse.R
import com.example.advancedandroidcourse.ui.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAccion.setOnClickListener {
            userViewModel.retrieveUsers()
        }

        userViewModel.uiState.observe(this, Observer {
            val dataState = it ?: return@Observer
            if (dataState.user != null && !dataState.user.consumed){
                dataState.user.consume()?.let {
                    Toast.makeText(applicationContext, "LA DATA HA SIDO RECIBIDA", Toast.LENGTH_LONG).show()
                    println("LA DATA HA SIDO RECIBIDA")
                }
            }
            if (dataState.error != null && !dataState.error.consumed){
                dataState.error.consume()?.let {
                    Toast.makeText(applicationContext, resources.getString(it), Toast.LENGTH_LONG).show()
                    println("HUBO UN ERROR DE CONEXION")
                }
            }
        })
    }
}
