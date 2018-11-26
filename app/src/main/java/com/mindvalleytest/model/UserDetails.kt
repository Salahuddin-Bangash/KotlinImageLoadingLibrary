package com.mindvalleytest.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * User Details model
 * @author Salahuddin
 */
class UserDetails protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: ProfileImage? = null
    @SerializedName("links")
    @Expose
    var linkDetails: LinkDetails? = null

    init {
        id = `in`.readString()
        username = `in`.readString()
        name = `in`.readString()
        profileImage = `in`.readParcelable(ProfileImage::class.java!!.getClassLoader())
        linkDetails = `in`.readParcelable(LinkDetails::class.java!!.getClassLoader())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeParcelable(profileImage, i)
        parcel.writeParcelable(linkDetails, i)
    }

    companion object {

        val CREATOR: Parcelable.Creator<UserDetails> = object : Parcelable.Creator<UserDetails> {
            override fun createFromParcel(`in`: Parcel): UserDetails {
                return UserDetails(`in`)
            }

            override fun newArray(size: Int): Array<UserDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}