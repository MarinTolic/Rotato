package hr.johndoeveloper.rotationapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.johndoeveloper.rotationapp.viewmodel.MainActivityViewModel
import hr.johndoeveloper.rotationapp.R
import hr.johndoeveloper.rotationapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = initViewModel()
        setContentView(bindData())
        setupAutoSizing()
        mainActivityViewModel.startAccelerometer()
    }

    private fun initViewModel(): MainActivityViewModel =
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainActivityViewModel::class.java)

    private fun bindData(): View {
        val binder: ActivityMainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )
        binder.viewModel = mainActivityViewModel
        binder.lifecycleOwner = this
        return binder.root
    }

    private fun setupAutoSizing() {
        TextViewCompat.setAutoSizeTextTypeWithDefaults(
            textViewResultantAcceleration,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityViewModel.stopAccelerometer()
    }

}
