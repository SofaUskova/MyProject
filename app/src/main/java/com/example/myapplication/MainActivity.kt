package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.ui.search.`interface`.OnActivityDataListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var mListener: OnActivityDataListener? = null
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search,
                R.id.navigation_favourite,
                R.id.navigation_add_information,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_filter_on_money, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            //TODO
            val fragment = supportFragmentManager.fragments.first().childFragmentManager.fragments.first()
            mListener = fragment as OnActivityDataListener

            if(item.icon.constantState?.equals(ResourcesCompat.getDrawable(resources, R.drawable.ic_filter_small, null)?.constantState)!!) {
                mListener?.onActivityDataListener(true)
                item.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_filter_big, null)
            } else {
                mListener?.onActivityDataListener(false)
                item.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_filter_small, null)
            }

        }
        return true
    }
}
