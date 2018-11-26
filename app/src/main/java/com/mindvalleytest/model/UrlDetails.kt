package com.mindvalleytest.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Url Details model
 * @author Salahuddin
 */
class UrlDetails protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("raw")
    @Expose
    var raw: String? = null
    @SerializedName("full")
    @Expose
    var full: String? = null
    @SerializedName("regular")
    @Expose
    var regular: String? = null
    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

    init {
        raw = `in`.readString()
        full = `in`.readString()
        regular = `in`.readString()
        small = `in`.readString()
        thumb = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(raw)
        parcel.writeString(full)
        parcel.writeString(regular)
        parcel.writeString(small)
        parcel.writeString(thumb)
    }

    companion object {

        val CREATOR: Parcelable.Creator<UrlDetails> = object : Parcelable.Creator<UrlDetails> {
            override fun createFromParcel(`in`: Parcel): UrlDetails {
                return UrlDetails(`in`)
            }

            override fun newArray(size: Int): Array<UrlDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}