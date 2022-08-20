package com.patproject.test.recviewadapterdelagate.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.patproject.test.recviewadapterdelagate.appComponent
import com.patproject.test.recviewadapterdelagate.databinding.FragmentTestBinding
import com.patproject.test.utils.Status
import kotlinx.coroutines.flow.buffer
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            testFragmentViewModel.getPosts()
                .buffer()
                .collect{resource->
                when(resource.status){
                    Status.ERROR ->{
                        Toast.makeText(context,"request exception",Toast.LENGTH_LONG).show()
                    }
                    Status.SUCCESS ->{}
                    Status.LOADING ->{}
                    else -> throw Exception("wrong resource work")
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            testFragmentViewModel.getRefPhotos()
                .buffer()
                .collect{resource->
                    when(resource.status){
                        Status.ERROR ->{
                            Toast.makeText(context,"request exception",Toast.LENGTH_LONG).show()
                        }
                        Status.SUCCESS ->{}
                        Status.LOADING ->{}
                        else -> throw Exception("wrong resource work")
                    }
                }
        }
        lifecycleScope.launchWhenStarted {
            testFragmentViewModel.getPhotos()
                .buffer()
                .collect{resource->
                    when(resource.status){
                        Status.ERROR ->{
                            Toast.makeText(context,"request exception",Toast.LENGTH_LONG).show()
                        }
                        Status.SUCCESS ->{}
                        Status.LOADING ->{}
                        else -> throw Exception("wrong resource work")
                    }
                }
        }

    }

}