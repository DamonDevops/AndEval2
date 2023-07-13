package be.technifutur.evaandexo2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Type(val label :String, val color :Int, val drawId :Int){
    ALIM("Alimentation", R.color.blue, R.drawable.food),
    BOISSON("Boisson", R.color.purple, R.drawable.drink),
    HYGIENE("Hygiène", R.color.yellow, R.drawable.health),
    MAISON("Maison", R.color.pink, R.drawable.home)
}

@Parcelize
class Product(val intitule :String, val quantity :String, val type :Type) :Parcelable