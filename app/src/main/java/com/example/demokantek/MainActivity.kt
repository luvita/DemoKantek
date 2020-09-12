package com.example.demokantek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.demokantek.network.mainModule
import com.example.demokantek.view.LoginFragment
import org.koin.android.ext.android.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin(this,
            listOf(mainModule),
            loadPropertiesFromFile = true)
        supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment(), "").addToBackStack(null).commit()
    }
}


// Extension function to replace fragment
fun AppCompatActivity.replaceFragment(fragment:Fragment){
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.container,fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}