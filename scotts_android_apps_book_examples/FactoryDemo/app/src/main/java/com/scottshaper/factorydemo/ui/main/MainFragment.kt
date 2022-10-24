package com.scottshaper.factorydemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scottshaper.factorydemo.Coffee
import com.scottshaper.factorydemo.CoffeeFactory
import com.scottshaper.factorydemo.R
import com.scottshaper.factorydemo.databinding.FragmentMainBinding


//project was based on this webpage
//https://www.codementor.io/@devmike01/factory-pattern-here-is-how-its-done-on-android-t0jghhfof
class MainFragment : Fragment() {

    private var isChanged: Boolean =false
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.coffeeBtn.setOnClickListener(this::changeCoffee)
    }

    /**
     * Change coffee recipe and name when button is clicked
     */
    fun changeCoffee(view: View){
        var factory: Coffee
        if (isChanged){
            factory = CoffeeFactory.getCoffee(CoffeeFactory.Type.AMERICANO)
            isChanged = false
        }else{
            factory = CoffeeFactory.getCoffee(CoffeeFactory.Type.LATTE)
            isChanged = true
        }


        changeCoffeeType(factory.recipe(), factory.name())
    }

    /**
     * Method that changes the coffee type
     */
    private fun changeCoffeeType(recipe: String, name: String){

        //coffee_recipe.text = recipe
        binding.coffeeRecipe.text = recipe
        binding.coffeeName.text = name

        //coffee_name.text =name
    }

}