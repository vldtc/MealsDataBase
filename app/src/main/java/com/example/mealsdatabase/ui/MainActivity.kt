package com.example.mealsdatabase.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mealsdatabase.R
import com.example.mealsdatabase.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val signout = R.id.btnSignOut
        auth = Firebase.auth
        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_categories,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when (destination.id){
                R.id.navigation_home -> {
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
                R.id.navigation_categories -> {
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
                R.id.navigation_notifications -> {
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
                R.id.navigation_login -> {
                    supportActionBar?.hide()
                    navView.visibility = View.GONE
                }
                R.id.navigation_register -> {
                    supportActionBar?.hide()
                    navView.visibility = View.GONE
                }
                else -> {
                    navView.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnSignOut -> {
                Firebase.auth.signOut()
                analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundleOf(
                    FirebaseAnalytics.Param.ITEM_ID to "signOut",
                    FirebaseAnalytics.Param.ITEM_NAME to "userSignOutEvent",
                    FirebaseAnalytics.Param.CONTENT_TYPE to "singOutClick"
                ))


                Toast.makeText(baseContext,
                    "You have been signed out!",
                    Toast.LENGTH_LONG)
                    .show()
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_login)
                return true
            }
            else -> super.onOptionsItemSelected(item)


        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}