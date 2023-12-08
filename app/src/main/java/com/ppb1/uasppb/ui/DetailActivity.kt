package com.ppb1.uasppb.ui

//import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
//import android.widget.VideoView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.ppb1.uasppb.R
import cz.msebera.android.httpclient.Header
import org.json.JSONException
import org.json.JSONObject

const val ID_MEAL ="111"

class DetailActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var tvTags: TextView
    private lateinit var tvUrl: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        progressBar= findViewById(R.id.pg_detail)
        textView= findViewById(R.id.tv_meal)
        tvTags= findViewById(R.id.tv_tags)
        tvUrl= findViewById(R.id.tv_link)


        val idMeal = intent.getStringExtra(ID_MEAL)
        fetchData(idMeal)
    }

    private fun fetchData(idMeal:String?){
        progressBar.visibility = View.VISIBLE

        val client = AsyncHttpClient()
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=${ idMeal }"
        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject
            ) {
                try {
                    progressBar.visibility = View.GONE
                    val mealsArray = response.getJSONArray("meals").getJSONObject(0)
                    textView.text = mealsArray.getString( "strMeal" )
                    tvTags.text = mealsArray.getString("strTags")
                    tvUrl.text = mealsArray.getString( "strYoutube" )
                } catch (_: JSONException) { //'_' is 'e'
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                throwable: Throwable?, errorResponse:
                JSONObject?) {

                progressBar.visibility = View.GONE
                Toast.makeText(this@DetailActivity, "error", Toast.LENGTH_LONG).show()
            }
        })
    }

}