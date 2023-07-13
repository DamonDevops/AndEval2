package be.technifutur.evaandexo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import be.technifutur.evaandexo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.toolbar.titleToolbar.text = getString(R.string.shoppinglist)
        val navController = findNavController(R.id.navHostFragment)
        setSupportActionBar(binding.toolbar.root)
        val toolbar = AppBarConfiguration(
            setOf(
                R.id.shoppingListFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController, toolbar)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.shoppingListFragment -> binding.toolbar.titleToolbar.text = getString(R.string.shoppinglist)
                R.id.newProductFragment -> binding.toolbar.titleToolbar.text = getString(R.string.new_product)
            }
        }

        binding.toolbar.newButton.setOnClickListener {
            navController.navigate(R.id.newProductFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.navHostFragment.findNavController().navigateUp()
    }
}