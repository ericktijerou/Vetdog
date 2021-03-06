package erick.mobile.data.remote.api

import com.serjltt.moshi.adapters.Wrapped
import erick.mobile.data.remote.model.DogRemoteModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheDogService {
    @GET("images/search?mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0")
    fun getDogs(@Query("size") size: String,
                @Query("limit") limit: Int): Observable<List<DogRemoteModel>>

    @GET("images/{id}")
    fun getDog(@Path("id") id: String): Observable<DogRemoteModel>
}