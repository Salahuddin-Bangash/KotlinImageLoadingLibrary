package com.mindvalleytest.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Category model
 * @author Salahuddin
 */
class CategoryDetails protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("photo_count")
    @Expose
    var photoCount: Int = 0
    @SerializedName("links")
    @Expose
    var links: LinkDetails? = null
    @SerializedName("download")
    @Expose
    var download: String? = null

    init {
        id = `in`.readInt()
        title = `in`.readString()
        photoCount = `in`.readInt()
        links = `in`.readParcelable(LinkDetails::class.java!!.getClassLoader())
        download = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(photoCount)
        parcel.writeParcelable(links, i)
        parcel.writeString(download)
    }

    companion object {

        val CREATOR: Parcelable.Creator<CategoryDetails> = object : Parcelable.Creator<CategoryDetails> {
            override fun createFromParcel(`in`: Parcel): CategoryDetails {
                return CategoryDetails(`in`)
            }

            override fun newArray(size: Int): Array<CategoryDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}