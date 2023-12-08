package com.ppb1.uasppb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ppb1.uasppb.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewTitle: TextView = binding.textTitle
        val textViewName: TextView = binding.textNama
        val textViewNim: TextView = binding.textNim

        textViewTitle.text = "Ujian Akhir Semester Pemrograman Piranti Bergerak"
        textViewName.text = "Charisty Elisabeth Helen Bessy"
        textViewNim.text = "(2142101941)"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
