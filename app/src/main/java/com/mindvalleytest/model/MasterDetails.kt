package com.mindvalleytest.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * pinboard response model
 * @author Salahuddin
 */
class MasterDetails protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("width")
    @Expose
    var width: Int = 0
    @SerializedName("height")
    @Expose
    var height: Int = 0
    @SerializedName("color")
    @Expose
    var color: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Int = 0
    @SerializedName("liked_by_user")
    @Expose
    var isLikedByUser: Boolean = false
    @SerializedName("user")
    @Expose
    var user: UserDetails? = null
    @SerializedName("current_user_collections")
    @Expose
    var currentUserCollections: List<Any>? = null
    @SerializedName("urls")
    @Expose
    var urlDetails: UrlDetails? = null
    @SerializedName("categories")
    @Expose
    var categories: List<CategoryDetails>? = null
    @SerializedName("links")
    @Expose
    var links: LinkDetails? = null

    init {
        id = `in`.readString()
        createdAt = `in`.readString()
        width = `in`.readInt()
        height = `in`.readInt()
        color = `in`.readString()
        likes = `in`.readInt()
        isLikedByUser = `in`.readByte().toInt() != 0
        user = `in`.readParcelable(UserDetails::class.java!!.getClassLoader())
        urlDetails = `in`.readParcelable(UrlDetails::class.java!!.getClassLoader())
        categories = `in`.createTypedArrayList(CategoryDetails.CREATOR)
        links = `in`.readParcelable(LinkDetails::class.java!!.getClassLoader())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(createdAt)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(color)
        parcel.writeInt(likes)
        parcel.writeByte((if (isLikedByUser) 1 else 0).toByte())
        parcel.writeParcelable(user, i)
        parcel.writeParcelable(urlDetails, i)
        parcel.writeTypedList(categories)
        parcel.writeParcelable(links, i)
    }

    companion object {

        val CREATOR: Parcelable.Creator<MasterDetails> = object : Parcelable.Creator<MasterDetails> {
            override fun createFromParcel(`in`: Parcel): MasterDetails {
                return MasterDetails(`in`)
            }

            override fun newArray(size: Int): Array<MasterDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}