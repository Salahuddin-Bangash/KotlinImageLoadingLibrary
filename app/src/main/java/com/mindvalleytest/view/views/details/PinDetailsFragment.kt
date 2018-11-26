package com.mindvalleytest.view.views.details

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.mindvalleytest.R
import com.mindvalleytest.appcontentloader.callback.ContentServiceObserver
import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload
import com.mindvalleytest.appcontentloader.models.ServiceImageDownload
import com.mindvalleytest.appcontentloader.utilities.ContentTypeServiceDownload
import com.mindvalleytest.model.MasterDetails
import com.mindvalleytest.model.UserDetails
import com.mindvalleytest.utility.AlertUtil
import com.mindvalleytest.utility.DateTimeUtil
import com.mindvalleytest.utility.NetworkUtil
import com.mindvalleytest.view.views.common.FragmentInstanceHandler
import com.mindvalleytest.view.views.unsplash.UnsplashAccountFragment

import java.lang.String.format

/**
 * This Fragment is used to show photo details
 *
 *
 *
 * @author Salahuddin
 * @version 1.0
 */
class PinDetailsFragment : Fragment(), PinDetailsView, View.OnClickListener {
    internal var imgvwFullView: ImageView? = null
    internal var txtvwTitle: TextView? = null
    internal var txtvwLikes: TextView? = null
    internal var linlayParent: LinearLayout? = null
    internal var txtvwImageDimensions: TextView? = null
    internal var txtvwImagePublishedDate: TextView? = null
    internal var txtvwImageCategory: TextView? = null
    internal var btnViewProfile: Button? = null

    private var fragmentInstanceHandler: FragmentInstanceHandler? = null
    private var detailsPresenter: PinDetailsPresenter? = null
    private var masterDetails: MasterDetails? = null

    private var mProvider: ContentTypeServiceDownload? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            masterDetails = bundle.getParcelable(ARG_DATA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, vg: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, vg, false)
//        ButterKnife.bind(this, view)
        imgvwFullView = view.findViewById<ImageView>(R.id.iv_full_view)
        txtvwTitle = view.findViewById<TextView>(R.id.tv_title)
        txtvwLikes = view.findViewById<TextView>(R.id.tv_likes)
        txtvwImageDimensions = view.findViewById<TextView>(R.id.tv_image_dimensions)
        txtvwImagePublishedDate = view.findViewById<TextView>(R.id.tv_image_published_date)
        txtvwImageCategory = view.findViewById<TextView>(R.id.tv_image_category)
        linlayParent = view.findViewById<LinearLayout>(R.id.xlinlayParent)
        btnViewProfile = view.findViewById<Button>(R.id.btn_view_profile)


        return view
    }

    override fun onStart() {
        super.onStart()
        initObjects()
    }

    /**
     * This function is used to initialise the objects that are going to be used in this fragment
     */
    fun initObjects() {
        mProvider = ContentTypeServiceDownload.getInstance()
        btnViewProfile!!.setOnClickListener(this)
        detailsPresenter = PinDetailsPresenterImplementor(this)
        detailsPresenter!!.renderDetails(this.masterDetails!!)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInstanceHandler = activity as FragmentInstanceHandler
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        fragmentInstanceHandler = getActivity() as FragmentInstanceHandler
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun renderPhoto(full: String) {
        val mDataTypeImageCancel = ServiceImageDownload(masterDetails!!.urlDetails!!.thumb!!, object : ContentServiceObserver {
            override fun onStart(mDownloadDataType: ServiceContentTypeDownload) {

            }

            override fun onSuccess(mDownloadDataType: ServiceContentTypeDownload) {
                imgvwFullView!!.setImageBitmap((mDownloadDataType as ServiceImageDownload).imageBitmap)
            }

            override fun onFailure(mDownloadDataType: ServiceContentTypeDownload, statusCode: Int, errorResponse: ByteArray, e: Throwable) {
                imgvwFullView!!.setImageResource(R.drawable.no_image)
            }

            override fun onRetry(mDownloadDataType: ServiceContentTypeDownload, retryNo: Int) {

            }
        })
        mProvider?.getRequest(mDataTypeImageCancel)
    }

    override fun setBackgroundColor(color: String) {
        linlayParent!!.setBackgroundColor(Color.parseColor(color))
    }

    override fun renderUserDetails(user: UserDetails) {
        txtvwTitle!!.text = getString(R.string.clicked_by, user.name)
    }

    @SuppressLint("DefaultLocale")
    override fun renderPhotoDetails(currentData: MasterDetails) {
        txtvwLikes!!.setText(format("%d", currentData.likes))
        txtvwImageDimensions?.setText(format("Image Dimensions - %d x %d", currentData.width, currentData.height))
        txtvwImagePublishedDate?.setText(format("Creation Date - %s", DateTimeUtil.formattedDateFromDate(DateTimeUtil.getDateFromString("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", masterDetails?.createdAt!!), "dd-MM-yyyy")))
    }

    override fun renderCategories(displayCategories: String) {
        txtvwImageCategory?.setText(format("Image Category - %s", displayCategories))
    }

    override fun onClick(view: View) {
        if (view === btnViewProfile) {

            if (NetworkUtil.isAirplaneModeWithNoWIFI(activity) || !NetworkUtil.isNetworkAvailable(activity)) {
                AlertUtil.showToast(activity, getString(R.string.network_error))

                fragmentInstanceHandler!!.changeFragment(this@PinDetailsFragment,
                        UnsplashAccountFragment.newInstance(masterDetails!!.user!!.linkDetails!!.html!!),
                        true)
            } else {
                fragmentInstanceHandler!!.changeFragment(this@PinDetailsFragment,
                        UnsplashAccountFragment.newInstance(masterDetails!!.user!!.linkDetails!!.html!!),
                        true)
            }

        }
    }

    companion object {

        val ARG_DATA = "data"


        fun newInstance(masterDetails: MasterDetails): PinDetailsFragment {
            val args = Bundle()
            args.putParcelable(ARG_DATA, masterDetails)
            val fragment = PinDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
