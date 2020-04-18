package hr.johndoeveloper.rotationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.TextViewCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAutoSizing()

    }

    private fun setupAutoSizing() {
        TextViewCompat.setAutoSizeTextTypeWithDefaults(
            textViewResultantAcceleration,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        )
    }

    /**
     * Function for testing practices only
     * **/
    private fun testCustomView(){
    }


}
