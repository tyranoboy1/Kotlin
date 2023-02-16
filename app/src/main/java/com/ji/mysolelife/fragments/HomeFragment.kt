package com.ji.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ji.mysolelife.R
import com.ji.mysolelife.contentsList.BookmarkRVAdapter
import com.ji.mysolelife.contentsList.ContentListActivity
import com.ji.mysolelife.contentsList.ContentModel
import com.ji.mysolelife.databinding.FragmentHomeBinding
import com.ji.mysolelife.utils.FBRef

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val TAG = HomeFragment::class.java.simpleName

    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()


    lateinit var rvAdapter : BookmarkRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("HomeFragment", "onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        binding.category1.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category","category1")
            startActivity(intent)
        }
        binding.category2.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category2")
            startActivity(intent)
        }
        binding.category3.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category3")
            startActivity(intent)
        }
        binding.category4.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category4")
            startActivity(intent)
        }
        binding.category5.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category5")
            startActivity(intent)
        }
        binding.category6.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category6")
            startActivity(intent)
        }
        binding.category7.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category7")
            startActivity(intent)
        }
        binding.category8.setOnClickListener {
            val intent = Intent(context, ContentListActivity::class.java)
            intent.putExtra("category", "category8")
            startActivity(intent)
        }

        binding.tipTap.setOnClickListener {
            Log.d("HomeFragment", "tipTap")
            it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }

        binding.talkTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)

        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)
        }



        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)

        val rv : RecyclerView = binding.mainRV
        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        getCategoryData()

        return binding.root
    }

    private fun getCategoryData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(ContentModel::class.java)

                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())


                }
                rvAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.category1.addValueEventListener(postListener)
        FBRef.category2.addValueEventListener(postListener)
        FBRef.category3.addValueEventListener(postListener)
        FBRef.category4.addValueEventListener(postListener)
        FBRef.category5.addValueEventListener(postListener)
        FBRef.category6.addValueEventListener(postListener)
        FBRef.category7.addValueEventListener(postListener)
        FBRef.category8.addValueEventListener(postListener)


    }


}