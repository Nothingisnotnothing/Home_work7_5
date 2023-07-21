package kg.vohkysan.home_work7_5_1.ui.fragments.names

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kg.vohkysan.home_work7_5_1.R
import kg.vohkysan.home_work7_5_1.databinding.FragmentNamesBinding

class NamesFragment : Fragment() {
    private lateinit var binding: FragmentNamesBinding
    private val listOfNames = arrayListOf<String>()
    private lateinit var myName: String
    private var count = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun checkNames() {
        val charFromMyName = myName.toCharArray().toList()
        val uniqueCharFromMyName = hashSetOf<Char>()
        for (char in charFromMyName) {
            if (listOfNames.contains(char.toString())) {
                uniqueCharFromMyName.add(char)
            }
        }
        binding.tvAnswer.text = uniqueCharFromMyName.toString()
    }

    private fun initClickListeners() {
        with(binding) {
            tvNames.text = (getString(R.string.remainder, count))
            btnEnterName.setOnClickListener {
                if (!etName.text.isNullOrEmpty() && count != 0) {
                    count--
                    tvNames.text = (getString(R.string.remainder, count))
                    listOfNames.add(etName.text.toString())
                    etName.text?.clear()
                } else if (!etName.text.isNullOrEmpty() && count == 0) {
                    tvNames.text = (getString(R.string.enter_your_name))
                    myName = etName.text.toString()
                    etName.text?.clear()
                    tvAnswer.isVisible = true
                    checkNames()
                } else {
                    Toast.makeText(requireContext(), "Enter the name", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            fab.setOnClickListener {
                findNavController().navigate(R.id.navigation_calculator)
            }
        }
    }
}
