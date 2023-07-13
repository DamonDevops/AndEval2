package be.technifutur.evaandexo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.evaandexo2.databinding.FragmentShoppingListBinding
import com.google.android.material.snackbar.Snackbar

class ShoppingListFragment : Fragment(), ProductAdapter.OnItemClickListener {
    private lateinit var binding :FragmentShoppingListBinding
    private val cart = mutableListOf(
        Product("Coca", "10", Type.BOISSON),
        Product("Chips", "10", Type.ALIM),
        Product("Gel nettoyant", "10", Type.HYGIENE),
        Product("Bougies parfumées", "10", Type.MAISON)
    )
    private var liveData :MutableLiveData<Product>? = null

    companion object{
        const val KEY_PROD = "KEY_PROD"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle
        liveData = savedStateHandle?.getLiveData(KEY_PROD)
        liveData?.observe(viewLifecycleOwner) { result ->
            cart.add(result)
            //remove data from observer while we don't go in the onDestroy()
            savedStateHandle?.remove<Product>(KEY_PROD)
        }

        setupShoppingList()

        if(cart.isEmpty()){
            binding.noCart.text = getString(R.string.pas_d_articles)
            binding.noCart.setTextColor(ContextCompat.getColor(requireContext(), R.color.pink))
        }
        else{
            binding.noCart.text = getString(R.string.shopping_cart)
            binding.noCart.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }

    }

    private fun setupShoppingList(){
        val shoppingList = binding.shopList
        shoppingList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        shoppingList.adapter = ProductAdapter(cart, this)
    }

    override fun onItemClicked(product: Product) {
        Snackbar.make(requireContext(), requireView() , "${product.intitule}, ${product.quantity}", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        liveData?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }
}