package be.technifutur.evaandexo2

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import be.technifutur.evaandexo2.databinding.FragmentNewProductBinding

class NewProductFragment : Fragment() {
    private lateinit var binding :FragmentNewProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.option1.text = Type.BOISSON.label
        binding.option2.text = Type.ALIM.label
        binding.option3.text = Type.HYGIENE.label
        binding.option4.text = Type.MAISON.label

        binding.saveButton.setOnClickListener {
            val savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle
            val selected = when(binding.options.checkedRadioButtonId){
                R.id.option1 -> Type.BOISSON
                R.id.option2 -> Type.ALIM
                R.id.option3 -> Type.HYGIENE
                R.id.option4 -> Type.MAISON
                else -> null
            }
            if(selected != null && binding.nameField.text.isNotBlank() && binding.quantityField.text.isNotBlank()){
                val product = Product(binding.nameField.text.toString(), binding.quantityField.text.toString(), selected )
                savedStateHandle?.set(ShoppingListFragment.KEY_PROD, product)
                findNavController().navigateUp()
            }
            else{
                AlertDialog.Builder(requireContext()).setTitle("ERROR").setMessage("Un des champs est invalide").setPositiveButton("OK", null).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}