package com.example.myapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //кнопки навигации внизу экрана
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        //добавление фрагментов
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_search, R.id.navigation_favourites, R.id.navigation_adds, R.id.navigation_profile)
        )
        //фрагмент-контйнер
        val navController = findNavController(R.id.container_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
