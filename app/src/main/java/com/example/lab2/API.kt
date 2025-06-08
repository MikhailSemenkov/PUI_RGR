import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

object RetrofitInstance {
    private const val BASE_URL = "https://reqres.in/"

    fun getInstance(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
    }
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String
)

data class RegisterRequest(
    val email: String,
    val password: String
)

data class RegisterResponse(
    val id: String,
    val token: String
)

data class DataItem(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class DataResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<DataItem>
)

interface LoginApi {
    @Headers("x-api-key: reqres-free-v1")
    @POST("api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}

interface RegisterAPI {
    @Headers("x-api-key: reqres-free-v1")
    @POST("api/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}

interface DataApi {
    @Headers("x-api-key: reqres-free-v1")
    @GET("api/users?page=1")
    fun getData(): Call<DataResponse>
}
