package com.mindvalleytest.view.views.pinboardlist

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.mindvalleytest.appcontentloader.callback.ContentServiceObserver
import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload
import com.mindvalleytest.appcontentloader.models.ServiceImageDownload
import com.mindvalleytest.appcontentloader.utilities.ContentTypeServiceDownload
import com.mindvalleytest.R
import com.mindvalleytest.model.MasterDetails
import com.mindvalleytest.view.views.common.ItemActionListener

import java.util.ArrayList

import java.lang.String.format

/**
 * This adapter is used to render user pin item.
 *
 * @author Salahuddin
 * @version 1.0
 */

class PinboardListAdapter(private val context: Context, private val arrlstData: ArrayList<MasterDetails>, private val itemActionListener: ItemActionListener?) : RecyclerView.Adapter<PinboardListAdapter.UserDetailsHolder>() {
    private val inflater: LayoutInflater

    private val mProvider: ContentTypeServiceDownload

    init {
        inflater = LayoutInflater.from(context)
        mProvider = ContentTypeServiceDownload.getInstance()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsHolder {
        return UserDetailsHolder(inflater.inflate(R.layout.item_photo_pin, parent, false))
    }

    @SuppressLint("DefaultLocale")
    override fun onBindViewHolder(holder: UserDetailsHolder, position: Int) {
        val currentData = arrlstData[position]

        holder.txtvwLikes?.setText(format("%d", currentData.likes))
        holder.txtvwTitle!!.text = context.getString(R.string.clicked_by, currentData.user!!.name)

        if (currentData.isLikedByUser) {
            holder.txtvwLikes!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_liked, 0, 0, 0)
        } else {
            holder.txtvwLikes!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0)

        }


        val mDataTypeImageCancel = ServiceImageDownload(currentData.urlDetails!!.thumb!!, object : ContentServiceObserver {
            override fun onStart(mDownloadDataType: ServiceContentTypeDownload) {

            }

            override fun onSuccess(mDownloadDataType: ServiceContentTypeDownload) {
                holder.imgvwThumbnail!!.setImageBitmap((mDownloadDataType as ServiceImageDownload).imageBitmap)
            }

            override fun onFailure(mDownloadDataType: ServiceContentTypeDownload, statusCode: Int, errorResponse: ByteArray, e: Throwable) {
                holder.imgvwThumbnail!!.setImageResource(R.drawable.no_image)
            }

            override fun onRetry(mDownloadDataType: ServiceContentTypeDownload, retryNo: Int) {

            }
        })
        mProvider.getRequest(mDataTypeImageCancel)

        holder.cardView!!.setOnClickListener {
            itemActionListener?.onItemClicked(currentData, position)
        }
    }

    override fun getItemCount(): Int {
        return arrlstData.size
    }

    inner class UserDetailsHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgvwThumbnail: ImageView? = null
        var txtvwTitle: TextView? = null
        var txtvwLikes: TextView? = null
        var cardView: CardView? = null


        init {
//            ButterKnife.bind(this, view)
            imgvwThumbnail = view.findViewById<ImageView>(R.id.iv_imagethumbnail)
            txtvwTitle = view.findViewById<TextView>(R.id.tv_title)
            txtvwLikes = view.findViewById<TextView>(R.id.tv_likes)
            cardView = view.findViewById<CardView>(R.id.card_view)

        }
    }

    fun add(newData: MasterDetails) {

        arrlstData.add(arrlstData.size, newData)
        notifyItemInserted(arrlstData.size)

    }


}
