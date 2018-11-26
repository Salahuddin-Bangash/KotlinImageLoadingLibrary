package com.mindvalleytest.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * profile image details model
 * @author Salahuddin
 */
class ProfileImage protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("medium")
    @Expose
    var medium: String? = null
    @SerializedName("large")
    @Expose
    var large: String? = null

    init {
        small = `in`.readString()
        medium = `in`.readString()
        large = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(small)
        parcel.writeString(medium)
        parcel.writeString(large)
    }

    companion object {

        val CREATOR: Parcelable.Creator<ProfileImage> = object : Parcelable.Creator<ProfileImage> {
            override fun createFromParcel(`in`: Parcel): ProfileImage {
                return ProfileImage(`in`)
            }

            override fun newArray(size: Int): Array<ProfileImage?> {
                return arrayOfNulls(size)
            }
        }
    }
}