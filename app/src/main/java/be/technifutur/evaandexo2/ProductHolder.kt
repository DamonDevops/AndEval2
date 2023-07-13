package be.technifutur.evaandexo2

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.evaandexo2.databinding.ShoppingListItemBinding

class ProductHolder(private var viewBinding :ShoppingListItemBinding) :RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(product :Product, clickListener : ProductAdapter.OnItemClickListener){
        viewBinding.name.text = product.intitule
        viewBinding.name.setTextColor(ContextCompat.getColor(viewBinding.name.context , product.type.color))
        viewBinding.quantity.text = product.quantity
        viewBinding.productType.setImageResource(product.type.drawId)
        itemView.setOnClickListener {
            clickListener.onItemClicked(product)
        }
    }
}