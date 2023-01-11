package com.omer.retrofitapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omer.retrofitapp.R
import com.omer.retrofitapp.adapter.PostRecyclerAdapter
import com.omer.retrofitapp.databinding.FragmentPostListBinding
import com.omer.retrofitapp.model.Post
import com.omer.retrofitapp.viewmodel.PostListViewModel


class PostListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: PostRecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel : PostListViewModel

    //private lateinit var binding: FragmentPostListBinding
    // private val postList = ArrayList<Post>()
    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = FragmentPostListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(PostListViewModel::class.java)
        viewModel.refreshData()

        val postList: ArrayList<Post> = ArrayList(viewModel.posts.getValue())

        viewManager = LinearLayoutManager(activity)
        viewAdapter = PostRecyclerAdapter(postList)

        recyclerView = binding.recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        observeLiveData()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        swipeRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        // fragment destroy
        _binding = null
    }
    fun <T> MutableList<T>.toArrayList(): ArrayList<T> {
        val arrayList = ArrayList<T>(this.size)
        arrayList.addAll(this)
        return arrayList
    }
    fun swipeRefresh()
    {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            observeLiveData()
            binding.swipeRefreshLayout.isRefreshing=false
        }
    }
    fun observeLiveData() {
        viewModel.posts.observe(viewLifecycleOwner) { post ->
            post?.let {
                // besinler gelirse recycler view gorunur(visible) olsun
                binding.recyclerView.visibility = View.VISIBLE
                viewAdapter.besinListUpdate(post)
            }
        }

        // hata mesajı true gelirse hata mesjaını göster gelmezse gösterme
        viewModel.postErrorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it) {
                    binding.textView.visibility = View.VISIBLE
                } else {
                    binding.textView.visibility = View.GONE
                }
            }

        }

        // besinler yukleniyor ise yukleniyor goster  hata mesajı ve recycler gizle yuklenmiyor veya yüklenmiş ise yükleniyor gösterme
        viewModel.postLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    binding.textView.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                } else {
                    binding.progressBar.visibility = View.GONE

                }

            }
        }
    }
}