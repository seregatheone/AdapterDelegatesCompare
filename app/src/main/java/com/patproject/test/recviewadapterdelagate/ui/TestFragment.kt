package com.patproject.test.recviewadapterdelagate.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.patproject.test.recviewadapterdelagate.appComponent
import com.patproject.test.recviewadapterdelagate.databinding.FragmentTestBinding
import javax.inject.Inject


class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var testFragmentViewModelFactory : TestFragmentViewModel.Companion.TestFragmentViewModelFactory

    private val testFragmentViewModel : TestFragmentViewModel by viewModels{testFragmentViewModelFactory}

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        Log.i("getPosts",testFragmentViewModel.getPosts().toString())
//        Log.i("getPhotos",testFragmentViewModel.getPhotos().toString())
//        Log.i("getRefPhotos",testFragmentViewModel.getRefPhotos().toString())
//
//        super.onViewCreated(view, savedInstanceState)
//    }
}