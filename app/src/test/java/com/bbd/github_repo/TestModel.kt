package ir.bbd.githubrepo


import com.google.gson.annotations.SerializedName

data class TestModel(
    @SerializedName("data")
    val `data`: String
)