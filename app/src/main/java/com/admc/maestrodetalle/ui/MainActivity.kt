package com.admc.maestrodetalle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.admc.maestrodetalle.R
import com.admc.maestrodetalle.databinding.ActivityMainBinding
import com.admc.maestrodetalle.ui.adapter.DogAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        inintRecyclerView()
        viewModel.isLoanding.observe(this, Observer {
            binding.progress.isVisible = it
        })


    }

    private fun inintRecyclerView() {
        adapter = DogAdapter(emptyList())
        viewModel.dogImages.observe(this, Observer { imgDogs ->
            adapter = DogAdapter(imgDogs)
            binding.rvDog.setHasFixedSize(true)
            binding.rvDog.adapter = adapter
            binding.rvDog.layoutManager = LinearLayoutManager(this)
        })
    }
    private fun searchByName(query: String){
        viewModel.fetchDogImages("$query/images"){showError()}

        hiddenKeyBoard()

    }

    private fun hiddenKeyBoard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }


    private fun showError() {
       Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.toLowerCase(Locale.ROOT))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}