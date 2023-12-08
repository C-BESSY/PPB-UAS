package com.ppb1.uasppb

import android.os.Bundle
//import android.util.Log
//import android.view.Gravity
import android.view.Menu
//import android.view.MenuItem
//import android.view.View
//import android.widget.LinearLayout
//import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.view.menu.MenuView.ItemView
//import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
//import androidx.fragment.app.Fragment
import com.ppb1.uasppb.databinding.ActivityMainBinding
//import com.ppb1.uasppb.ui.beef.BeefFragment

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_beef, R.id.nav_chicken, R.id.nav_seafood
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val home = navView.menu.findItem(R.id.menu_home)
        home.setOnMenuItemClickListener{
            navController.navigate(R.id.nav_home)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val beef = navView.menu.findItem(R.id.menu_beef)
        beef.setOnMenuItemClickListener{
            navController.navigate(R.id.nav_beef)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val chicken = navView.menu.findItem(R.id.menu_chicken)
        chicken.setOnMenuItemClickListener{
            navController.navigate(R.id.nav_chicken)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val seafood = navView.menu.findItem(R.id.menu_seafood)
        seafood.setOnMenuItemClickListener{
            navController.navigate(R.id.nav_seafood)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}