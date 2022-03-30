package com.amar.lockapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.amar.lockapp.LocksViewModel
import com.amar.lockapp.databinding.ActivityMainBinding
import com.amar.lockapp.ui.adapters.LockRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: LocksViewModel by viewModels()
    lateinit var adapter: LockRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = LockRecyclerViewAdapter(listOf(), "")
        binding.recyclerView.adapter = adapter

        setObservers()

        viewModel.getLocks()

        binding.searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getLocksByFilter(s.toString())
            }
        })

    }

    fun setObservers() {

        lifecycleScope.launchWhenCreated {
            viewModel.lockListFiltered.collect {
                adapter.setItems(it, binding.searchET.text.toString())
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.showProgress.collect { show ->
                binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

    }
}