package be.technifutur.evaandexo2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.evaandexo2.databinding.ShoppingListItemBinding

class ProductAdapter(private var shoppingList :MutableList<Product>, private val clickListener :OnItemClickListener) :RecyclerView.Adapter<ProductHolder>(){
    private lateinit var binding :ShoppingListItemBinding

    interface OnItemClickListener{
        fun onItemClicked(product :Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        binding = ShoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount() = shoppingList.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(shoppingList[position], clickListener)
    }
}