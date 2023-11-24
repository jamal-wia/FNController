package com.jamal_aliev.fncontroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.jamal_aliev.fncontroller.core.AndroidNavigationContextChangerFragment
import com.jamal_aliev.fncontroller.core.provider.OnNavigationUpProvider

class MainActivity : AppCompatActivity() {

    private lateinit var rootContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootContainer = findViewById(R.id.root_navigation_changer)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(rootContainer.id, AndroidNavigationContextChangerFragment())
                .commitNow()

            App.navigator.reset(Screens.AppTabNavigationControllerScreen)
        }
    }

    override fun onNavigateUp(): Boolean {
        return (supportFragmentManager.fragments.first { it is OnNavigationUpProvider }
                as OnNavigationUpProvider)
            .onNavigationUp() == Unit
    }

}