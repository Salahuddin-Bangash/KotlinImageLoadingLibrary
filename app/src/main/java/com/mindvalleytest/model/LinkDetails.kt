package com.mindvalleytest.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * links model
 * @author Salahuddin
 */
class LinkDetails protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("self")
    @Expose
    var self: String? = null
    @SerializedName("html")
    @Expose
    var html: String? = null
    @SerializedName("photos")
    @Expose
    var photos: String? = null
    @SerializedName("likes")
    @Expose
    var likes: String? = null

    init {
        self = `in`.readString()
        html = `in`.readString()
        photos = `in`.readString()
        likes = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(self)
        parcel.writeString(html)
        parcel.writeString(photos)
        parcel.writeString(likes)
    }

    companion object {

        val CREATOR: Parcelable.Creator<LinkDetails> = object : Parcelable.Creator<LinkDetails> {
            override fun createFromParcel(`in`: Parcel): LinkDetails {
                return LinkDetails(`in`)
            }

            override fun newArray(size: Int): Array<LinkDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}