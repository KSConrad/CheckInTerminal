package org.pahappa.checkintermal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import org.pahappa.checkintermal.databinding.ActivityEnterPinBinding
import org.pahappa.checkintermal.utils.LoadingDialog

class EnterPinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterPinBinding

    private lateinit var code1: TextInputEditText
    private lateinit var code2: TextInputEditText
    private lateinit var code3: TextInputEditText
    private lateinit var code4: TextInputEditText

    private lateinit var customLoadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize View Items
        binding = ActivityEnterPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        customLoadingDialog = LoadingDialog(this, "Verifying Code...")

//        code1 = binding.code1
//        code2 = binding.code2
//        code3 = binding.code3
//        code4 = binding.code4

        binding.code1.addTextChangedListener {
            if (it?.length == 1) {
                binding.code2.requestFocus()
            }
        }

        binding.code2.addTextChangedListener {
            if (it?.length == 1) {
                binding.code3.requestFocus()
            }
        }

        binding.code3.addTextChangedListener {
            if (it?.length == 1) {
                binding.code4.requestFocus()
            }
        }

        binding.code4.addTextChangedListener {
            if (it?.length == 1) {
                verifyPassword()
            }
        }
    }

    private fun verifyPassword() {
        //perform network call to verify password
        proceedToCamera();
    }

    private fun proceedToCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

}