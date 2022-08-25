package com.patproject.test.recviewadapterdelagate.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.patproject.test.api.models.DisplayableItem
import com.patproject.test.api.models.PhotoClass
import com.patproject.test.api.models.PhotoRefClass
import com.patproject.test.api.models.PostClass
import com.patproject.test.recviewadapterdelagate.appComponent
import com.patproject.test.recviewadapterdelagate.databinding.FragmentTestBinding
import com.patproject.test.recviewadapterdelagate.databinding.PictureItemLayoutBinding
import com.patproject.test.recviewadapterdelagate.databinding.ReferenceItemLayoutBinding
import com.patproject.test.recviewadapterdelagate.databinding.TextItemLayoutBinding
import com.patproject.test.utils.Resource
import com.patproject.test.utils.Status
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import javax.inject.Inject
import com.bumptech.glide.request.target.Target


class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!


    private val firstAdapter: AdapterDelegate by lazy{AdapterDelegate(requireContext())}
    private val secondAdapter: AdapterDelegate by lazy{AdapterDelegate(requireContext())}
    private val thirdAdapter: AdapterDelegate by lazy{AdapterDelegate(requireContext())}

    @Inject
    lateinit var testFragmentViewModelFactory: TestFragmentViewModel.Companion.TestFragmentViewModelFactory

    private val testFragmentViewModel: TestFragmentViewModel by viewModels { testFragmentViewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        runRequest(testFragmentViewModel.getRefPhotos(),thirdAdapter)
        runRequest(testFragmentViewModel.getPhotos(),firstAdapter)
        runRequest(testFragmentViewModel.getPosts(),secondAdapter)

        val recViewFirst = binding.firstRecView
        recViewFirst.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recViewFirst.adapter = firstAdapter

        val recViewSecond = binding.secondRecView
        recViewSecond.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recViewSecond.adapter = secondAdapter

        val recViewThird = binding.thirdRecView
        recViewThird.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recViewThird.adapter = thirdAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun runRequest(stateFlowResource: StateFlow<Resource<List<DisplayableItem>>>, adapter : AdapterDelegate) {
        lifecycleScope.launchWhenStarted {
            stateFlowResource
                .buffer()
                .collect { resource ->
                    when (resource.status) {
                        Status.ERROR -> {
                            Toast.makeText(context, "request exception", Toast.LENGTH_LONG).show()
                        }
                        Status.SUCCESS -> {
                            adapter.items = resource.data!!.subList(0,10)
                            adapter.notifyDataSetChanged()
                        }
                        Status.LOADING -> {}
                        else -> throw Exception("wrong resource work")
                    }
                }
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

fun photoAdapterDelegate(context: Context) =
    adapterDelegateViewBinding<PhotoClass, DisplayableItem, PictureItemLayoutBinding>(
        { layoutInflater, root -> PictureItemLayoutBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.title.text = item.title
            Glide.with(context)
                .load(item.url)
                .addListener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.i("GlideExceptionGlide",e.toString())
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .dontAnimate()
                .dontTransform()
                .into(binding.imageIcon)
            binding.imageId.text = item.id.toString()
        }
    }

fun photoRefAdapterDelegate(context: Context) =
    adapterDelegateViewBinding<PhotoRefClass, DisplayableItem, ReferenceItemLayoutBinding>(
        { layoutInflater, root -> ReferenceItemLayoutBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.imageIdRef.text = item.id.toString()
            binding.urlRef.text = item.url
            binding.refLayout.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                context.startActivity(intent)
            }
        }
    }

fun textAdapterDelegate() =
    adapterDelegateViewBinding<PostClass, DisplayableItem, TextItemLayoutBinding>(
        { layoutInflater, root -> TextItemLayoutBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.idText.text = item.id.toString()
            binding.bodyText.text = item.body
            binding.titleText.text = item.title
            binding.userIdText.text = item.userId.toString()

        }
    }