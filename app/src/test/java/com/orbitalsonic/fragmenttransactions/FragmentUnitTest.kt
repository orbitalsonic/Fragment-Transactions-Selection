package com.orbitalsonic.fragmenttransactions

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.*
import com.orbitalsonic.fragmenttransactions.databinding.ActivityMainBinding
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.util.ReflectionHelpers


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MainActivityTest{

    lateinit var mainActivity: MainActivity

    private var fragmentHome: FragmentScenario<FragmentCamera>?= null

    private lateinit var binding: ActivityMainBinding


    val arguments = Bundle()

    @Before
    fun setup() {
        mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().get()

        val layoutInflater =
            ApplicationProvider.getApplicationContext<Context>()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as
                    LayoutInflater
        binding = ActivityMainBinding.inflate(layoutInflater)

    }

    @Test
    fun testFragmentLifeCycle() {
        val b = Bundle()
        fragmentHome =
            launchFragmentInContainer(
                fragmentArgs = arguments, initialState = Lifecycle.State.INITIALIZED
            )
        fragmentHome!!.moveToState(Lifecycle.State.CREATED)
        fragmentHome!!.onFragment { fragment ->
            fragment.onCreate(b)
            fragmentHome!!.onFragment { fragment ->
                fragment.onStart()
                fragmentHome!!.onFragment { fragment ->
                    fragment.onResume()
                    fragmentHome!!.onFragment { fragment ->
                        fragment.onPause()
                        fragmentHome!!.onFragment { fragment ->
                            fragment.onStop()
                            fragmentHome!!.onFragment { fragment -> fragment.onDestroy() }
                        }
                    }
                }
            }
        }
    }


    @Test
    fun test_camera_fragment() {
        val scenario = launchFragmentInContainer<FragmentCamera>()
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val binding = ReflectionHelpers.getField<ActivityMainBinding>(mainActivity,"binding")

            binding.btnCamera.performClick()


        }

    }

    @Test
    fun test_home_fragment() {
        val scenario = launchFragmentInContainer<FragmentCamera>()
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val binding = ReflectionHelpers.getField<ActivityMainBinding>(mainActivity,"binding")

            binding.btnHome.performClick()


        }

    }

    @Test
    fun test_gallery_fragment() {
        val scenario = launchFragmentInContainer<FragmentCamera>()
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val binding = ReflectionHelpers.getField<ActivityMainBinding>(mainActivity,"binding")

            binding.btnGallery.performClick()
        }
    }

    @Test
    fun test_setting_fragment() {
        val scenario = launchFragmentInContainer<FragmentCamera>()
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val binding = ReflectionHelpers.getField<ActivityMainBinding>(mainActivity,"binding")

            binding.btnSettings.performClick()
        }
    }

}