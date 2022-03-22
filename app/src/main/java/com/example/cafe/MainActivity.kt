package com.example.cafe




import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.HandlerCompat.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe.accounts.AccountActivity
import com.example.cafe.catalog.CatalogueActivity
import com.example.cafe.data.MyData
import com.example.cafe.databinding.ActivityMainBinding
import com.example.cafe.home.HomeActivity
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var toggle:ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getHome()


        binding.naviview.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                  //  Toast.makeText(this, "home ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.catalogue -> {
                    val intent = Intent(this@MainActivity, SettingActivity::class.java)
                    startActivity(intent)
                   // Toast.makeText(this, "catalogue", Toast.LENGTH_SHORT).show()
                }
                R.id.search -> {
                   // Toast.makeText(this, "setting ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, CatalogueActivity::class.java)
                    startActivity(intent)
                }
                R.id.profile -> {
                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(intent)

                    //Toast.makeText(this, "profile ", Toast.LENGTH_SHORT).show()
                }
                R.id.setting -> {
                    val intent = Intent(this@MainActivity, AccountActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this, "setting ", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }





    }












    private fun getHome() {
        binding.bottom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile1-> {
                    val intent = Intent(this@MainActivity,ProfileActivity::class.java)
                    startActivity(intent)

                }
                        R.id.home1-> {
                    //  Toast.makeText(this@MainActivity,"home ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity,HomeActivity::class.java)
                    startActivity(intent)}
                        R.id.catalogue1-> {
                    val intent = Intent(this@MainActivity,SettingActivity::class.java)
                    startActivity(intent)
                }
                        R.id.setting1-> {
                    val intent = Intent(this@MainActivity, AccountActivity::class.java)
                    startActivity(intent)
                   // Toast.makeText(this@MainActivity, "setting ", Toast.LENGTH_SHORT).show()
                }


            }
            true

            }


        }
    }


