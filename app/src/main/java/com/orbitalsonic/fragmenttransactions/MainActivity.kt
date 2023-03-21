package com.orbitalsonic.fragmenttransactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.orbitalsonic.fragmenttransactions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        fragmentSelection("home_screen")

        binding.btnHome.setOnClickListener {
            fragmentSelection("home_screen")
        }
        binding.btnCamera.setOnClickListener {
            fragmentSelection("camera_screen")
        }
        binding.btnGallery.setOnClickListener {
            fragmentSelection("gallery_screen")
        }
        binding.btnSettings.setOnClickListener {
            fragmentSelection("setting_screen")
        }
    }

    fun fragmentSelection(frag: String) {
        try {
            when (frag) {
                "home_screen" -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentHome()).commit()
                }
                "gallery_screen" -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentGallery()).commit()
                }
                "setting_screen" -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentSettings()).commit()
                }
                "camera_screen" -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentCamera()).commit()
                }

            }
        } catch (e: Exception) {
            Log.d("fragmentSelectionTAG", "fragmentSelection: ${e.message}")
        }

    }

}