package org.pahappa.checkintermal

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.pahappa.checkintermal.databinding.ActivityMainBinding
import org.pahappa.checkintermal.utils.LoadingDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timeView: TextView
    private lateinit var loadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
    }

    fun initViews() {

        timeView = binding.currentTime;



        loadingDialog = LoadingDialog(this, "Check In")

        binding.btnCheckIn.setOnClickListener { v ->
            //add a 2 second delay
            loadingDialog.startLoadingDialog()

            intent = Intent(this,  EnterPinActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            loadingDialog.dismissDialog()

            startActivity(intent)
        }

        // Use a coroutine to update the time in the background and update the UI on the main thread
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                val currentTimeString = updateTime()
                withContext(Dispatchers.Main) {
                    timeView.text = currentTimeString
                }
                kotlinx.coroutines.delay(1000) // Delay for 1 second
            }
        }
    }

    private fun updateTime(): String {
        val sdf = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
        return sdf.format(Date())
    }
}