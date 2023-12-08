package com.ppb1.uasppb.ui.seafood

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppb1.uasppb.MealAdapter
import cz.msebera.android.httpclient.Header
import org.json.JSONException
import org.json.JSONObject

import com.ppb1.uasppb.databinding.FragmentSeafoodBinding

class SeafoodFragment : Fragment() {

    private var _binding: FragmentSeafoodBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[SeafoodViewModel::class.java]
        _binding = FragmentSeafoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView= binding.rvSeafood
        progressBar= binding.pgSeafood

        val layoutManager =LinearLayoutManager(view.context)
        val itemDecoration = DividerItemDecoration(view.context, layoutManager.orientation)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(itemDecoration)
        fetchData()
    }

    private fun fetchData(){
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject) {
                try {
                    progressBar.visibility = View.GONE
                    val mealsArray = response.getJSONArray("meals")
                    mealAdapter = MealAdapter(mealsArray)
                    recyclerView.adapter = mealAdapter
                } catch (_: JSONException) {
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                throwable: Throwable?, errorResponse:
                JSONObject?) {

                progressBar.visibility = View.GONE
                Toast.makeText(recyclerView.context, "error", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}