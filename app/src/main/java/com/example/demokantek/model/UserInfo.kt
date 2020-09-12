package com.example.demokantek.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserInfo() : Parcelable {
    @SerializedName("id")
    val id: Int? = 0
    @SerializedName("email")
    val email: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("phone")
    val phone: String? = null
    @SerializedName("address")
    val address: String? = null
    @SerializedName("latitude")
    val latitude: String? = null
    @SerializedName("longitude")
    val longitude: String? = null
    @SerializedName("birthday")
    val birthday: String? = null
    @SerializedName("gender")
    val gender: Int? = 0
    @SerializedName("facebook_id")
    val facebook_id: Int? = 0
    @SerializedName("google_id")
    val google_id: Int? = 0
    @SerializedName("device_token")
    val device_token: String? = null
    @SerializedName("cover")
    val cover: String? = null
    @SerializedName("type")
    val type: Int? = null
    @SerializedName("receive_notification")
    val receive_notification: Boolean? = false
    @SerializedName("access_token")
    val access_token: String? = null
    @SerializedName("avatar_url")
    val avatar_url: String? = null
    @SerializedName("cover_url")
    val cover_url: String? = null
    @SerializedName("friend_count")
    val friend_count: Int? = 0
    @SerializedName("follower_count")
    val follower_count: Int? = 0
    @SerializedName("review_count")
    val review_count: Int? = 0
    @SerializedName("check_in_count")
    val check_in_count: Int? = 0
    @SerializedName("media_count")
    val media_count: Int? = 0
    @SerializedName("friend_status")
    val friend_status: String? = null
    @SerializedName("follow_status")
    val follow_status: Int? = 0
    @SerializedName("is_influence")
    val is_influence: Boolean? = false

    constructor(parcel: Parcel) : this() {
    }
}