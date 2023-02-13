package com.rgk.retomobile.module.home_screen.ui
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rgk.retomobile.R
import com.rgk.retomobile.databinding.ActivityHomeScreenBinding
import com.rgk.retomobile.module.common.intentFor
import com.rgk.retomobile.module.detail_screen.ui.DetailScreen
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var adapter: AdpLstPelicula
    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Peliculas"

        adapter = AdpLstPelicula(viewModel::onItemClicked)
        binding.rcData.adapter = adapter

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            layoutManager =  GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
        }else{
            layoutManager =  GridLayoutManager(
                this,
                3,
                GridLayoutManager.VERTICAL,
                false
            )
        }
        binding.rcData.layoutManager = layoutManager

        binding.swipe.setOnRefreshListener {
            searchView!!.onActionViewCollapsed()
            searchView!!.setQuery("",false)
            searchView.clearFocus()
            viewModel.getAllPelicula()
        }

        iniViewModel()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager =  GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
        } else {
            layoutManager =  GridLayoutManager(
                this,
                3,
                GridLayoutManager.VERTICAL,
                false
            )
        }
        binding.rcData.layoutManager = layoutManager
    }

    private fun iniViewModel() {
        viewModel.loadingGetAllPelicula.observe(this) {
            with(binding){
                if (it){
                    progressBar.visibility = View.VISIBLE
                    swipe.isRefreshing = true
                    swipe.isEnabled = false
                    rcData.visibility = View.GONE
                }else{
                    progressBar.visibility = View.GONE
                    swipe.isRefreshing = false
                    swipe.isEnabled = true
                    rcData.visibility = View.VISIBLE
                }
            }
        }
        viewModel.resultGetAllPelicula.observe(this){ model ->
            adapter.mData = model
            adapter.mDataFilter = model
            adapter.notifyDataSetChanged()
        }

        viewModel.itemSelected.observe(this){
           startActivity(intentFor<DetailScreen> {
               putExtra(DetailScreen.CLAVE_ITEM, it)
           })
        }

        viewModel.getAllPelicula()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuExit -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mnu_home,menu)
        val menuItem = menu!!.findItem(R.id.searchView)

        searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = "Buscar..."

        val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setTextColor(resources.getColor(R.color.white));
        editText.setHintTextColor(resources.getColor(R.color.purple_500))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String?): Boolean {
                adapter!!.filter.filter(text)
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                adapter!!.filter.filter(text)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

}