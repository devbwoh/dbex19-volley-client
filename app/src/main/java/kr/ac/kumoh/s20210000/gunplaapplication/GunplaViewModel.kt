package kr.ac.kumoh.s20210000.gunplaapplication
// package는 본인의 것 사용

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class GunplaViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val QUEUE_TAG = "VolleyRequest"
    }

    private var mQueue: RequestQueue

    data class Mechanic (
        val id: Int,
        val name: String,
        val model: String,
        val manufacturer: String,
        val armor: String,
        val height: Float,
        val weight: Float
    )

    val list = MutableLiveData<ArrayList<Mechanic>>()
    private val gunpla = ArrayList<Mechanic>()

    init {
        list.value = gunpla
        mQueue = VolleyRequest.getInstance(application).requestQueue
    }

    fun requestMechanic() {
        // NOTE: 서버 주소는 본인의 서버 주소 사용할 것
        val url = "https://dbex19-volley-server-aizgq.run.goorm.io/gunpladb/mechanic/"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
                gunpla.clear()
                list.value = gunpla
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        request.tag = QUEUE_TAG
        mQueue.add(request)
    }

    fun getGunpla(i: Int) = gunpla[i]

    fun getSize() = gunpla.size

    override fun onCleared() {
        super.onCleared()
        mQueue.cancelAll(QUEUE_TAG)
    }
}